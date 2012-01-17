package game
object Main {

  def main(args:Array[String]){
    println("Test")
    val c:Game= new Game
    println(c.saveState("Test2"))    
  }
}