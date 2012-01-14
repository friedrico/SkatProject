/**
 *
 */
package game
import cards.Skat
import cards.Trick
import cards.Trump
import cards.Ramsch
import scala.xml.XML

/**
 * @author Oliver Friedrich
 *
 */
class Game {
	/**
	 * The Player who plays the first Card.
	 */
	val players=List(new Player(0),new Player(2), new Player(1))

	/**
	 * The two Cards which lent the name to the Game.
	 */
	val skat=new Skat
	
	/**
	 * The Trick which represents the current Cards on the table and where every Player has to play his Cards
	 */
	val currentTrick=new Trick()
		
	/**
	 * The current Trump of the Game.
	 */
	val trump=getTrump

	initialize

	/**
	 * Writes the current environment (which means the current state of the game) to the given file. It creates a XML file which represents the current state of the Game. So which Player has which Cards and who has to play the next Card. The syntax of the XML file is really easy. It shall describe the game readable and does not save it the most efficient way.
	 * @param pPath the path where to create the XML file
	 */
	def saveState(pPath:String)={
		val saveXml = 
				<game>
					<player1>
					<hand>
					</hand>
					<madeTricks>
						<trick>
						</trick>
					</madeTricks>
					<points>
						<own>
							{}
						</own>
						<opposing>
						</opposing>
					</points>
					<opposition>
						<left>
						</left>
						<right>
						</right>
					</opposition>
					</player1>
					<player2>
					</player2>
					<player3>
					</player3>
					<currentRound>
						<player>
						</player>
						<trump>
						</trump>
						<trick>
						</trick>
						<skat>
						</skat>
					</currentRound>
				</game>
				XML.save(pPath,saveXml)
	}
	/**
	 * Reads an environment (which means the current state of the game) from the given file. It is the complement of saveState and so saveState(x);loadState(x) will not change anything.
	 * @param pPath the file to read
	 */
	def readState(pPath:String)={
		val readXml=XML.load(pPath)
	}
	/**
	 * Deals the Cards. Will create a Skat and give 10 Cards to every Player
	 */
	def dealOut={
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
	    skat.add(33792L)
	}
	/**
	 * Returns the number of steps needed to calculate the 
	 * @return
	 */
	def getComplexity:Long={
			0L
	}
	/**
	 * Sets the neighbors of the Players correctly and deals the Cards.
	 */
	def initialize={
			dealOut
	}
	/**
	 * Gives the current Trump.
	 * @return the Trump which was chosen by the winner of the betting phase
	 */
	def getTrump():Trump={
			Ramsch()
	}
}