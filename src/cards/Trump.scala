/**
 *
 */
package cards

/**
 * @author Oliver Friedrich
 *
 */
abstract case class Trump {
  def getMask:Long
}

/**
 * @author Oliver Friedrich
 *
 */
case class Diamonds extends Trump {
  def getMask():Long= {
    // 00000000000000000000000011111111	= 255L = Diamonds
    // 00010000000100000001000000010000 = 269488144L = Jacks
    return 269488399L
  }
  override def toString="Diamonds"

}
/**
 * @author Oliver Friedrich
 *
 */
case class Hearts extends Trump {
  def getMask():Long={
    // 00000000000000001111111100000000	= 65280L = Hearts
    // 00010000000100000001000000010000 = 269488144L = Jacks
    return 269553424L
  }
  override def toString="Hearts"
}
/**
 * @author Oliver Friedrich
 *
 */
case class Spades extends Trump {
  def getMask():Long={
    // 00000000111111110000000000000000	= 16711680L = Spades
    // 00010000000100000001000000010000 = 269488144L = Jacks
    return 286199824L
  }
  override def toString="Spades"
}
/**
 * @author Oliver Friedrich
 *
 */
case class Clubs extends Trump {
  def getMask():Long={
    // 11111111000000000000000000000000	= 4278190080L = Clubs
    // 00010000000100000001000000010000 = 269488144L = Jacks
    
    return 4547678224L
  }
  override def toString="Clubs"
}
/**
 * @author Oliver Friedrich
 *
 */
case class Null extends Trump {
  def getMask():Long={
    return 0L
  }
  override def toString="Null"
}
/**
 * @author Oliver Friedrich
 *
 */
case class Grand extends Trump {
  def getMask():Long={
    // 00010000000100000001000000010000 = 269488144L = Jacks 
    return 269488144L
  }
  override def toString="Grand"
}
/**
 * @author Oliver Friedrich
 *
 */
case class Ramsch extends Trump {
  def getMask():Long={
    // 00010000000100000001000000010000 = 269488144L = Jacks
    return 269488144L
  }
  override def toString="Ramsch"
}