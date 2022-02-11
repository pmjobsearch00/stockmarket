/*
 * No Copyright intended or License applies just for templating.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jpmc.newexchange.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.jpmc.newexchange.constants.ServiceConstants;
import com.jpmc.newexchange.exceptions.InvalidInputException;
import com.jpmc.newexchange.exceptions.ServiceException;
import com.jpmc.newexchange.model.CommonStock;
import com.jpmc.newexchange.model.PreferredStock;
import com.jpmc.newexchange.model.Stock;
import com.jpmc.newexchange.model.Trade;
import com.jpmc.newexchange.model.TradeType;
import com.jpmc.newexchange.repository.StockExchangeRepository;
import com.jpmc.newexchange.repository.TradeRecordsRepository;

/**
 * Class SimpleStockCalculatorService
 * 
 * It provide below five service functionalities
 * 
 * calculateDividendYield()
 * calculatePERato()
 * saveTrade()
 * calculateVolumeWeightedStockPrice()
 * calculateGBCEShareIndex()
 * 
 * It also loads a list of stocks in memory whenever instantiated as a constraint 
 * 
 * @author PM
 *
 */

public class SimpleStockCalculatorService implements IStockCalculatorServices {

	private StockExchangeRepository stockExchange = new StockExchangeRepository();
	private TradeRecordsRepository tradeRecords = new TradeRecordsRepository();

	/** Constructor that loads initial stocks in memory. */
	public SimpleStockCalculatorService() {
		loadStocksInMemory();
	}

	/**
	 * SimpleStockCalculator.calculateDivideodYield(java.lang.String,
	 * java.math.BigDecimal)
	 * 
	 * @throws InvalidInputException, ServiceException
	 */
	public BigDecimal calculateDividendYield(String stockSymbol, BigDecimal price) throws InvalidInputException, ServiceException {
		
		validatePrice(price);
		
		Stock stock = stockExchange.getStockBySymbol(stockSymbol);
		
		if (null == stock) {
			throw new ServiceException("You have entered invalid stock symbol: " + stockSymbol);
		}
		
		BigDecimal dividendYield = BigDecimal.ZERO;

		if (stock instanceof CommonStock) {
			CommonStock commonStock = (CommonStock) stock;
			dividendYield = commonStock.getLastDividend().divide(price);
		} else if (stock instanceof PreferredStock) {
			PreferredStock preferredStock = (PreferredStock) stock;
			dividendYield = preferredStock.getFixedDividend().multiply(preferredStock.getPerValue()).divide(price)
					.divide(BigDecimal.valueOf(100));
		}

		return dividendYield;
	}

	/**
	 * SimpleStockCalculator.calculatePERato(java.lang.String,
	 * java.math.BigDecimal)
	 * 
	 * @throws InvalidInputException, ServiceException
	 */
	public BigDecimal calculatePERato(String stockSymbol, BigDecimal price) throws InvalidInputException, ServiceException {
		
		validatePrice(price);
		
		Stock stock = stockExchange.getStockBySymbol(stockSymbol);
		
		if (null == stock) {
		String errMsg = "You have entered invalid stock symbol: "+stockSymbol;
		throw new ServiceException(errMsg);
		}
	
		BigDecimal peRatio = BigDecimal.ZERO;

		if (stock.getLastDividend().intValue() > 0) {
			peRatio = price.divide(stock.getLastDividend());
		}
		return peRatio;
	}

	/**
	 * SimpleStockCalculator.saveTrade(java.lang.String, java.math.BigInteger, TradeType, 
	 * java.math.BigInteger)
	 * 
	 * @throws InvalidInputException, ServiceException
	 */
	public Optional<String> saveTrade(String stockSymbol, BigInteger quantity, TradeType orderType, BigDecimal price)
			throws InvalidInputException, ServiceException {
		
		if (!((stockSymbol.equalsIgnoreCase(ServiceConstants.TEASTOCK))
				|| (stockSymbol.equalsIgnoreCase(ServiceConstants.POPSTOCK))
				|| (stockSymbol.equalsIgnoreCase(ServiceConstants.ALESTOCK))
				|| (stockSymbol.equalsIgnoreCase(ServiceConstants.GINSTOCK))
				|| (stockSymbol.equalsIgnoreCase(ServiceConstants.JOESTOCK)))) {
			
			String errMsg = "You have entered invalid stock symbol!";
			throw new ServiceException(errMsg);
		}
		
		
		validateQuantity(quantity);
		validatePrice(price);

		LocalDateTime timeStamp = LocalDateTime.now();
		Trade trade = new Trade(UUID.randomUUID().toString(), stockSymbol, timeStamp, quantity, orderType, price);
		String tradeID = tradeRecords.add(trade);

		return Optional.of(tradeID);
	}

