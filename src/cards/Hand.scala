/**
 *
 */
package cards

/**
 * @author Oliver Friedrich
 *
 */
class Hand(pVector:Long) extends CardCollection(pVector) {

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
  def filter(suit: Int): Hand = {
    // the card is a trump card.
    if (suit == trumpSuit) {
      if ((vector & trumpMask) == 0L) {
         this
      }
      	new Hand(vector & trumpMask)
    }

    // the card is not a trump card.
    suit match {
      // TODO : constants for cases
      case 0 => // diamond
        if ((vector & 0x000000EFL) == 0L) this else new Hand(vector & 0x000000EFL)
      case 1 => // heart
        if ((vector & 0x0000EF00L) == 0L) this else new Hand(vector & 0x0000EF00L)
      case 2 => // spades
        if ((vector & 0x00EF0000L) == 0L) this else new Hand(vector & 0x00EF0000L)
      case 3 => // clubs
        if ((vector & 0xEF000000L) == 0L) this else new Hand(vector & 0xEF000000L)
      case _ => throw new Exception("Not implemented yet")
    }

  }
def this()=this(0)
}