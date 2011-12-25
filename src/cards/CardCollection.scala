/**
 *
 */
package cards

/**
 * @author Oliver Friedrich
 *
 */
class CardCollection {
  /**
 * 
 */
private var vector:Int=0
	/**
	 * @param pCard
	 */
	def add(pCard:Int)={
		vector |= 1<<pCard
	}
	/**
	 * @param pCard
	 */
	def remove(pCard:Int)={
	  vector^=1<<pCard
	}
	/**
	 * @param pCard
	 * @return
	 */
	def contains(pCard:Int):Boolean={
	  (vector & 1<<pCard)!=0
	}
	/**
	 * @return
	 */
	def size:Int={
	  0
	}
	/**
	 * 
	 */
	def flush={
	  vector=0
	}
}