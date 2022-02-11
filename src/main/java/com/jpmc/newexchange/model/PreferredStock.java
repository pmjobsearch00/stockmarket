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
 * Class PreferredStock
 * 
 * The model class to represent a PreferredStock
 * 
 * @author PM
 *
 */
public class PreferredStock extends Stock{

	/** The fixedDividend. */
	private BigDecimal fixedDividend;

	/**
	 * Constructor.
	 * 
	 * Represents a Preferred Stock
	 * 
	 * @param id
	 * @param stockSymbol
	 * @param lastDividend
	 * @param perValue
	 * @param fixedDividend
	 */ 
	public PreferredStock(String id, String stockSymbol, BigDecimal lastDividend, BigDecimal parValue, BigDecimal fixedDividend) {
		super(id, stockSymbol, lastDividend, parValue);
		this.fixedDividend = fixedDividend;
	}

	/**
	 * @return the fixedDividend
	 */
	public BigDecimal getFixedDividend() {
		return fixedDividend;
	}

	/**
	 * @param fixedDividend the fixedDividend to set
	 */
	public void setFixedDividend(BigDecimal fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	
}