	/**
	 * SimpleStockCalculator.calculateVolumeWeightedStockPrice(java.lang.String)
	 * 
	 * @throws InvalidInputException, ServiceException
	 */
	public BigDecimal calculateVolumeWeightedStockPrice(String stockSymbol) throws InvalidInputException, ServiceException {

		// Retrieves trades for last 5 minutes for an specific trade symbol
		List<Trade> trades = tradeRecords.getTradeByDurationInMinutes(stockSymbol, 5);
		BigDecimal weightedStockPrice = BigDecimal.ZERO;

		final Optional<BigDecimal> totalValueTraded = trades.parallelStream()
				.map(trade -> trade.getPrice().multiply(new BigDecimal(trade.getQuantity()))).reduce(BigDecimal::add);

		final Optional<BigInteger> totalVolumeTraded = trades.parallelStream().map(trade -> trade.getQuantity())
				.reduce(BigInteger::add);

		if (trades.isEmpty()) {
			return weightedStockPrice;
		} else if (!totalValueTraded.isPresent()) {
			return weightedStockPrice;
		} else if (!totalVolumeTraded.isPresent()) {
			return weightedStockPrice;
		}

		if (totalVolumeTraded.get().intValue() > 0) {
			weightedStockPrice = totalValueTraded.get().divide(new BigDecimal(totalVolumeTraded.get()),
					new MathContext(ServiceConstants.OPERATIONS_PRECISION, ServiceConstants.ROUNDING_MODE));
		}

		return weightedStockPrice;
	}

	/**
	 * SimpleStockCalculator.calculateGBCEShareIndex()
	 * 
	 * @throws InvalidInputException, ServiceException
	 */
	public double calculateGBCEShareIndex() throws InvalidInputException, ServiceException {

		BigDecimal multipliedStocks = BigDecimal.ONE;
		int stockCount = 0;
		
		for (Stock stock : stockExchange.getStocks()) {

			BigDecimal volumeWeightedStockPrice = calculateVolumeWeightedStockPrice(stock.getStockSymbol());
			if (volumeWeightedStockPrice.intValue() > 0) {
				multipliedStocks = multipliedStocks.multiply(volumeWeightedStockPrice);
				stockCount += 1;
			}
		}

		if (stockCount > 0) {
			return Math.pow(multipliedStocks.doubleValue(), 1.0 / stockCount);
		} else {
			return 0.0;
		}
	}

	// Internal helpers
	
	/**
	 * Validates the given value is a positive.
	 * 
	 * @param none
	 */
	private void loadStocksInMemory() {
		Stock tea = new CommonStock(UUID.randomUUID().toString(), ServiceConstants.TEASTOCK, 
				BigDecimal.valueOf(0), BigDecimal.valueOf(100));
		Stock pop = new CommonStock(UUID.randomUUID().toString(), ServiceConstants.POPSTOCK, 
				BigDecimal.valueOf(8), BigDecimal.valueOf(100));
		Stock ale = new CommonStock(UUID.randomUUID().toString(), ServiceConstants.ALESTOCK, 
				BigDecimal.valueOf(23),BigDecimal.valueOf(60));
		Stock gin = new PreferredStock(UUID.randomUUID().toString(), ServiceConstants.GINSTOCK, 
				BigDecimal.valueOf(8), BigDecimal.valueOf(100), BigDecimal.valueOf(2));
		Stock joe = new CommonStock(UUID.randomUUID().toString(), ServiceConstants.JOESTOCK, 
				BigDecimal.valueOf(13), BigDecimal.valueOf(250));

		stockExchange.add(tea);
		stockExchange.add(pop);
		stockExchange.add(ale);
		stockExchange.add(gin);
		stockExchange.add(joe);
	}
	
	/**
	 * Validates the given value is a positive.
	 * 
	 * @param BigDecimal value 
	 */
	private void validatePrice(BigDecimal value) {
		if (value == null || BigDecimal.ZERO.compareTo(value) >= 0) {
			throw new InvalidInputException("You have entered invalid or negetive value: " + value);
		}
	}
	
	/**
	 * Validates the given value is a positive.
	 * 
	 * @param BigDecimal value 
	 */
	private void validateQuantity(BigInteger value) {
		if (value == null || BigInteger.ZERO.compareTo(value) >= 0) {
			throw new InvalidInputException("You have entered invalid or negetive value: " + value);
		}
	}
}
