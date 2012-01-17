/**
 *
 */
package game
import cards.Skat
import cards.Trump
import cards._
import scala.xml.XML
import cards.Hand
import scala.xml.Elem

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
  var skat: (Long, Long) = (0L, 0L)
  // deal out the cards to the skat and to the players.
  initialize
  /**
   * The Trick which represents the current Cards on the table and where every Player has to play his Cards
   */
  var currentTrick: (Long, Long, Long) = (0L, 0L, 0L)
  /**
   * The current Trump of the Game.
   */
  var trump: Trump = Ramsch()

  val tricks = List[(Int, Int, Int)]()

  /**
   * Writes the current environment (which means the current state of the game) to the given file. It creates a XML file which represents the current state of the Game. So which Player has which Cards and who has to play the next Card. The syntax of the XML file is really easy. It shall describe the game readable and does not save it the most efficient way.
   * @param pPath the path where to create the XML file
   */
  def saveState(pPath: String): Elem = {
    val saveXml =
      <game>
        <currentRound>
          <trump>
            { trump }
          </trump>
          <trick>
            { currentTrick._1 }
            ,
            { currentTrick._2 }
            ,
            { currentTrick._3 }
          </trick>
          <skat>
            { skat._1 }
            { skat._2 }
          </skat>
        </currentRound>
        <initialDistribution>
          {
            players(0).toXML
            players(1).toXML
            players(2).toXML
          }
        </initialDistribution>
        <tricks>
          {
            for (trick <- tricks) { <trick>{ trick._1 },{ trick._2 },{ trick._3 }</trick> }
          }
        </tricks>
        <serializedTree>
        </serializedTree>
      </game>
    XML.save(pPath, saveXml)
    saveXml
  }
  /**
   * Reads an environment (which means the current state of the game) from the given file. It is the complement of saveState and so saveState(x);loadState(x) will not change anything.
   * @param pPath the file to read
   */
  def readState(pPath: String) = {
    val readXml = XML.load(pPath)

    skat = ((readXml \ "skat").text.split(",")(0).toLong, (readXml \ "skat").text.split(",")(1).toLong)

    currentTrick = ((readXml \ "trick").text.split(",")(0).toLong,
      (readXml \ "trick").text.split(",")(1).toLong,
      (readXml \ "trick").text.split(",")(2).toLong)

    trump = (readXml \ "trump").text match {
      case "Hearts" => Hearts()
      case "Spades" => Spades()
      case "Ramsch" => Ramsch()
      case "Clubs" => Clubs()
      case "Null" => Null()
      case "Grand" => Grand()
      case "Diamonds" => Diamonds()
    }
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

    players(0).handCards.add(3011576193L)
    players(1).handCards.add(1142575622L)
    players(2).handCards.add(140781688L)
    skat = (10L, 15L)
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
    pPlayer.handCards.setTrumpMask(pTrump)
  }

}