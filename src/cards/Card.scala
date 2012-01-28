/**
 *
 */
package cards

/**
 * Card is a object which offers the opportunity to get the Cards, Suits and Ranks by better named constants.
 * Therefore there are some kinds of constants are defined:
 * 32 Cards named SuitRank which returns the ong which represents the Card.
 * 4 Suits named SuitQuotient which returns the result of log2(Card) / 8. So Diamonds_/8=DiamondsQuotient
 * 8 Ranks named RankModulo which returns the result of log2(Card) % 8. So log2(_Seven) % 8 = SevenModulo
 * @author Oliver Friedrich
 */
object Card {
  
  val DiamondsSeven = 0
  val DiamondsEight = 1
  val DiamondsNine = 2
  val DiamondsTen = 3
  val DiamondsJack = 4
  val DiamondsQueen = 5
  val DiamondsKing = 6
  val DiamondsAce = 7

  val HeartsSeven = 8
  val HeartsEight = 9
  val HeartsNine = 10
  val HeartsTen = 11
  val HeartsJack = 12
  val HeartsQueen = 13
  val HeartsKing = 14
  val HeartsAce = 15

  val SpadesSeven = 16
  val SpadesEight = 17
  val SpadesNine = 18
  val SpadesTen = 19
  val SpadesJack = 20
  val SpadesQueen = 21
  val SpadesKing = 22
  val SpadesAce = 23

  val ClubsSeven = 24
  val ClubsEight = 25
  val ClubsNine = 26
  val ClubsTen = 27
  val ClubsJack = 28
  val ClubsQueen = 29
  val ClubsKing = 30
  val ClubsAce = 31
  
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
	* Thats a value between 0 and 3.
	* @return the suit of a card.
   */
  def getSuit(pCard:Int):Int= {
    pCard / 8
  }
  /**
   * Thats a value between 0 and 7.
   * @return the rank of a card.
   */
  def getRank(pCard:Int):Int= {
    pCard % 8
  }

  def toString(pCard:Int):String={
    List(0x2666.toChar+"7",
         0x2666.toChar+"8",
         0x2666.toChar+"9",
         0x2666.toChar+"10",
         0x2666.toChar+"J",
         0x2666.toChar+"Q",
         0x2666.toChar+"K",
         0x2666.toChar+"A",
         
         0x2665.toChar+"7",
         0x2665.toChar+"8",
         0x2665.toChar+"9",
         0x2665.toChar+"10",
         0x2665.toChar+"J",
         0x2665.toChar+"Q",
         0x2665.toChar+"K",
         0x2665.toChar+"A",
         
         0x2660.toChar+"7",
         0x2660.toChar+"8",
         0x2660.toChar+"9",
         0x2660.toChar+"10",
         0x2660.toChar+"J",
         0x2660.toChar+"Q",
         0x2660.toChar+"K",
         0x2660.toChar+"A",
         
         0x2663.toChar+"7",
         0x2663.toChar+"8",
         0x2663.toChar+"9",
         0x2663.toChar+"10",
         0x2663.toChar+"J",
         0x2663.toChar+"Q",
         0x2663.toChar+"K",
         0x2663.toChar+"A"
         )(pCard)
  }
  
  
  
  
  
}