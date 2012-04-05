/**
 *
 */
package game
import scala.xml.{XML, TopScope, Text, PrettyPrinter, Elem,UnprefixedAttribute}
import cards.{Trump, Skat, Hand, _}
import scala.xml.NodeSeq
import java.text.ParseException
import scala.collection.mutable.ListBuffer
import util.Graph
import scala.util.Random

/**
 * @author Oliver Friedrich
 *
 */
class Game {
  /**
   * A list of players. the players are listed in follow way:
   * player0, player1, player2, player0, player1
   */
  var players = List(new Player(), new Player())

  players ++= new Player() :: players
  /**
   * The two Cards which lent the name to the Game.
   */
  var skat: (Int, Int) = (0, 0)
  // deal out the cards to the skat and to the players.
 // initialize
  /**
   * The Trick which represents the current Cards on the table and where every Player has to play his Cards
   * The first 3 Cards are the played Cards, the fourth is a redundancy which is the forehand's Card.
   */
  var currentTrick: ListBuffer[Option[Int]] = ListBuffer(None,None,None,None)
  /**
   * The current Trump of the Game.
   */
  var trump: Trump = Ramsch()

  var tricks = List[ListBuffer[Option[Int]]]()
  
  val graph = new Graph[ListBuffer[Option[Int]],Int] //NodeValue, EdgeValue
  
  
  
