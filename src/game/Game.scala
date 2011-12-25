/**
 *
 */
package game
import cards.Skat
import cards.Trick
import cards.Trump
import cards.Ramsch

/**
 * @author Oliver Friedrich
 *
 */
class Game {

	/**
	 * The Player who plays the first Card.
	 */
	val forehand=new Player
	/**
	 * The Player who plays the second Card.
	 */
	val middlehand=new Player(forehand)
	/**
	 * The Player who plays the third Card.
	 */
	val rearhand=new Player(forehand,middlehand)
	
	/**
	 * The two Cards which lent the name to the Game.
	 */
	val skat=new Skat
	/**
	 * The Trick which represents the current Cards on the table and where every Player has to play his Cards
	 */
	val currentTrick=new Trick(forehand,middlehand,rearhand)
	/**
	 * The current Trump of the Game.
	 */
	val trump=getTrump
	
	initialize
	  
	/**
	 * Writes the current distribution to the given file. It creates a XML file which represents the current state of the Game. So which Player has which Cards and who has to play the next Card.
	 * @param pPath the path where to create the XML file
	 */
	def saveState(pPath:String)={
	    
	}
	/**
	 * Reads a distribution from the given file. It is the complement of saveState and so saveState(x);loadState(x) will not change anything.
	 * @param pPath the file to read
	 */
	def readState(pPath:String)={
	    
	}
	/**
	 * Deals the Cards. Will create a Skat and give 10 Cards to every Player
	 */
	def dealOut={
	    
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
	    forehand.leftNeighbor=middlehand
		forehand.rightNeighbor=rearhand
		middlehand.leftNeighbor=rearhand
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