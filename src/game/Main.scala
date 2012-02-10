package game
import cards.Card
import scala.collection.mutable.ListBuffer
import java.sql.Time
import java.util.Date
import java.util.Calendar
object Main {

  def main(args:Array[String]){
    
    val c:Game= new Game
    val start=Calendar.getInstance().getTimeInMillis()
    //for(i<-1 to 300){
    c.initializeTest
   
   // c.playGame(0)
//    makeTricksFancy(c.tricks)
   // }
    
    println(Calendar.getInstance().getTimeInMillis()-start)
  }
  
  def makeTricksFancy(a:List[ListBuffer[Option[Int]]])={
  	a.reverse.foreach({trick=>
  	trick match{
  		case ListBuffer(Some(a),Some(b),Some(c),Some(d)) => println("("+Card.toString(a)+","+Card.toString(b)+","+Card.toString(c)+","+Card.toString(d)+")")
  	}
  	})
  }
}