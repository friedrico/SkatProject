/**
 *
 */
package game
import cards.{Trump, Hand, Ramsch, CardLikelihoodMap,Card}
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

/**
 * @author Oliver Friedrich
 *
 */
class Player(pFriendIndex: Int, pHand: Hand, pIsComputer:Boolean) {
	/**
	 *
	 */
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
	def getNextCard(pTrick: ListBuffer[Option[Int]]): Option[Int]= {
		if(isComputer){
			getNthCard(0,pTrick)
		}
		else{
			var map=new HashMap[Int,Int]()
			var index=1
			val sb=new StringBuilder
			val sb2=new StringBuilder
			sb.append("Current Trick: ")
			//convert cards to string
			pTrick.foreach{
				rawcard => rawcard match{
					case Some(card)=> sb.append(Card.toString(card))
					case _ => sb.append("_ ")
				}
			}
			sb.append("\n")
			for(i<-0 to 31){
				pTrick(3) match{
					case Some(card) => //I'm middle- or rearHand
						if(handCards.filter(card).contains(i)){
							sb.append(Card.toString(i)).append("\t")
							sb2.append(index.toString).append("\t")
							map+= index->i 
							index=index+1
						}
					case None=> //I'm forehand - not really nice done...
						if(handCards.contains(i)){
							sb.append(Card.toString(i)).append("\t")
							sb2.append(index.toString).append("\t")
							map+= index->i 
							index=index+1
						}
					}
			}
			try{
				println(sb.append("\n").append(sb2).append("\n").append("Choose a card:"))
				var chosen=Console.readInt
				while(chosen<1||chosen>=index){
					println("The number you have chosen is out of range. Please choose another one")
					println(sb)
					chosen=Console.readInt
				}
				// println("You choosed: "+chosen.toString+" which is "+Card.toString(map(chosen)))
				Some(map(chosen))
			}
			catch{
				case nfe:NumberFormatException =>   println("This is not a number. Please insert a number")
																getNextCard(pTrick)
				case e:Exception=> throw e 
			}
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

	def getNthCard(n:Int,pTrick:ListBuffer[Option[Int]]):Option[Int]={
		var count=0
		for(i <- 0 to 31){
			pTrick(3) match{
				case Some(pCard) => //I'm middle- or rearHand
					var long=handCards.filter(pCard)
					if(handCards.filter(pCard).contains(i)){
						if(count<n)
							count=count+1
						else{
							handCards.remove(i)
							return Some(i)
						}
					}
				case None => 
					if(handCards.contains(i)){
						if(count<n)
							count=count+1
						else{
							handCards.remove(i)
							return Some(i)
						}
					}
			}
		}
		None
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