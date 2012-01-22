package game
object Main {

  def main(args:Array[String]){
    
    "String" match{
      case "String" => println(true)
      case _=> println(false)
    }
    
    val c:Game= new Game
    val c2:Game= new Game
    c.initialize
    c.saveState("Test2")
   // c2.readState("Test2")
    c2.saveState("Test3")
  }
}