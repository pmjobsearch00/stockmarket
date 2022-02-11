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
 * Class CommonStock
 * 
 * The model class to represent a CommonStock
 * 
 * @author PM
 *
 */
public class CommonStock extends Stock{

	/**
	 * Constructor.
	 * 
	 * Represents a Common Stock
	 * 
	 * @param id
	 * @param stockSymbol
	 * @param lastDividend
	 * @param perValue
	 */ 
	public CommonStock(String id, String stockSymbol, BigDecimal lastDividend, BigDecimal parValue) {
		super(id, stockSymbol, lastDividend, parValue);

	}

}