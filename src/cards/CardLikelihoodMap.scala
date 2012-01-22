/**
 *
 */
package cards
import scala.xml.Elem

/**
 * @author Oliver Friedrich
 *
 */
class CardLikelihoodMap {

  def toXML(pId:String):Elem={
    <cardlikelihoodmap id={pId}></cardlikelihoodmap>
    }
}