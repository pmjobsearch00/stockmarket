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

/**
 * Class Stock
 * 
 * The model class to represent a Stock
 * 
 * @author PM
 *
 */
public class Stock {

	/** The trade id. */
	private String id;
	
	/** The stock symbol. */
	private String stockSymbol;
	
	/** The lastDividend. */
	private BigDecimal lastDividend;

	/** The perValue. */
	BigDecimal perValue;

	/**
	 * Constructor.
	 * 
	 * Represents a stock
	 * 
	 * @param id
	 * @param stockSymbol
	 * @param lastDividend
	 * @param perValue
	 */ 
	public Stock(String id, String stockSymbol, BigDecimal lastDividend, BigDecimal perValue) {
		this.id = id;
		this.stockSymbol = stockSymbol;
		this.lastDividend = lastDividend;
		this.perValue = perValue;
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
	 * @return the lastDividend
	 */
	public BigDecimal getLastDividend() {
		return lastDividend;
	}



	/**
	 * @param lastDividend the lastDividend to set
	 */
	public void setLastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}



	/**
	 * @return the perValue
	 */
	public BigDecimal getPerValue() {
		return perValue;
	}



	/**
	 * @param perValue the perValue to set
	 */
	public void setPerValue(BigDecimal perValue) {
		this.perValue = perValue;
	}

	
	@Override
	public String toString() {
		return "Stock [id=" + id + ", stockSymbol=" + stockSymbol + ", lastDividend=" + lastDividend + ", perValue="
				+ perValue + "]";
	}
	
}