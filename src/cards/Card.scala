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
	
	val DiamondsQuotient=0
	val HeartsQuotient=1
	val SpadesQuotient=2
	val ClubsQuotient=3
	
	val SevenModulo=0
	val EightModulo=1
	val NineModulo=2
	val TenModulo=3
	val JackModulo=4
	val QueenModulo=5
	val KingModulo=6
	val AceModulo=7
}