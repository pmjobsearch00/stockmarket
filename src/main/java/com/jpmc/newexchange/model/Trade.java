/*
 * No Copyright intended or License applies just for templating.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jpmc.newexchange.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * Class Trade
 * 
 * The model class to represent a Trade
 * 
 * @author PM
 *
 */

public class Trade {

	/** The trade id. */
	private String id;
	
	/** The stock symbol. */
	private String stockSymbol;
	
	/** The trade time stamp. */
	private LocalDateTime timeStamp;
	
	/** The stock quantity. */
	private BigInteger quantity;
	
	/** The BUY or SELL. */
	private TradeType tradeType;
	
	/** The price. */
	private BigDecimal price;

	/**
	 * Constructor.
	 * 
	 * Represents a trade
	 * 
	 * @param id
	 * @param stockSymbol
	 * @param timestamp
	 * @param quantity
	 * @param tradeType
	 * @param price
	 */
	public Trade(String id, String stockSymbol, LocalDateTime timeStamp, BigInteger quantity, TradeType tradeType, BigDecimal price) {
		this.id = id;
		this.stockSymbol = stockSymbol;
		this.timeStamp = timeStamp;
		this.quantity = quantity;
		this.tradeType = tradeType;
		this.price = price;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}



	/**
	 * @return the stockSymbol
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}



	/**
	 * @param stockSymbol the stockSymbol to set
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}



	/**
	 * @return the timeStamp
	 */
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}



	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}



	/**
	 * @return the quantity
	 */
	public BigInteger getQuantity() {
		return quantity;
	}



	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}



	/**
	 * @return the tradeType
	 */
	public TradeType getTradeType() {
		return tradeType;
	}



	/**
	 * @param tradeType the tradeType to set
	 */
	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}



	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}



	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Trade [id=" + id + ", stockSymbol=" + stockSymbol + ", timeStamp=" + timeStamp + ", quantity="
				+ quantity + ", tradeType=" + tradeType + ", price=" + price + "]";
	}


}