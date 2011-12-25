/**
 * 
 */
package cards
import game.Player


/**
 * @author Oliver Friedrich
 *
 */
class Trick(pForehand:Player,pMiddlehand:Player,pRearhand:Player) extends CardCollection {
  /**
 * 
 */
val forehand=pForehand
  /**
 * 
 */
val middlehand=pMiddlehand
  /**
 * 
 */
val rearhand=pRearhand 
  
  /**
 * @return
 */
def getWinner():Player={
    null
  }
}