/**
 *
 */
package cards

/**
 * @author Oliver Friedrich
 *
 */
class Hand extends CardCollection {

  var trumpMask = 0L

  var trumpSuit = 0

  /**
   * Sets the mask which filters every card which is not a trump card.
   */
  def setTrump(pTrump: Trump) = {
    trumpMask = pTrump.getMask
    trumpSuit = pTrump.getSuit
  }
  /**
   * @return : only the diamond cards without the jacks.
   */
  def getDiamonds(): Long = {
    if ((vector & 0xEFL) == 0L) {
      return vector
    } else {
      return (vector & 0xEFL)
    }
  }
  /**
   * @return : only the hearts cards without the jacks.
   */
  def getHearts(): Long = {
    if ((vector & 0xEF00L) == 0L) {
      return vector
    } else {
      return (vector & 0xEF00L)
    }
  }

  def getSpades(): Long = {
    if ((vector & 0xEF0000L) == 0L) {
      return vector
    } else {
      return (vector & 0xEF0000L)
    }
  }

  def getClubs(): Long = {
    if ((vector & 0xEF000000L) == 0L) {
      return vector
    } else {
      return (vector & 0xEF000000L)
    }
  }

  def filter(suit: Int): Long = {
    // pruefen, ob suit = trumpf

    if (suit == trumpSuit) {
      // return irgendwas
    }
    // kein trump
    suit match {
      // TODO : constants for cases
      case 0 => // diamond
        if ((vector & 0xEFL) == 0L) {
          return vector
        } else {
          return (vector & 0xEFL)
        }
      case 1 => // heart
        if ((vector & 0xEF00L) == 0L) {
          return vector
        } else {
          return (vector & 0xEF00L)
        }
      case 2 => // spades
        if ((vector & 0xEF0000L) == 0L) {
          return vector
        } else {
          return (vector & 0xEF0000L)
        }
      case 3 => // clubs
        if ((vector & 0xEF000000L) == 0L) {
          return vector
        } else {
          return (vector & 0xEF000000L)
        }
    }
    return -1
  }

}