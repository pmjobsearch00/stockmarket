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
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpmc.newexchange.model.TradeType;
import com.jpmc.newexchange.exceptions.InvalidInputException; 
import com.jpmc.newexchange.exceptions.ServiceException; 

/**
 * JUnit Test class for SimpleStockCalculatorService
 * @author PM
 *
 */
public class SimpleStockCalculatorServiceTest {

	Logger logger = Logger.getLogger(SimpleStockCalculatorServiceTest.class.getName());
	
	/** The class under tests. */
	SimpleStockCalculatorService simpleStockService;

	/**
	 * Runs before the tests.
	 */
	@Before
	public void setUp() throws Exception {
		simpleStockService = new SimpleStockCalculatorService();
	}
	
	
	/**
	 * Runs after the tests.
	 */
	@After
	public void tearDown() throws Exception {
		simpleStockService = null;
	}

	/**
	 * Test method for
	 * SimpleStockCalculator.calculateDivideodYield(java.lang.String,
	 * java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test
	public void calculateDividendYieldCommonStockTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.calculateDivideodYield method for common stock.");

		String stockSymbol1 = "TEA";
		BigDecimal price1 = BigDecimal.valueOf(25);
		Assert.assertEquals(BigDecimal.ZERO, simpleStockService.calculateDividendYield(stockSymbol1, price1));
		
		String stockSymbol2 = "POP";
		BigDecimal price2 = BigDecimal.valueOf(8);
		Assert.assertEquals(BigDecimal.ONE, simpleStockService.calculateDividendYield(stockSymbol2, price2));
		
		String stockSymbol3 = "ALE";
		BigDecimal price3 = BigDecimal.valueOf(1);
		Assert.assertEquals(new BigDecimal(23), simpleStockService.calculateDividendYield(stockSymbol3, price3));
		
		String stockSymbol4 = "JOE";
		BigDecimal price4 = BigDecimal.valueOf(13);
		Assert.assertEquals(BigDecimal.ONE, simpleStockService.calculateDividendYield(stockSymbol4, price4));

	}
	
	/**
	 * Test method for
	 * SimpleStockCalculator.calculateDivideodYield(java.lang.String,
	 * java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test
	public void calculateDividendYieldPreferredStockTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.calculateDivideodYield method for preferred stock.");

		String stockSymbol = "GIN";
		BigDecimal price = BigDecimal.valueOf(2);
		Assert.assertEquals(BigDecimal.ONE, simpleStockService.calculateDividendYield(stockSymbol, price));

	}
	
	/**
	 * Test method for
	 * SimpleStockCalculator.calculateDivideodYield(java.lang.String,
	 * java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test(expected = ServiceException.class)
	public void calculateDividendYieldInvalidSymbolTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.calculateDivideodYield method for invalid stock.");

		String stockSymbol = "InvalidTEA";
		BigDecimal price = BigDecimal.valueOf(25);
		simpleStockService.calculateDividendYield(stockSymbol, price);

	}
	
	/**
	 * Test method for
	 * SimpleStockCalculator.calculateDivideodYield(java.lang.String,
	 * java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test(expected = InvalidInputException.class)
	public void calculateDividendYieldInvalidPriceTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.calculateDivideodYield method for invalid price.");

		String stockSymbol = "TEA";
		BigDecimal price = BigDecimal.valueOf(-25);
		simpleStockService.calculateDividendYield(stockSymbol, price);

	}
	
	/**
	 * Test method for SimpleStockCalculator.calculatePERato(java.lang.String,
	 * java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test
	public void calculatePERatoForValidSymbolAndPrice() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.CalculatePERato method for valid price and symbol.");

		String stockSymbol = "POP";
		BigDecimal price = BigDecimal.valueOf(8);
		Assert.assertEquals(BigDecimal.ONE, simpleStockService.calculatePERato(stockSymbol, price));

	}
	
	/**
	 * Test method for SimpleStockCalculator.calculatePERato(java.lang.String,
	 * java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test(expected = ServiceException.class)
	public void calculatePERatoInvalidSymbolTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.CalculatePERato method with invalid symbol.");
		
		String stockSymbol = "InvalidPOP";
		BigDecimal price = BigDecimal.valueOf(8);
		
		simpleStockService.calculatePERato(stockSymbol, price);

	}
	
	/**
	 * Test method for SimpleStockCalculator.calculatePERato(java.lang.String,
	 * java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test(expected = InvalidInputException.class)
	public void calculatePERatoInvalidPriceTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.CalculatePERato method with invalid price.");

		String stockSymbol = "POP";
		BigDecimal price = BigDecimal.valueOf(-8);
		simpleStockService.calculatePERato(stockSymbol, price);

	}

	/**
	 * Test method for SimpleStockCalculator.saveTrade(java.lang.String, int,
	 * java.lang.String, java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test
	public void saveTradeWithValidSymbolAndValidPriceAndValidQuantityTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.testSaveTrade method with valid symbol, price and quantity.");

		String stockSymbol1 = "POP";
		BigInteger quantity1 = BigInteger.valueOf(10);
		TradeType orderType1 = TradeType.BUY;
		BigDecimal price1 = BigDecimal.valueOf(10);
		simpleStockService.saveTrade(stockSymbol1, quantity1, orderType1, price1);

		String stockSymbol2 = "GIN";
		BigInteger quantity2 = BigInteger.valueOf(20);
		TradeType orderType2 = TradeType.BUY;
		BigDecimal price2 = BigDecimal.valueOf(20);
		simpleStockService.saveTrade(stockSymbol2, quantity2, orderType2, price2);

		String stockSymbol3 = "JOE";
		BigInteger quantity3 = BigInteger.valueOf(10);
		TradeType orderType3 = TradeType.SELL;
		BigDecimal price3 = BigDecimal.valueOf(10);
		simpleStockService.saveTrade(stockSymbol3, quantity3, orderType3, price3);
		
		String stockSymbol4 = "TEA";
		BigInteger quantity4 = BigInteger.valueOf(5);
		TradeType orderType4 = TradeType.BUY;
		BigDecimal price4 = BigDecimal.valueOf(5);
		simpleStockService.saveTrade(stockSymbol4, quantity4, orderType4, price4);
		
		String stockSymbol5 = "ALE";
		BigInteger quantity5 = BigInteger.valueOf(30);
		TradeType orderType5 = TradeType.BUY;
		BigDecimal price5 = BigDecimal.valueOf(30);
		simpleStockService.saveTrade(stockSymbol5, quantity5, orderType5, price5);
		
		Optional<String> tradeId1 = simpleStockService.saveTrade(stockSymbol1, quantity1, orderType1, price1);
		Optional<String> tradeId2 = simpleStockService.saveTrade(stockSymbol2, quantity2, orderType2, price2);
		Optional<String> tradeId3 = simpleStockService.saveTrade(stockSymbol3, quantity3, orderType3, price3);
		Optional<String> tradeId4 = simpleStockService.saveTrade(stockSymbol4, quantity4, orderType4, price4);
		Optional<String> tradeId5 = simpleStockService.saveTrade(stockSymbol5, quantity5, orderType5, price5);

		Assert.assertTrue(tradeId1.isPresent());
		Assert.assertTrue(tradeId2.isPresent());
		Assert.assertTrue(tradeId3.isPresent());
		Assert.assertTrue(tradeId4.isPresent());
		Assert.assertTrue(tradeId5.isPresent());

	}
	
	/**
	 * Test method for SimpleStockCalculator.saveTrade(java.lang.String, int,
	 * java.lang.String, java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test(expected = InvalidInputException.class)
	public void saveTradeWithValidSymbolAndValidPriceAndInvalidQuantityTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.testSaveTrade method with valid symbol, valid price and invalid quantity.");

		String stockSymbol = "POP";
		BigInteger quantity = BigInteger.valueOf(-50);
		TradeType orderType = TradeType.BUY;
		BigDecimal price = BigDecimal.valueOf(20);

		simpleStockService.saveTrade(stockSymbol, quantity, orderType, price);

	}
	
	/**
	 * Test method for SimpleStockCalculator.saveTrade(java.lang.String, int,
	 * java.lang.String, java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test(expected = InvalidInputException.class)
	public void saveTradeWithValidSymbolAndInvalidPriceAndValidQuantityTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.testSaveTrade method with valid symbol, invalid price and valid quantity.");

		String stockSymbol = "POP";
		BigInteger quantity = BigInteger.valueOf(50);
		TradeType orderType = TradeType.BUY;
		BigDecimal price = BigDecimal.valueOf(-20);
		
		simpleStockService.saveTrade(stockSymbol, quantity, orderType, price);
	}

	/**
	 * Test method for SimpleStockCalculator.weightedStockPrice(java.lang.String,
	 * int, java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test
	public void calculateVolumeWeightedStockPriceWithThreeTradesTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.testWeightedStockPrice method with one trades of each recorded.");

		String stockSymbol1 = "POP";
		BigInteger quantity1 = BigInteger.valueOf(10);
		TradeType orderType1 = TradeType.BUY;
		BigDecimal price1 = BigDecimal.valueOf(10);
		simpleStockService.saveTrade(stockSymbol1, quantity1, orderType1, price1);

		String stockSymbol2 = "GIN";
		BigInteger quantity2 = BigInteger.valueOf(20);
		TradeType orderType2 = TradeType.BUY;
		BigDecimal price2 = BigDecimal.valueOf(20);
		simpleStockService.saveTrade(stockSymbol2, quantity2, orderType2, price2);

		String stockSymbol3 = "JOE";
		BigInteger quantity3 = BigInteger.valueOf(10);
		TradeType orderType3 = TradeType.SELL;
		BigDecimal price3 = BigDecimal.valueOf(10);
		simpleStockService.saveTrade(stockSymbol3, quantity3, orderType3, price3);
		
		String stockSymbol4 = "TEA";
		BigInteger quantity4 = BigInteger.valueOf(5);
		TradeType orderType4 = TradeType.BUY;
		BigDecimal price4 = BigDecimal.valueOf(5);
		simpleStockService.saveTrade(stockSymbol4, quantity4, orderType4, price4);
		
		String stockSymbol5 = "ALE";
		BigInteger quantity5 = BigInteger.valueOf(30);
		TradeType orderType5 = TradeType.BUY;
		BigDecimal price5 = BigDecimal.valueOf(30);
		simpleStockService.saveTrade(stockSymbol5, quantity5, orderType5, price5);

		final BigDecimal vwStockPrice1 = simpleStockService.calculateVolumeWeightedStockPrice(stockSymbol1);
		Assert.assertEquals(new BigDecimal("10"), vwStockPrice1);
		
		final BigDecimal vwStockPrice2 = simpleStockService.calculateVolumeWeightedStockPrice(stockSymbol2);
		Assert.assertEquals(new BigDecimal("20"), vwStockPrice2);
		
		final BigDecimal vwStockPrice3 = simpleStockService.calculateVolumeWeightedStockPrice(stockSymbol3);
		Assert.assertEquals(new BigDecimal("10"), vwStockPrice3);
		
		final BigDecimal vwStockPrice4 = simpleStockService.calculateVolumeWeightedStockPrice(stockSymbol4);
		Assert.assertEquals(new BigDecimal("5"), vwStockPrice4);
		
		final BigDecimal vwStockPrice5 = simpleStockService.calculateVolumeWeightedStockPrice(stockSymbol5);
		Assert.assertEquals(new BigDecimal("30"), vwStockPrice5);
	}

	/**
	 * Test method for SimpleStockCalculator.weightedStockPrice(java.lang.String,
	 * int, java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test
	public void calculateVolumeWeightedStockPriceWithOutTradesTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.testWeightedStockPrice method with out any trades recorded.");

		final BigDecimal vwStockPrice = simpleStockService.calculateVolumeWeightedStockPrice("POP");
		Assert.assertEquals(BigDecimal.ZERO, vwStockPrice);
	}
	
	/**
	 * Test method for SimpleStockCalculator.weightedStockPrice(java.lang.String,
	 * int, java.math.BigDecimal)
	 * 
	 * @throws Exception
	 */
	@Test
	public void calculateVolumeWeightedStockPriceIgnoreTradesBeforeFiveMinutesTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.testWeightedStockPrice method ingnoring trades before 5 minutes.");

