package week4.idealized

object BooleanTest {
  
  def main(args: Array[String]): Unit = {
    val t = True
    t.ifThenElse(println("true"), println("false"))
    val f = False
    f.ifThenElse(println("true"), println("false"))
    
    val tandf = t&&f
    println(t<f)
    println(f<t)
    println(t<t)
    println(f<f)
  }
}