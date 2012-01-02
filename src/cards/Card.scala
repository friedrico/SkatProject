/**
 *
 */
package cards

/**
 * Card is a object which offers the opportunity to get the Cards, Suits and Ranks by better named constants.
 * Therefore there are some kinds of constants are defined:
 * 32 Cards named SuitRank which returns the Int which represents the Card.
 * 4 Suits named SuitQuotient which returns the result of Card/8. So Diamonds_/8=DiamondsQuotient
 * 8 Ranks named RankModulo which returns the result of Card%8. So _Seven%8=SevenModulo
 * @author Oliver Friedrich
 */
object Card {

  /*
	val DiamondsSeven=0
	val DiamondsEight=1
	val DiamondsNine=2
	val DiamondsTen=4
	val DiamondsJack=8
	val DiamondsQueen=16
	val DiamondsKing=32
	val DiamondsAce=64
	
	val HeartsSeven=128
	val HeartsEight=256
	val HeartsNine=512
	val HeartsTen=1024
	val HeartsJack=2048
	val HeartsQueen=4096
	val HeartsKing=8192
	val HeartsAce=16384
	
	val SpadesSeven=32768
	val SpadesEight=65536
	val SpadesNine=131072
	val SpadesTen=262144
	val SpadesJack=524288
	val SpadesQueen=1048576
	val SpadesKing=2097152
	val SpadesAce=4194304
	
	val ClubsSeven=8388608
	val ClubsEight=16777216
	val ClubsNine=33554432
	val ClubsTen=67108864
	val ClubsJack=134217728
	val ClubsQueen=268435456
	val ClubsKing=536870912
	val ClubsAce=1073741824
	
	*/

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
  def getSuit(pCard:Long):Integer= {
    var suit = 0
    var card = pCard
    while(card != 1) {
      card = card >> 1
      suit+=1
    }
    return (suit / 8);
  }
  /**
   * returns the rank of a card.
   * Thats a value between 0 and 7.
   */
  def getRank(pCard:Long):Integer= {
    var rank = 0
    var card = pCard
    while(card != 1) {
      card = card >> 1
      rank+=1
    }
    return (rank % 8);
  }
  
  
  
  
}