  /**
   * Writes the current environment (which means the current state of the game) to the given file. It creates a XML file which represents the current state of the Game. So which Player has which Cards and who has to play the next Card. The syntax of the XML file is really easy. It shall describe the game readable and does not save it the most efficient way.
   * @param pPath the path where to create the XML file
   */
  def saveState(pPath: String): Elem = {
var outFile = new java.io.FileOutputStream(pPath)
var outStream = new java.io.PrintStream(outFile)


    val saveXml =	
<game>
<currentRound>
<trump>{trump}</trump>
<trick>{currentTrick(0)},{currentTrick(1)},{currentTrick(2)},{currentTrick(3)}</trick>
<skat>{skat._1},{skat._2}</skat>
</currentRound>
<initialDistribution>{players(0).toXML("0")++players(1).toXML("1")++players(2).toXML("2")}</initialDistribution>
<tricks>{for (trick <- tricks) yield {<trick>{trick(0)},{trick(1)},{trick(2)},{trick(3)}</trick>}}</tricks>
<serializedTree></serializedTree>
</game>

    val prettyPrinter = new PrettyPrinter(30,2)
    val outString = prettyPrinter.format(saveXml)
    println(outString)
   // XML.save(pPath, saveXml)
    outStream.print(outString)
    outStream.close
    outStream=null
    outFile=null
    saveXml
  }
  /**
   * Reads an environment (which means the current state of the game) from the given file. It is the complement of saveState and so saveState(x);loadState(x) will not change anything.
   * @param pPath the file to read
   */
  def readState(pPath: String) = {
    val readXml = XML.load(pPath)
    if(!(readXml \"currentRound" \ "skat").isEmpty)
   	 skat = ((readXml \"currentRound" \ "skat").text.split(",")(0).toInt, 
   			 	(readXml \"currentRound" \ "skat").text.split(",")(1).toInt)
   	 
	if(!(readXml \"currentRound" \ "trick").isEmpty){
	  currentTrick = ListBuffer(Some((readXml \"currentRound" \ "trick").text.split(",")(0).toInt), //TODO Some shouldnt be possible when None is saved
					      Some((readXml \"currentRound" \ "trick").text.split(",")(1).toInt),
					      Some((readXml \"currentRound" \ "trick").text.split(",")(2).toInt),
					      Some((readXml \"currentRound" \ "trick").text.split(",")(3).toInt))
	}
    
      
    trump = (readXml \"currentRound" \ "trump").text match {
      case "Hearts" => Hearts()
      case "Spades" => Spades()
      case "Ramsch" => Ramsch()
      case "Clubs" => Clubs()
      case "Null" => Null()
      case "Grand" => Grand()
      case "Diamonds" => Diamonds()
      case _ => throw new ParseException("The trump couldn't be read correctly",0)
      
      
    }
    val tmpPlayers= readXml \"initialDistribution"\"player"
    var i=0
    tmpPlayers.foreach({tmpPlayer=>
      players(i).handCards.vector=(tmpPlayer\"hand").text.toLong
      players(i).friendIndex=(tmpPlayer\"friend").text.toInt
      players(i).ownPoints=(tmpPlayer\"points"\"own").text.toInt
      players(i).opposingPoints=(tmpPlayer\"points"\"opposing").text.toInt
      i+=1
    })
//    val ownPoints=
//    val opposingPoints
//    val hand
//    val friendIndex
//    val cardLikelihoodMap
//    
  }
  /**
   * Deals the Cards. Will create a Skat and give 10 Cards to every Player
   */
  def dealOut = {
    //	    10987654321098765432109876543210
    //	    10110011100000010000000110000001	forehand
    //	    01000100000110100101001000000110	middlehand
    //	    00001000011001000010100001111000	rearhand
    //	    00000000000000001000010000000000	skat
    //
    //	    vector(cardCollection) = sum over all a in A for 2^a
    //	    A(forhand) = {0,7,8,16,23,24,25,28,29,31}
    //	    A(middle)  = {1,2,9,12,14,17,19,20,26,30}
    //	    A(rear)    = {3,4,5,6,11,13,18,21,22,27}
    //	    A(Skat)	 = {10, 15}
    //
    //	    vector(forehand)   = 3011576193
    //	    vector(middlehand) = 1142575622
    //	    vector(rearhand)   =  140781688
    //	    vector(skat)       =      33792
    //	    -------------------------------
    //	    +		   		     = 4294967295	= 2^32 - 1 => correct

    players(0).handCards.addCards(0xB3810181L)
    players(1).handCards.addCards(0x441A5206L)
    players(2).handCards.addCards(0x08642878L)
    skat = (10, 15)
  }
  /**
   * Returns the number of steps needed to calculate the
   * @return
   */
  def getComplexity: Long = {
    0L
  }
  /**
   * Sets the neighbors of the Players correctly and deals the Cards.
   */
  def initialize = {
    dealOut
    val winnerPlayerIndex = auction()
    val trump:Trump = players(winnerPlayerIndex).getTrump()
    trumpToOtherPlayers(players(winnerPlayerIndex + 1), trump)
    trumpToOtherPlayers(players(winnerPlayerIndex + 2), trump)
    
  //  buildGraph(winnerPlayerIndex)
  }
 def initializeTest = {
//    players(0).handCards.addCards(12L)
//    players(1).handCards.addCards(3L)
//    players(2).handCards.addCards(48L)
	 players(0).handCards.addCards(0xFL)
    players(1).handCards.addCards(1<<8|1<<9|1<<10|1<<11)
    players(2).handCards.addCards(1<<16|1<<17|1<<18|1<<19)
    val winnerPlayerIndex = 0
    val trump:Trump = players(winnerPlayerIndex).getTrump()
    trumpToOtherPlayers(players(winnerPlayerIndex + 1), trump)
    trumpToOtherPlayers(players(winnerPlayerIndex + 2), trump)
    
    buildGraph(winnerPlayerIndex)
println("Size "+graph.nodes.size)   

  }
  /**
   * the players bids for the game.
   * after the winner is calculated, the friendIndices of the players are set.
   * @return the index of the player, which won the auction. This is 0,1 or 2.
   */
  def auction(): Integer = {
    /*
     * TODO: calculate the winner and set the friendIndices
     * here we keep it simple and set the winner to player1, so we do:
     */
    players(0).friendIndex = 0
    players(1).friendIndex = 2
    players(2).friendIndex = 1
    return 0
  }
  /**
   * hands the trump to the hand of the player.
   * @param pPlayer = the player, which needs the trump.
   * @param pTrump = the trump, which was chosen by another player.
   */
  def trumpToOtherPlayers(pPlayer: Player, pTrump: Trump) = {
    pPlayer.handCards.setTrump(pTrump)
  }
/**
 * @return winner's index
 */
  def playRound(startIndex:Int):Int={
    currentTrick=ListBuffer(None,None,None,None)
    println("Player: "+startIndex)
    val firstCard=players(startIndex).getNextCard(currentTrick)
    for(i<-1 to 10){println("\n")}
    currentTrick(3)=firstCard
    currentTrick(startIndex)=firstCard
    println("Player: "+(startIndex+1))
    currentTrick((startIndex+1)%3)=players(startIndex+1).getNextCard(currentTrick)
    for(i<-1 to 10){println("\n")}
    println("Player: "+(startIndex+2))
    currentTrick((startIndex+2)%3)=players(startIndex+2).getNextCard(currentTrick)
    
    val windex=getWinner(currentTrick)
    println("WonBy: "+windex)

    tricks::=currentTrick
    println(windex)
    windex
  }

