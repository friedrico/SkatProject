/**
 *
 */
package cards

/**
 * Card is a object which offers the opportunity to get the Cards, Suits and Ranks by better named constants.
 * Therefore there are some kinds of constants are defined:
 * 32 Cards named SuitRank which returns the Long which represents the Card.
 * 4 Suits named SuitQuotient which returns the result of log2(Card) / 8. So Diamonds_/8=DiamondsQuotient
 * 8 Ranks named RankModulo which returns the result of log2(Card) % 8. So log2(_Seven) % 8 = SevenModulo
 * @author Oliver Friedrich
 */
object Card {

  val DiamondsSeven = 1L // 0
  val DiamondsEight = 2L
  val DiamondsNine = 4L
  val DiamondsTen = 8L
  val DiamondsJack = 16L
  val DiamondsQueen = 32L
  val DiamondsKing = 64L
  val DiamondsAce = 128L // 7

  val HeartsSeven = 256L // 8
  val HeartsEight = 512L
  val HeartsNine = 1024L
  val HeartsTen = 2048L
  val HeartsJack = 4096L
  val HeartsQueen = 8192L
  val HeartsKing = 16384L
  val HeartsAce = 32768L // 15

  val SpadesSeven = 65536L // 16
  val SpadesEight = 131072L
  val SpadesNine = 262144L
  val SpadesTen = 524288L
  val SpadesJack = 1048576L
  val SpadesQueen = 2097152L
  val SpadesKing = 4194304L
  val SpadesAce = 8388608L // 23

  val ClubsSeven = 16777216L // 24
  val ClubsEight = 33554432L
  val ClubsNine = 67108864L
  val ClubsTen = 134217728L
  val ClubsJack = 268435456L
  val ClubsQueen = 536870912L
  val ClubsKing = 1073741824L
  val ClubsAce = 2147483648L // 31

  val DiamondsQuotient = 0
  val HeartsQuotient = 1
  val SpadesQuotient = 2
  val ClubsQuotient = 3

  val SevenModulo = 0
  val EightModulo = 1
  val NineModulo = 2
  val TenModulo = 3
  val JackModulo = 4
  val QueenModulo = 5
  val KingModulo = 6
  val AceModulo = 7
  
  /**
   * returns the suit of a card.
   * Thats a value between 0 and 3.
   */
  def getSuit(pCard:Long):Long= {
    return log2(pCard) / 8
  }
  /**
   * returns the rank of a card.
   * Thats a value between 0 and 7.
   */
  def getRank(pCard:Long):Long= {
    return log2(pCard) % 8
  }
  
  /**
   * this function calculates the logarithm base 2.
   * @param pNumber = The number, which satisfies the following condition: pNumber = 2^return
   * @return = the number, which satisfies the following condition: return = log2(pNumber)
   * this function is just for numbers with power of two, so pNumber can be 1,2,4,8,...
   * the highest value of pNumber is 2147483648.
   */
  def log2(pNumber:Long):Long= {
    var y = 0L
    var lowerBound = 0
    var upperBound = 31
    
    while (y != 1) {
      y = pNumber >> (lowerBound + upperBound) / 2
      if (y > 1) {
        lowerBound = ((lowerBound + upperBound) / 2) + 1
      }
      if (y == 0) {
        upperBound = ((lowerBound + upperBound) / 2) - 1
      }
    }
    return ((lowerBound + upperBound) / 2)
  }
  
  
  
  
  
  
  
}