/**
 *
 */
package game
import cards.Hand
import cards.CardLikelihoodMap
import cards.Trick
import cards.CardCollection
import cards.Trump

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
	
	
	def giveTrumpToHand(pTrump:Trump) = {
	  handCards.setTrumpMask(pTrump)
	}
	
	
	def toXML{
	<player>
		<hand>
			{handCards}
		</hand>
		<points>
			<own>
				{ownPoints}
			</own>
			<opposing>
				{opposingPoints}
			</opposing>
		</points>
	</player>
	}
	def this(pFriendIndex:Int)=this(pFriendIndex,new Hand)
	def this() = this(0, new Hand)
}