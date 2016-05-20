package week2

object Main {
  type Set = Int => Boolean
  def contains(s: Set, elem: Int): Boolean = s(elem)
  
  def singletonSet(elem: Int): Set = {
    (x:Int) => (x==elem)
  }
  
  def union(s: Set, t: Set): Set = {
    (x:Int) => s(x) || t(x)
  }
  
  def intersect(s: Set, t: Set): Set = {
    (x:Int) => (s(x) && t(x))
  }
  
  def diff(s: Set, t: Set): Set = {
    (x:Int) => (s(x) && !t(x))
  }
  
  def filter(s: Set, p: Int => Boolean): Set = {
    (x:Int) => s(x) && p(x)
  }
  
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
     if (contains(s,a) && !p(a)) false  
     else if (a>1000) true
     else iter(a+1)
   }
   iter(-1000)
  }
  
  //1. forall should return false when some p is true, 
  //therefore we have to negate the p (because the originally forall returned false if any p is false)
  //forall returned false for 1 element using !p => there exists a element for of s for which p is true (negate the final result)
  //2. if the function forall is returned true implies that the function !p is true for all elements
  //=> for all the elements p is false => (negate the final result)
  def exists(s: Set, p: Int => Boolean): Boolean =  {
    //to negate the function p, we have to apply the function on some element (x) and negate the result
    //http://stackoverflow.com/questions/12681616/how-to-use-refer-to-the-negation-of-a-boolean-function-in-scala
    !forall(s,x => !p(x))
  }
  
  
  //set is a function of the form Int => Boolean
  def map(s: Set, f: Int => Int): Set = {
   x:Int => exists(s, (y:Int)=>f(y)==x)
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