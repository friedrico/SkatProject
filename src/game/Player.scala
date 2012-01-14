/**
 *
 */
package game
import cards.Hand
import cards.CardLikelihoodMap
import cards.Trick
import cards.CardCollection

/**
 * @author Oliver Friedrich
 *
 */
class Player(pFriendIndex:Int,pHand:Hand) {
	/**
	 * 
	 */
	val handCards=pHand
	/**
	 * 
	 */
	val leftLikelihood=new CardLikelihoodMap
	/**
	 * 
	 */
	val rightLikelihood=new CardLikelihoodMap
	/**
	 * 
	 */
	var ownPoints=0
	/**
	 * 
	 */
	var opposingPoints=0
	/**
	 * 
	 */
	val friendIndex=pFriendIndex
	/**
	 * @param pTrick
	 * @return
	 */
	def getNextCard(pTrick:Trick):Int={
	  0
	}
	
	def this(pFriendIndex:Int)=this(pFriendIndex,new Hand)
}