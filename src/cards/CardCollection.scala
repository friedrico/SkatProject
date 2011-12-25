/**
 *
 */
package cards

/**
 * A CardCollection contains between 0 and 32 Cards.
 * @author Oliver Friedrich
 */
class CardCollection {
/**
 * The vector is the representation of the Cards. The 32 bits stand for 32 Cards and are set to 1 when the Collections contains the nth Card.
 * Initially the vector is 0 so no card is in the Collection
 */
private var vector:Int=0
	/**
	 * Adds a (or more) Card(s) to the Collection. If you want to add more than one Card with one call you have to OR all the Cards which shall be added.
	 * @param pCard the bit vector representation of the Card(s) to add - 32 bits as Int
	 */
	def add(pCard:Int)={
		vector |= 1<<pCard
	}
	/**
	 * Removes a (or more) Card(s) from the Collection. If you want to remove more than one Card with one call you have to OR all the Cards which shall be removed.
	 * @param pCard the bit vector representation of the Card(s) to remove - 32 bits as Int
	 */
	def remove(pCard:Int)={
	  vector^=1<<pCard
	}
	/**
	 * Tests if the given Card(s) is/are part of the Collection. If you want to test whether more than one Card are in the Collection with one call you have to OR all the Cards which shall be tested.
	 * @param pCard the bit vector representation of the Card(s) to test - 32 bits as Int
	 * @return true if and only if the given Card(s) is/are in the Collection
	 */
	def contains(pCard:Int):Boolean={
	  (vector & 1<<pCard)!=0
	}
	/**
	 * Gives the number of Cards in the Collection.
	 * @return the number of Cards in the Collection
	 */
	def size:Int={
	  0
	}
	/**
	 * Removes all Cards from the Collection.
	 */
	def flush={
	  vector=0
	}
}