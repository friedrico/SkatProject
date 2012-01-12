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
class Player(pLeftNeighbor:Player,pRightNeighbor:Player,pHand:Hand) {
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
	val madeTricks=new CardCollection
	
	/**
	 * 
	 */
	var rightNeighbor=pRightNeighbor
	/**
	 * 
	 */
	var leftNeighbor=pLeftNeighbor

	/**
	 * 
	 */
	var ownPoints=0
	/**
	 * 
	 */
	var opposingPoints=0
	
	/**
	 * @param pTrick
	 * @return
	 */
	def getNextCard(pTrick:Trick):Int={
	  0
	}
	
	def this()=this(null,null,new Hand)
	def this(pRightNeighbor:Player)=this(null,pRightNeighbor,new Hand)
	def this(pLeftNeighbor:Player,pRightNeighbor:Player)=this(pLeftNeighbor,pRightNeighbor,new Hand)

}