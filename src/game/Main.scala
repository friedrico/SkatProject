package game
object Main {

  def main(args:Array[String]){
    
    "String" match{
      case "String" => println(true)
      case _=> println(false)
    }
    
    val c:Game= new Game
    c.saveState("Test2")
    c.readState("Test2")
    c.saveState("Test3")
  }
}