		String stockSymbol1 = "POP";
		BigInteger quantity1 = BigInteger.valueOf(30);
		TradeType orderType1 = TradeType.BUY;
		BigDecimal price1 = BigDecimal.valueOf(20);
		simpleStockService.saveTrade(stockSymbol1, quantity1, orderType1, price1);

		// Sleep for 6 minutes and see if the earlier trades were considered
		Thread.sleep(360000);

		String stockSymbol2 = "POP";
		BigInteger quantity2 = BigInteger.valueOf(40);
		TradeType orderType2 = TradeType.BUY;
		BigDecimal price2 = BigDecimal.valueOf(40);
		simpleStockService.saveTrade(stockSymbol2, quantity2, orderType2, price2);

		String stockSymbol3 = "POP";
		BigInteger quantity3 = BigInteger.valueOf(10);
		TradeType orderType3 = TradeType.SELL;
		BigDecimal price3 = BigDecimal.valueOf(10);
		simpleStockService.saveTrade(stockSymbol3, quantity3, orderType3, price3);

		final BigDecimal vwStockPrice = simpleStockService.calculateVolumeWeightedStockPrice(stockSymbol1);
		Assert.assertEquals(new BigDecimal("34"), vwStockPrice);
	}
	
	/**
	 * Test method for SimpleStockCalculator.calculateGBCEShareIndexWithTwoTradesTest()
	 * 
	 * @throws Exception
	 */
	@Test
	public void calculateGBCEShareIndexWithTwoTradesTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.calculateGBCEShareIndex method with 2 trades.");

		String stockSymbol1 = "POP";
		BigInteger quantity1 = BigInteger.valueOf(10);
		TradeType orderType1 = TradeType.BUY;
		BigDecimal price1 = BigDecimal.valueOf(10);
		simpleStockService.saveTrade(stockSymbol1, quantity1, orderType1, price1);

		String stockSymbol3 = "ALE";
		BigInteger quantity3 = BigInteger.valueOf(10);
		TradeType orderType3 = TradeType.SELL;
		BigDecimal price3 = BigDecimal.valueOf(40);
		simpleStockService.saveTrade(stockSymbol3, quantity3, orderType3, price3);
		
		double GBCEAllShareIndex = simpleStockService.calculateGBCEShareIndex();
		Assert.assertTrue(GBCEAllShareIndex == 20.0);

	}
	
	/**
	 * Test method for SimpleStockCalculator.calculateGBCEShareIndexWithTwoTradesTest()
	 * 
	 * @throws Exception
	 */
	@Test
	public void calculateGBCEShareIndexWithOutTradesTest() throws Exception {
		logger.info("Begin: Testing SimpleStockCalculator.calculateGBCEShareIndex method with no trades.");
		
		double GBCEAllShareIndex = simpleStockService.calculateGBCEShareIndex();
		Assert.assertTrue(GBCEAllShareIndex == 0.0);

	}

}
