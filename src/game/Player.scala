/**
 *
 */
package game
import cards.{Trump, Hand, Diamonds, CardLikelihoodMap}

/**
 * @author Oliver Friedrich
 *
 */
class Player(pFriendIndex: Int, pHand: Hand) {
	/**
	 *
	 */
	val handCards = pHand
			/**
			 *
			 */
			val leftLikelihood = new CardLikelihoodMap
			/**
			 *
			 */
			val rightLikelihood = new CardLikelihoodMap
			/**
			 *
			 */
			var ownPoints = 0
			/**
			 *
			 */
			var opposingPoints = 0
			/**
			 *
			 */
			var friendIndex = pFriendIndex
			/**
			 * @param pTrick
			 * @return
			 */
			def getNextCard(pTrick: (Long, Long, Long)): Int = {
		0
	}

	/**
	 * This method will be called only once at the beginning of the game.
	 * After this player won the auction the game needs to hand it to the other players.
	 * @return the Trump which was chosen by this winner of the auction.
	 */
	def getTrump(): Trump = {
			// TODO: calculate best trump.
			var trump:Trump = new Diamonds
					return trump
	}

	def toXML(pId:String) = {
		<player id={pId}>
		<hand>{handCards}</hand>
		<points>
		<own>{ownPoints}</own>
		<opposing>{opposingPoints}</opposing>
		</points>
		<friend>{friendIndex}</friend>
		<cardlikelihoodmaps>{leftLikelihood.toXML("0")++rightLikelihood.toXML("1")}</cardlikelihoodmaps>
		</player>
	}
	def this(pFriendIndex: Int) = this(pFriendIndex, new Hand)
	def this() = this(0, new Hand)
}