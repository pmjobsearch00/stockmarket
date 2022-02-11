/*
 * No Copyright intended or License applies just for templating.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jpmc.newexchange.constants;

import java.math.RoundingMode;
import static java.math.RoundingMode.HALF_EVEN;


/**
 * Class ServiceConstants
 * 
 * It holds the services constants 
 * 
 * @author PM
 *
 */

public class ServiceConstants {

    public static final String TEASTOCK = "TEA";
    public static final String POPSTOCK = "POP";
    public static final String ALESTOCK = "ALE";
    public static final String GINSTOCK = "GIN";
    public static final String JOESTOCK = "JOE";
    
    public static final RoundingMode ROUNDING_MODE = HALF_EVEN;
    public static final int OPERATIONS_PRECISION = 40;
}
