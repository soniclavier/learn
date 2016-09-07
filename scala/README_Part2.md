<blockquote>April 20th, 2016</blockquote>
# Collections

## Lists
```scala
val fruits = List("apples","oranges")
val nums: List[Int] = List(1,2,3)
val listList: List[List[Int]] = List(List(1,3,4),List(2,3,4))
val empty: List[Nothing] = List()

```
List has mainly three operations, 
- head : first element of list
- tail : list of elements except the first
- isEmpty : if list is empty or not

lists are immutable in Scala

#### Cons
construction operation `::`
```scala
fruits = "apples" :: ("oranges" :: Nil)
```
operators ending in ":" reverse are considered right hand operand in infix rotation<br/>
Here **::** is considered as a function of it's right hand side. either the list, or Nil in the above example

#### List patterns
```scala
1 :: 2 :: xs \\lists that start with 1 and then 2
x :: Nil \\list of length 1 (x means it can be anything, but only 1 item present)
List() \\empty list

```
<blockquote>May 23rd, 2016</blockquote>
Other list functions

function|use
---|---
xs.length|gets the length of list
xs.last|gets the last element of list, exception if list is empty
xs.init|gets the list except the last element
xs take n|a list containing first *n* elements of the list
xs drop n|a list after dropping first n elements of the original list
xs(n)|gets the *nth* element of the list

<blockquote>May 28th, 2016</blockquote>

**filter, filterNot, partition**
```scala
val nums = List(2, -4, 5, 7, 1)

nums filter (x => x > 0)        //get all values > 0  =  List(2, 5, 7, 1)             
nums filterNot (x => x > 0)     //get all values ! >0  = List(-4)             
nums partition (x => x > 0)     //gets a pair/tuple with first value the reuslt of >0 and second of ~ >0 = (List(2),List(-4, 5, 7, 1))
```

**takeWhile, dropWhile, span**
stops when a wrong condition in encountered
```scala
nums takeWhile (x => x > 0)  //values > 0 till first failed condition =  List(2)            
nums dropWhile (x => x > 0)  //rest of the elements of takeWhile = List(-4, 5, 7, 1)                    
nums span (x => x > 0)  //tuple of above two  = (List(2),List(-4, 5, 7, 1))
```

#### ReduceLeft
syntax : `list reduceLeft op`
e.g., `List(1,2,3,4) reduceLeft (_+_)`
```
      +
     / \
    +   4
   / \
  +   3
 / \
1   2
```
The given `op` is applied to each pair of element in the list, starting from the left. `reduceLeft` will not work in case the list is empty (will throw `java.lang.UnsupportedOperationException: empty.reduceLeft`). We can use `foldLeft` in such cases, which takes a initail value 

#### FoldLeft
syntax : `(list foldLeft accumulator)(op)`
- Similar to reduceLeft, except that it takes an initial value/accumulator
- The inital value is returned in case list is empty `(List() foldLeft -1)((x:Int,y:Int) => x+y)`
e.g., `(List(1,2,3,4) foldLeft -1)(_+_)`

