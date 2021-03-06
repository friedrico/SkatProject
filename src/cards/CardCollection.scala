/**
 *
 */
package cards

/**
 * A CardCollection contains between 0 and 32 Cards.
 * @author Oliver Friedrich
 */
class CardCollection(pVector:Long) {

  /**
   * The vector is the representation of the Cards. The 32 bits stand for 32 Cards and are set to 1 when the Collections contains the nth Card.
   * Initially the vector is 0 so no card is in the Collection
   */
  var vector: Long = pVector
  /**
   * Adds a (or more) Card(s) to the Collection. If you want to add more than one Card with one call you have to OR all the Cards which shall be added.
   * @param pCard the bit vector representation of the Card(s) to add - 32 bits as Long
   */
  def add(pCard: Int) = {
    vector |= 1 << pCard
  }
  def addCards(pCardVector: Long) = {
    vector |= pCardVector
  }
  /**
   * Removes a Card from the Collection.
   * @param pCard the Card to remove
   */
  def remove(pCard: Int) = {
    vector &= ~(1 << pCard)
  }
  /**
   * Tests if the given Card(s) is/are part of the Collection. If you want to test whether more than one Card are in the Collection with one call you have to OR all the Cards which shall be tested.
   * @param pCard the bit vector representation of the Card(s) to test - 32 bits as Long
   * @return true if and only if the given Card(s) is/are in the Collection
   */
  def contains(pCard: Int): Boolean = {
    (vector & 1 << pCard) != 0
  }
  
  /**
   * Overrides the toString method. It just prints the current Long as it is saved in the vector variable.
   */
  override def toString:String=vector.toString
  
  /**
   * Gives the number of Cards in the Collection.
   * @return the number of Cards in the Collection
   */
  def size: Int = {
    var x = vector
    var c = 0
    while (x != 0) {
      if (x % 2 == 1) {
        c += 1
      }
      x = x >> 1
    }
    return c
  }
  /**
   * Removes all Cards from the Collection.
   */
  def flush = {
    vector = 0
  }
  
  /**
   * Standard empty constructor which initializes the Collection with 0 so empty.
   */
  def this()=this(0L)
}
