package course2.week1

object week1 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
	
val integers = new Generator[Int] {
	def generate = scala.util.Random.nextInt()
}                                                 //> integers  : course2.week1.week1.Generator[Int] = course2.week1.week1$$anonfu
                                                  //| n$main$1$$anon$1@73f792cf
val booleans = integers.map(_ >= 0)               //> booleans  : course2.week1.week1.Generator[Boolean] = course2.week1.week1$$an
                                                  //| onfun$main$1$Generator$1$$anon$4@3b81a1bc

def pairs[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T,U)] {
	def generate = (t.generate, u.generate)
}                                                 //> pairs: [T, U](t: course2.week1.week1.Generator[T], u: course2.week1.week1.Ge
                                                  //| nerator[U])course2.week1.week1.Generator[(T, U)]
def single[T](x: T): Generator[T] = new Generator[T] {
	def generate = x
}                                                 //> single: [T](x: T)course2.week1.week1.Generator[T]

def choose(lo: Int, hi: Int): Generator[Int] = {
	for (x <- integers) yield lo + x % (hi-lo)
}                                                 //> choose: (lo: Int, hi: Int)course2.week1.week1.Generator[Int]

def oneOf[T](xs: T*): Generator[T] = {
	for(idx <- choose(0, xs.length)) yield xs(idx)
}                                                 //> oneOf: [T](xs: T*)course2.week1.week1.Generator[T]
	
	
def leaves: Generator[Leaf] ={
	for (x <- integers) yield Leaf(x)
}                                                 //> leaves: => course2.week1.week1.Generator[course2.week1.week1.Leaf]

def inners: Generator[Inner] = for {
		l <- trees
		r <- trees
} yield Inner(l,r)                                //> inners: => course2.week1.week1.Generator[course2.week1.week1.Inner]

def trees: Generator[Tree] = for {
	isLeaf <- booleans
	tree <- if (isLeaf) leaves else inners
} yield tree                                      //> trees: => course2.week1.week1.Generator[course2.week1.week1.Tree]
trait Generator[+T] {
	self => def generate: T
	
	def map[S](f: T => S): Generator[S] = new Generator[S] {
		def generate = f(self.generate)
	}
	
	def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
		def generate = f(self.generate).generate
	}
}

def emptyLists = single(Nil)                      //> emptyLists: => course2.week1.week1.Generator[scala.collection.immutable.Nil
                                                  //| .type]
def nonEmptyLists = for {
	head <- integers
	tail <- lists
} yield head :: tail                              //> nonEmptyLists: => course2.week1.week1.Generator[List[Int]]
def lists: Generator[List[Int]] = for {
	cutoff <- booleans
	list <- if (cutoff) emptyLists else nonEmptyLists
} yield list                                      //> lists: => course2.week1.week1.Generator[List[Int]]
def test[T](r: Generator[T], noTimes: Int = 100)(test: T => Boolean) {
	for(_ <- 0 until noTimes) {
		val value = r.generate
		assert(test(value), "Test failed for: "+value)
	}
}                                                 //> test: [T](r: course2.week1.week1.Generator[T], noTimes: Int)(test: T => Boo
                                                  //| lean)Unit
def partialfun = test(pairs(lists,lists)) _       //> partialfun: => (((List[Int], List[Int])) => Boolean) => Unit
partialfun({
	case (xs, ys) => (xs ++ ys).length > xs.length
})                                                //> java.lang.AssertionError: assertion failed: Test failed for: (List(-2044065
                                                  //| 382, -663651514, -4117687, 1924024172, -147338511),List())
                                                  //| 	at scala.Predef$.assert(Predef.scala:179)
                                                  //| 	at course2.week1.week1$$anonfun$main$1$$anonfun$course2$week1$week1$$ano
                                                  //| nfun$$test$1$1.apply$mcVI$sp(course2.week1.week1.scala:64)
                                                  //| 	at scala.collection.immutable.Range.foreach$mVc$sp(Range.scala:141)
                                                  //| 	at course2.week1.week1$$anonfun$main$1.course2$week1$week1$$anonfun$$tes
                                                  //| t$1(course2.week1.week1.scala:62)
                                                  //| 	at course2.week1.week1$$anonfun$main$1$$anonfun$partialfun$1$1.apply(cou
                                                  //| rse2.week1.week1.scala:67)
                                                  //| 	at course2.week1.week1$$anonfun$main$1$$anonfun$partialfun$1$1.apply(cou
                                                  //| rse2.week1.week1.scala:67)
                                                  //| 	at course2.week1.week1$$anonfun$main$1.apply$mcV$sp(course2.week1.week1.
                                                  //| scala:68)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.wor
                                                  //| Output exceeds cutoff limit.

trait Tree
case class Inner(left: Tree, right: Tree) extends Tree
case class Leaf(x: Int) extends Tree


}