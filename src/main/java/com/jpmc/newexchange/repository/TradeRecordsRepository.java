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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.jpmc.newexchange.exceptions.InvalidInputException;
import com.jpmc.newexchange.model.Trade;

/**
 * Class TradeRecordsRepository
 * 
 * It provide below five repository functionalities
 * 
 * add()
 * remove()
 * getTrades()
 * getTradeByDurationInMinutes()
 * 
 * 
 * @author PM
 *
 */

public class TradeRecordsRepository {

	/** Data structure that holds the trades in memory. */
	ConcurrentHashMap<String, Trade> trades;

	/** Constructor that initializes Trades data structure in memory. */
	public TradeRecordsRepository() {
		trades = new ConcurrentHashMap<String, Trade>();
	}

	/**
	 * TradeRecordsRepository.add(com.jpmc.newexchange.model.Trade)
	 * 
	 * @throws none
	 */
	public String add(Trade trade) {
		trades.put(trade.getId(), trade);
		return trade.getId();
	}

	/**
	 * TradeRecordsRepository.remove(com.jpmc.newexchange.model.Trade)
	 * 
	 * @throws none
	 */
	public String remove(Trade trade) {
		trades.remove(trade.getId());
		return trade.getId();
	}

	/**
	 * TradeRecordsRepository.getTrades()
	 * 
	 * @throws none
	 */
	public List<Trade> getTrades() {
		return new ArrayList<Trade>(trades.values());
	}
	
	/**
	 * TradeRecordsRepository.getTradeByDurationInMinutes(java.lang.String, int)
	 * 
	 * @throws none
	 */
	public List<Trade> getTradeByDurationInMinutes(String stockSymbol, int minutes) {		
		LocalDateTime fiveMinutesBefore = LocalDateTime.now().minusMinutes(minutes);
		
		return (new ArrayList<Trade>(trades.values()).parallelStream()
				.filter(trade -> trade.getTimeStamp().isAfter(fiveMinutesBefore))
				.filter(trade -> trade.getStockSymbol().equalsIgnoreCase(stockSymbol))
				.collect(Collectors.toList()));

	}

}
