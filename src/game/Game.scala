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
 * 
 */
val forehand=new Player
  /**
 * 
 */
val middlehand=new Player(forehand)
  /**
 * 
 */
val rearhand=new Player(forehand,middlehand)

  /**
 * 
 */
val skat=new Skat
  /**
 * 
 */
val currentTrick=new Trick(forehand,middlehand,rearhand)
  /**
 * 
 */
val trump=getTrump

  initialize
  
  /**
 * @param pPath
 */
def saveState(pPath:String)={
    
  }
  /**
 * @param pPath
 */
def readState(pPath:String)={
    
  }
  /**
 * 
 */
def dealOut={
    
  }
  /**
 * @return
 */
def getComplexity:Long={
    0L
  }
  /**
 * 
 */
def initialize={
    forehand.leftNeighbor=middlehand
	forehand.rightNeighbor=rearhand
	middlehand.leftNeighbor=rearhand
	dealOut
  }
  /**
 * @return
 */
/**
 * @return
 */
def getTrump():Trump={
    Ramsch()
  }
}