```  
          +
         / \
        +   4
       / \ 
      +   3
     / \
    +   2
   / \
 -1  1  
```
Another example with pair accumulator, to find average
```scala
val (sum,count) = (List(1,2,3,4) foldLeft (0.0,0))((x,y) => (x._1+y,x._2+1))
                                                 //> sum  : Double = 10.0
                                                 //| count  : Int = 4
sum/count 					 //> res13: Double = 2.5
```
The `op` passed to foldLeft(acc) should have the first parameter of same type as the acc (in the above example Tuple(Double,Int).<br/>
In general, the foldLeft signature is as below
```scala
def foldLeft[U](acc: U)(op: (U,T) => U): U = ???
```
The accumulator type is `U` and the function `op` should take a function that takes two parameters of the types `U` and `T` and returns a reuslt of type `U`. Also the function foldLeft returns a type `U`

#### ReduceRight
syntax : `list reduceRight op`<br/>
e.g., `List(1,2,3,4) reduceRight (_+_)`<br/>
*If the `op` is both commutative and associative, reduceLeft and reduceRight will return the same result, as in the above example*
```
      +
     / \
    1   +
       / \
      2   +
         / \
        3   4
```

#### FoldRight
syntax : `(list foldRight acc)(op)`<br/>
e.g., `(List(1,2,3,4) foldRight 0)(_+_)`
```
      +
     / \
    1   +
       / \
      2   +
         / \
        3   +
           / \
          4   0
```
An example where we cannot interchange foldLeft and foldRight, `concat`
```scala
def concat[T](xs: List[T],ys: List[T]): List[T] = {
  (xs foldRight ys)(_ :: _)
}

foldRight
    ::
   /  \
  x1  ::
     /  \
   x2   ::
       /  \
      xn   ::
          /  \
        y1   ::
            /  \
           y2  ::
              /  \
             yn   Nil
```
We cannot do foldLeft here because
```
     ::
    /  \
   ::
  /  \
x1   x2
```
we cannot do concat, `::` on x2 since x2 is a Int, not List. (note `::` is **right associative**)
### Pairs and Tuples
A pair is a Tuple2 in scala. e.g., 
```scala
val pair = ("answer",42)
```
We can get the value from the pair like below (this is actually pattern matching)
```scala
val (label,value) = pair
//here label will hold the first value and value will hold the second value

val test = ("a",1,2.3)
val (x,y,z) = test
```

#### Pairs from List *using splitAt*
```scala
val pair = xs splitAt index
```


#### Tuple class
e.g., of a Tuple 2 class
```scala
case class Tuple2[T1,T2](_1: +T1, _2: +T2) {
  overrdie def toString = "("+_1 +","+ _2 +")"
}
```
Since scala names the tuple filed as _1,_2 etc that is why we are able to access the tuple values of scala using ._1, ._2 etc

### Implicit parameters

If a function defines a parameter as implicit, then while executing that function, if the parameter is avaiable in the scope, it will be accessible inside the function body without having to pass the parameter to the function call.
```scala
class ImplicitObj {
		def execute() = {
			println("obj executed")
		}
	}
	
	type MyObj =  ImplicitObj
	implicit val x: MyObj = new ImplicitObj
	
	
	def testImplicit()(implicit o: MyObj) = {
		o.execute()
	}
  
  testImplicit()
```
- Here function `testImplicit` takes an implicity parameter o of the type MyObj
- `val x` is available as implicit while executing `testImplicit`

if multiple implicit objects of the same type is present in the scope while executing testImplicit, scala will not which implicit object to take, hence will not compile saying `ambiguous implicit values`

<blockquote>May 29th, 2016</blockquote>

## Vectors
Represented as shallow trees
```scala
val nums = Vector(1,2,-10,4)
```
- vector of length < 32 elements is array **(level 1)**
- if > 32, there will be a vector of 32 pointers pointing 32\*32 elements **(level 2)**
- if > 2^10 tree grows one more level and so on. up to 2^15 **(level 3)**
- 2^20 elements in **(level 4)**
- 2^30 elements in **(level 5)**

depth of vector log_32(N)

<blockquote>Use lists instead of vector if head and tail of the lists are accessed more often.(lists give constant time access to head and tail of a list).</blockquote>

#### Operations on Vectors
- All operations that are avaiable on list are also applicable to vector - such as **map, fold, head, tail** etc
- Cons operator on Vector is different from list
	- `x +: xs`  *Creates a new vector with leading element x and followed by all elements of xs*
	- `xs :+ x`  *Creates a new vector with all elements of xs followed by x*

## Seq
Super class of List and Vector
- Array and String are also sequence like strucutre which has operations such as **map, filter, fold, head, tail, takeWhile etc**

```scala
val l = Array(1,2,3,4)
l map (x => x+1)

val s = "Hello"
s filter (c => c.isUpper)
```
![Scala Hierarchy of Collections](https://vishnuviswanath.com/img/scala_coll_hierarchy.png)


### Range
gives a range of elements
```scala
val r: Range = 1 until 5
```

#### For-Expression 
synax : `for (s) yield e`
**s** is a sequence of generators and filters <br/>
**generator** `p <- e` where p is a pattern and e is a collection or an expression who's value is a collection
**filter** of the form `if f` where f a is boolean expression

```scala
case class City(name: String, area: Int)
for (c <- cities if c.area > 1000) yield c.name
```
the above for expression is equivalent to 
```scala
cities filter (c => c.area > 1000) map (c => c.name)
```
The difference between for expression and for loop is that, for-loop produces [side effects](https://github.com/soniclavier/learn/tree/master/scala#side-effects)

**Pattern on the LHS**<br/>
we can also have patterns on the LHS<br/>
e.g.,
```scala
var x = List(1,2,3)
for {
	1 <- x
    } yield "one"
    
res2: List[String] = List(one)
```

## Sets
```scala
val s = Set("a","b","c","a")  //> s  : scala.collection.immutable.Set[String] = Set(a, b, c)
```
most operations in sequence is avaiable in set also, such as **map, filter ** etc
- sets are unordered
- does not contain duplicates


## Map
- of the type `Map[Key,Value]`
- Associative map
- Associates key of the type `Key` to a value of the type `Value`

e.g.,
```scala
val m = Map("a" -> 97, "b" -> 98)
```

#### Maps are Functions
```scala
m("a")
m("c") //throws exception
m get "c" // Option[Int] = None
m get "a" // Option[Int] = Some(97)
```
**Option** <br/>
- Option is a `trait Option[+A]`, 
- there is `case class Some[+A] extends Option[A]`
- there is an `object None extends Option[Nothing]` 


#### Sorted and GroupBy

Sort
```scala
val l = List("c","b","aa","a","cccc")           //> l  : List[String] = List(c, b, aa, a, cccc)
l.sorted                                        //> res4: List[String] = List(a, aa, b, c, cccc)
l.sortWith(_.length < _.length)                 //> res5: List[String] = List(c, b, a, aa, cccc)
l.sortWith((x,y) =>x.length < y.length)         //> res6: List[String] = List(c, b, a, aa, cccc)
```

Group
```scala
l groupBy (x => x.head)                         //> Map(b -> List(b), a -> List(aa, a), c -> List(c, cccc))
l groupBy (_.length)                            //> Map(2 -> List(aa), 4 -> List(cccc), 1 -> List(c, b, a))
```

### Map as total function (with default values)
```scala
val m1 = m withDefaultValue -1
m1("c")  //-1
```

#### Map from sequence
```scala
val seq = List((1,"a"),(2,"b"))           //> seq  : List[(Int, String)] = List((1,a), (2,b))
seq.toMap       
```

<blockquote>June 21st, 2016</blockquote>

## Misc
#### sbt % vs %%
The difference between % and %% is that, 
- `%%` adds the scala version to the dependency
- `%` does not add

e.g.,
```scala
"org.scala-tools" % "scala-stm_2.9.1" % "0.3" // you have to specify the scala version

scalaVersion in ThisBuild := "2.11.7"
"org.scala-tools" %% "scala-stm" % "0.3" //scala version will inferred from project version,

```
<blockquote>June 23rd, 2016</blockquote>
#### Compilation error: Forward reference extends over definition of variable
One of the reason why this error occurs if you have a method call which is defined after the call, and you have some `val` defenition between the call and the defenition. e.g.,
```scala
    object ForwardReferenceTest {
    
      def main(args: Array[String]): Unit = {
        test
        val x = 1
        def test = println("hi")
      }
    }
```
you will get error
```
    Error:(7, 5) forward reference extends over definition of value x
        test
        ^
```
the function `test` is defined after the call and there is a value definition of `x` in between. Removing/Moving `val x` definition will solve the problem.

[Part 3](README_Part3.md)
