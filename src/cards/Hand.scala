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
   * @param : the suit of a card.
   * @return : the cards, which would match with this card.
   */
  def filter(suit: Int): Long = {
    // the card is a trump card.
    if (suit == trumpSuit) {
      if ((vector & trumpMask) == 0L) {
        return vector
      }
      return vector & trumpMask
    }

    // the card is not a trump card.
    suit match {
      // TODO : constants for cases
      case 0 => // diamond
        if ((vector & 0xEFL) == 0L) {
          return vector
        }
        return (vector & 0xEFL)
      case 1 => // heart
        if ((vector & 0xEF00L) == 0L) {
          return vector
        }
        return (vector & 0xEF00L)
      case 2 => // spades
        if ((vector & 0xEF0000L) == 0L) {
          return vector
        }
        return (vector & 0xEF0000L)
      case 3 => // clubs
        if ((vector & 0xEF000000L) == 0L) {
          return vector
        }
        return (vector & 0xEF000000L)
    }
    // not implemented, so we return exit_failure
    return -1
  }

}