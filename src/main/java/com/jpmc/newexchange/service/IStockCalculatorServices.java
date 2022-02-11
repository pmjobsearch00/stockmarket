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

import com.jpmc.newexchange.exceptions.InvalidInputException;
import com.jpmc.newexchange.exceptions.ServiceException;
import com.jpmc.newexchange.model.TradeType;


/**
 * Interface IStockCalculatorServices
 * 
 * It provide below five service functionalities
 * 
 * calculateDividendYield()
 * calculatePERato()
 * saveTrade()
 * calculateVolumeWeightedStockPrice()
 * calculateGBCEShareIndex()
 *  
 * 
 * @author PM
 *
 */

public interface IStockCalculatorServices {

	/**
	 * calculates the Dividend Yield.
	 * @param stock symbol and price
	 * @return the DividendYield
	 * @throws InvalidInputException, ServiceException
	 */
	BigDecimal calculateDividendYield(String stockSymbol, BigDecimal price) throws InvalidInputException, ServiceException;

	/**
	 * calculates the PERato.
	 * @param stock symbol and price
	 * @return the PERato
	 * @throws InvalidInputException, ServiceException
	 */
	BigDecimal calculatePERato(String stockSymbol, BigDecimal price) throws InvalidInputException, ServiceException;

	/**
	 * calculates the Dividend Yield.
	 * @param stock symbol and price
	 * @return the DividendYield
	 * @throws InvalidInputException, ServiceException
	 */
	Optional<String> saveTrade(String stockSymbol, BigInteger quantity, TradeType orderType, BigDecimal price) 
			throws InvalidInputException, ServiceException;

	/**
	 * Saves a trade in memory.
	 * @param stock symbol, quantity, trade type e,g, buy or sell, and price
	 * @return the trade ID
	 * @throws InvalidInputException, ServiceException
	 */
	BigDecimal calculateVolumeWeightedStockPrice(String stockSymbol) throws InvalidInputException, ServiceException;
	
	/**
	 * calculates the ShareIndex using geometric mean.
	 * @param none
	 * @return the ShareIndex
	 * @throws InvalidInputException, ServiceException
	 */
	double calculateGBCEShareIndex() throws InvalidInputException, ServiceException;

}
