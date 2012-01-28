package game
import cards.Card
object Main {

  def main(args:Array[String]){
    
    val c:Game= new Game
    c.initialize
    c.players(0).getNextCard((0,0,0,4))


  }
}