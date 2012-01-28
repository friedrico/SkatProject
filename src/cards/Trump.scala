/**
 *
 */
package cards

/**
 * @author Oliver Friedrich
 *
 */
abstract case class Trump {
  def getMask: Long
  def getSuit: Int
}

/**
 * @author Oliver Friedrich
 *
 */
case class Diamonds extends Trump {
  def getMask(): Long = {

    // 000000FF = Diamonds
    // 10101010 = Jacks
    // 101010FF = Diamonds | Jacks

    // 0000 0000 0000 0000 0000 0000 1111 1111 = 255L = Diamonds
    // 0001 0000 0001 0000 0001 0000 0001 0000 = 269488144L = Jacks
    // 0001 0000 0001 0000 0001 0000 1111 1111 = 269488383L = Diamonds | Jacks
    return 0x101010FFL
  }

  def getSuit(): Int = {
    return 0
  }
  override def toString = "Diamonds"
}
/**
 * @author Oliver Friedrich
 *
 */
case class Hearts extends Trump {
  def getMask(): Long = {

    // 0000FF00 = Hearts
    // 10101010 = Jacks
    // 1010FF10 = Hearts | Jacks

    // 0000 0000 0000 0000 1111 1111 0000 0000 = 65280L = Hearts
    // 0001 0000 0001 0000 0001 0000 0001 0000 = 269488144L = Jacks
    // 0001 0000 0001 0000 1111 1111 0001 0000 = 269549328L = Hearts | Jacks
    return 0x1010FF10L
  }

  def getSuit(): Int = {
    return 1
  }
  override def toString = "Hearts"
}
/**
 * @author Oliver Friedrich
 *
 */
case class Spades extends Trump {
  def getMask(): Long = {
    // 0000 0000 1111 1111 0000 0000 0000 0000 = 16711680L = Spades
    // 0001 0000 0001 0000 0001 0000 0001 0000 = 269488144L = Jacks
    // 0001 0000 1111 1111 0001 0000 0001 0000 = 285151248L = Spades + Jacks
    return 285151248L
  }

  def getSuit(): Int = {
    2
  }

  override def toString = "Spades"
}
/**
 * @author Oliver Friedrich
 *
 */
case class Clubs extends Trump {
  def getMask(): Long = {

    // FF000000 = Clubs
    // 10101010 = Jacks
    // FF101010 = Clubs | Jacks

    // 1111 1111 0000 0000 0000 0000 0000 0000 = 4278190080L = Clubs
    // 0001 0000 0001 0000 0001 0000 0001 0000 = 269488144L = Jacks
    // 1111 1111 0001 0000 0001 0000 0001 0000 = 4279242768L = Clubs | Jacks
    return 0xFF101010L
  }

  def getSuit(): Int = {
    return 3
  }

  override def toString = "Clubs"
}
/**
 * @author Oliver Friedrich
 *
 */
case class Null extends Trump {
  def getMask(): Long = {
    return 0L
  }

  def getSuit(): Int = {
    return 5
  }

  override def toString = "Null"
}
/**
 * @author Oliver Friedrich
 *
 */
case class Grand extends Trump {
  def getMask(): Long = {
    // 10101010 = Jacks
    // 00010000000100000001000000010000 = 269488144L = Jacks 
    return 0x10101010L
  }

  def getSuit(): Int = {
    return 4
  }

  override def toString = "Grand"
}
/**
 * @author Oliver Friedrich
 *
 */
case class Ramsch extends Trump {
  def getMask(): Long = {
    // 10101010 = Jacks
    // 00010000000100000001000000010000 = 269488144L = Jacks
    return 0x10101010L
  }

  def getSuit(): Int = {
    return 6
  }

  override def toString = "Ramsch"
}