/**
 *
 */
package game
import cards.{Trump, Hand, Ramsch, CardLikelihoodMap,Card}
import scala.collection.mutable.HashMap

/**
 * @author Oliver Friedrich
 *
 */
class Player(pFriendIndex: Int, pHand: Hand, pIsComputer:Boolean) {
	val isComputer = pIsComputer
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
	def getNextCard(pTrick: (Int, Int, Int,Int)): Int = {
		if(isComputer)
			0
		else{
		  var map=new HashMap[Int,Int]()
		  var index=1
		  val sb=new StringBuilder
		  val sb2=new StringBuilder
		  for(i<-0 to 31){
 		    if(handCards.filter(Card.getSuit(pTrick._4)).contains(i)){
 		      sb.append(Card.toString(i)).append("\t")
 		      sb2.append(index.toString).append("\t")
 		      map+= index->i 
		      index=index+1
 		    }
		  }
		  println(sb.append("\n").append(sb2).append("\n").append("Choose a card:"))
		  var chosen=Console.readInt
		  while(chosen<1||chosen>=index){
		    println("The number you have choosed is out of range. Please choose another one")
		    println(sb)
		    chosen=Console.readInt
		  }
		  println("You choosed: "+chosen.toString+" which is "+Card.toString(map(chosen)))
		  map(chosen)
		}
	}

	/**
	 * This method will be called only once at the beginning of the game.
	 * After this player won the auction the game needs to hand it to the other players.
	 * @return the Trump which was chosen by this winner of the auction.
	 */
	def getTrump(): Trump = {
			// TODO: calculate best trump.
			var trump:Trump = Ramsch()
			trump
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
	def this(pFriendIndex: Int) = this(pFriendIndex, new Hand,true)
	def this() = this(0, new Hand,true)
	def this(pFriendIndex: Int,pIsComputer:Boolean) = this(pFriendIndex, new Hand,pIsComputer)
	def this(pIsComputer:Boolean) = this(0, new Hand,pIsComputer)
}