  def playGame(startIndex:Int){
    var windex=startIndex
    for(i<-1 to 10){
      windex=playRound(windex)
    }
  }
  /**
   * Calculates the winner of the given trick. The winner is given as index in the players list
   * Throws an Exception if the given trick is empty
   */
  def getWinner(pTrick:ListBuffer[Option[Int]]):Int={
    pTrick match{
      case ListBuffer(Some(a),Some(b),Some(c),Some(d)) => 
        if(compareCards(a,b)==a){
          if(compareCards(a,c)==a) 0 //c<b<a
          else 2 //b<a<c
        }
        else{
	        if(compareCards(b,c)==b) 1 //c<a<b
	        else 2 //a<b<c
        }
      case _ => throw new Exception("Current Trick seems to be empty")
    } 
  }
  
  /**
   * Compares two Cards and returns the better one depending on the current Trump
   */
  def compareCards(pCard1:Int, pCard2:Int):Int={
      trump match{
	      case Ramsch() | Grand()=> 
	        if(Card.isJack(pCard1) && Card.isJack(pCard2)) Math.max(pCard1,pCard2) // two Jacks => higher rank wins
	        else if(Card.isJack(pCard1)) pCard1 //one Jack => wins
	        else if(Card.isJack(pCard2)) pCard2 //the other Jack => wins
	        else help(-1,pCard1,pCard2)
	      case Null() => help(-1,pCard1,pCard2)
	      case Diamonds() =>help(Card.DiamondsQuotient,pCard1,pCard2)
	      case Clubs() =>help(Card.ClubsQuotient,pCard1,pCard2)
	      case Hearts() =>help(Card.HeartsQuotient,pCard1,pCard2)
	      case Spades() =>help(Card.SpadesQuotient,pCard1,pCard2)
      }
    }
  /**
   * Defines a relation between two Cards and returns the better one, depending on the given Trump
   * @return the best of two Cards
   */
  def help(trump:Int,pCard1:Int,pCard2:Int):Int={
    if(Card.getSuit(pCard1)==trump && Card.getSuit(pCard2)==trump||Card.isJack(pCard1) && Card.isJack(pCard2)){ // both trump
      if(Card.isJack(pCard1) && Card.isJack(pCard2) //both Jacks or both not
          || !Card.isJack(pCard1) && !Card.isJack(pCard2)){
        if(Card.isTen(pCard1)&& !Card.isAce(pCard2)) pCard1 //first is ten and higher
        else if(Card.isTen(pCard2)&& !Card.isAce(pCard1)) pCard2 //second is ten and higher
        else Math.max(pCard1,pCard2) //the higher rank wins
      }
      else if(Card.isJack(pCard1)) pCard1 //one is Jack => wins
      else pCard2 //if(Card.isJack(pCard2)) pCard2 //the other is Jack => wins
    }
    else if((Card.getSuit(pCard1)==trump || Card.isJack(pCard1)) 
        && (Card.getSuit(pCard2)!=trump|| !Card.isJack(pCard2))) pCard1//first is trump, the other not => trump wins
    else if((Card.getSuit(pCard2)==trump || Card.isJack(pCard2)) 
        && (Card.getSuit(pCard1)!=trump|| !Card.isJack(pCard1))) pCard2//the other is trump, the first not => trump wins
    else{ //no trump
      if(Card.getSuit(pCard1)==Card.getSuit(pCard2)){ //both suits are equal
        if(Card.isTen(pCard1)&& !Card.isAce(pCard2)) pCard1 //first is ten and higher
        else if(Card.isTen(pCard2)&& !Card.isAce(pCard1)) pCard2 //second is ten and higher
        else Math.max(pCard1,pCard2) //the higher rank wins
      }
      else{ //different suits => first wins
        pCard1
      }
    }
  }
  /*
   * a,b
   * if (trumpf a, trumpf b)
   * 		if a != bube ^ b != bube || abube^bbube
   * 			max(Rang a, Rang b)
   *     if a bube ^ b!= bube => a
   *     if a!=bube ^ b==bube => b
   * if(a trumpf, b!=trump)=> a
   * if(b trump, a!= trumpf)=>b
   * if(a !0 trumpf, b != trumpf)
   *     if(suit a != suit b) => a
   *     else
   *     	max(Rang a, rang b)
   */
  def buildGraph(startIndex:Int)={
	graph.addNode(ListBuffer())
    buildGraphRec(startIndex,ListBuffer(),0,0,0)
  }
  def buildGraphRec(startIndex:Int,parent:ListBuffer[Option[Int]],card0:Int,card1:Int,card2:Int):Int={
	  /*
	   * 
begin
	if zeile > 8 then -- Loesungsbehandlung:
		drucke Brett;
	else
		for spalte in 1..8 loop
			if Feld (zeile,spalte) nicht bedroht then
				Setze Dame auf Feld (zeile,spalte);
				probiereAbZeile(zeile+1);
				Entferne Dame von Feld (zeile,spalte);
			end if;
		end loop;
	end if;
end
	   */
	  val curTri:ListBuffer[Option[Int]]=ListBuffer(None,None,None,None)
	  parent match{
	  case ListBuffer(_,_,_,None) => 0
	  case _ =>
		  for(i<-0 to 10){
			  val firstCard=players(startIndex).getNthCard(i,curTri)
			  if(firstCard.isDefined){
//			  println("First Card: "+firstCard)
				  curTri(3)=firstCard
				  curTri(startIndex)=firstCard
				  for(j<-0 to 10){
					  val secondCard=players(startIndex+1).getNthCard(j,curTri)
					  if(secondCard.isDefined){
//						  println("Second Card: "+secondCard)
						  curTri((startIndex+1)%3)=secondCard
						  for(k<-0 to 10){
							  val thirdCard=players(startIndex+2).getNthCard(k,curTri)
							  if(thirdCard.isDefined){
//							  println("Third Card: "+thirdCard)
									curTri((startIndex+2)%3)=thirdCard
//									println(curTri)
									val clone=curTri.clone()
									graph.addNode(clone)
									graph.addEdge(parent,clone,0)
									var int=buildGraphRec(getWinner(curTri),clone,card0+1,0,0)
									players(startIndex+2).handCards.add(thirdCard.get) //put it back
//									curTri((startIndex+2)%3)=None
							  }
						  }
						  players(startIndex+1).handCards.add(secondCard.get) //put it back
//						  curTri((startIndex+1)%3)=None
					  }
				  }
				  players(startIndex).handCards.add(firstCard.get) //put it back
//				  curTri((startIndex+1)%3)=None
//				  curTri((startIndex+2)%3)=None	
//				  curTri((startIndex+3)%3)=None	
			  }
			  else{
			  	//first player cant play a card - end of game.
			  	return 3
			  }
		  }
		  return 4
	  }
//			  currentTrick=ListBuffer(None,None,None,None)
//					  val firstCard=players(startIndex).getNthCard(card0,currentTrick)
//					  currentTrick(3)=firstCard
//					  currentTrick(startIndex)=firstCard
//					  currentTrick((startIndex+1)%3)=players(startIndex+1).getNthCard(card1,currentTrick)
//					  currentTrick((startIndex+2)%3)=players(startIndex+2).getNthCard(card2,currentTrick)
//
//					  currentTrick match{
//					  case trick@ListBuffer(Some(x),Some(y),Some(z),_) => //valid => add Edge
//					  graph.addNode(currentTrick.clone())
//					  graph.addEdge(parent,currentTrick,0)
//					  buildGraphRec(getWinner(trick),trick,0,0,0) // build subgraph
//					  players(2).handCards.add(z)
//					  false
//					  case ListBuffer(Some(x),Some(y),None,_) => //2 is invalid => let +1 set another card
//					  buildGraphRec(startIndex,parent,x,y+1,0)
//					  players(1).handCards.add(y)
//					  false
//					  case ListBuffer(Some(x),None,_,_) => //1 is invalid => let +0 set another card
//					  buildGraphRec(startIndex,parent,x+1,0,0)
//					  players(0).handCards.add(x)
//					  false
//					  case ListBuffer(_,_,_,None) => //0 is invalid => end
//					  true
//			  }
	  }
 
}
