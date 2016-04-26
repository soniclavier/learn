package week2

object Main {
  type Set = Int => Boolean
  def contains(s: Set, elem: Int): Boolean = s(elem)
  
  def singletonSet(elem: Int): Set = {
    (x:Int) => x == elem
  }
  def union(s: Set, t: Set): Set = {
    (x:Int) => (s(x) || t(x))
  }
  
  def intersect(s: Set, t: Set): Set = {
    (x:Int) => (s(x) && t(x))
  }
  
  def diff(s: Set, t: Set): Set = {
    (x:Int) => (s(x) && !t(x))
  }
  
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
     if (contains(s,a)) p(a)
     else if (???) ???
     else iter(???)
   }
   iter(???)
  }
  
  
  def main(args:Array[String]):Unit = {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s = union(s1,s2)
    println("union")
    println(contains(s,1))
    println(contains(s,2))
    println(contains(s,3))
    
    val s3 = singletonSet(2)
    val s4 = singletonSet(3)
    val s5 = union(s3,s4)
    
    val interSet = intersect(s,s5)
    val difSet = diff(s,s5)
    println("intersect")
    println(contains(interSet,1))
    println(contains(interSet,2))
    println("diff")
    println(contains(difSet,2))
    println(contains(difSet,3))
    println(contains(difSet,1))
    
    
    
  }
  
  
   
}