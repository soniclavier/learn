package week4
import week4._
object week4worksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
	def nth[T](n:Int,l: List[T]) : T =
		if (l.isEmpty) throw new IndexOutOfBoundsException
		else if (n == 0) l.head
		else nth(n-1,l.tail)              //> nth: [T](n: Int, l: week4.List[T])T
		
	val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> list  : week4.Cons[Int] = week4.Cons@2ed94a8b
 	nth(2,list)                               //> res0: Int = 3
	nth(-1,list)                              //> java.lang.IndexOutOfBoundsException
                                                  //| 	at week4.week4worksheet$$anonfun$main$1.nth$1(week4.week4worksheet.scala
                                                  //| :7)
                                                  //| 	at week4.week4worksheet$$anonfun$main$1.apply$mcV$sp(week4.week4workshee
                                                  //| t.scala:13)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week4.week4worksheet$.main(week4.week4worksheet.scala:3)
                                                  //| 	at week4.week4worksheet.main(week4.week4worksheet.scala)
                                                
}