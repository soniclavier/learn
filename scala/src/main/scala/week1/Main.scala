package week1

object Main {
  
  def pascal(c:Int,r:Int):Int = {
    require(c<=r," row num should be less than or eq to col num ")
    var s = Array.ofDim[Int](r+1,r+1)
    s(0)(0) = 1
    
    for(i <- 1 to r) {
      for(j <- 0 to i) {
        if (j==0) {
            s(i)(j) = s(i-1)(0)
            print(s(i)(j)+",")
        } else if (j==i) {
          s(i)(j) = s(i-1)(j-1)
          print(s(i)(j)+",")
        } else {
          s(i)(j) = s(i-1)(j-1)+s(i-1)(j)
          print(s(i)(j)+",")
        }
        if (i == r && j==c) {
          println()
          return s(i)(j)
        }
      }
      println()
    }
    1
  }
  
  def balance(chars: List[Char],top:Int): Boolean = {
    if (chars.isEmpty) return top == 0
    val head = chars.head
    if (head == '(') {
      return balance(chars.tail,top+1)
    } else if (head == ')') {
      if (top <=0) return false
      else return balance(chars.tail,top-1)
    } else {
      return balance(chars.tail,top)
    }
    true
  }
  
  def countChange(money:Int,coins: List[Int]): Int = {
    if (money < 0) {println("path not possible");return 0}
    if (money == 0) { println("found exact"); return 1}
    if (coins.isEmpty) {println("no more coins"); return 0}
    countChange(money,coins.tail)+ countChange((money-coins.head),coins)
  }
  def main(s:Array[String]):Unit = {
    //println(pascal(4,10))
    //println(balance(List('(','(',')',')'),0))
    //println(countChange(4,List(1,2)))
    type Set = Int => Boolean
    def contains(s:Set,elem:Int): Boolean = s(elem)
        
  }
}