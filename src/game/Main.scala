package game
import cards.Card
object Main {

  def main(args:Array[String]){
    
    val c:Game= new Game
    c.initialize
    c.playGame(0)
    println(c.tricks)


  }
}