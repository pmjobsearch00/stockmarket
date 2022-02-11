/*
 * No Copyright intended or License applies just for templating.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jpmc.newexchange.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.jpmc.newexchange.model.Stock;

/**
 * Class TradeRecordsRepository
 * 
 * It provide below repository functionalities
 * 
 * add()
 * remove()
 * getStocks()
 * getStockBySymbol()
 * 
 * 
 * @author PM
 *
 */

public class StockExchangeRepository {

	/** Data structure that holds the stocks in memory. */
	ConcurrentHashMap<String, Stock> stocks;

	/** Constructor that initializes Stocks data structure in memory. */
	public StockExchangeRepository() {
		stocks = new ConcurrentHashMap<String, Stock>();
	}

	/**
	 * TradeRecordsRepository.add(com.jpmc.newexchange.model.Stock)
	 * 
	 * @throws none
	 */
	public void add(Stock stock) {
		stocks.put(stock.getId(), stock);
	}

	/**
	 * TradeRecordsRepository.remove(com.jpmc.newexchange.model.Stock)
	 * 
	 * @throws none
	 */
	public void remove(Stock stock) {
		stocks.remove(stock.getId());
	}

	/**
	 * TradeRecordsRepository.getStocks()
	 * 
	 * @throws none
	 */
	public List<Stock> getStocks() {
		return new ArrayList<Stock>(stocks.values());
	}
	
	/**
	 * TradeRecordsRepository.getStockBySymbol(java.lang.String, int)
	 * 
	 * @throws none
	 */
	public Stock getStockBySymbol(String symbol) {
		for (Stock stock : getStocks()) {
            if (stock.getStockSymbol().equalsIgnoreCase(symbol)) {
            	return stock;
            }
         }
		
		return null;
	}

}
