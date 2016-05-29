<blockquote>April 20th, 2016</blockquote>
# Collections

### Lists
```
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
```
fruits = "apples" :: ("oranges" :: Nil)
```
operators ending in ":" reverse are considered right hand operand in infix rotation<br/>
Here **::** is considered as a function of it's right hand side. either the list, or Nil in the above example

#### List patterns
```
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
```
val nums = List(2, -4, 5, 7, 1)

nums filter (x => x > 0)        //get all values > 0  =  List(2, 5, 7, 1)             
nums filterNot (x => x > 0)     //get all values ! >0  = List(-4)             
nums partition (x => x > 0)     //gets a pair/tuple with first value the reuslt of >0 and second of ~ >0 = (List(2),List(-4, 5, 7, 1))
```

**takeWhile, dropWhile, span**
stops when a wrong condition in encountered
```
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
```
val (sum,count) = (List(1,2,3,4) foldLeft (0.0,0))((x,y) => (x._1+y,x._2+1))
                                                 //> sum  : Double = 10.0
                                                 //| count  : Int = 4
sum/count 					 //> res13: Double = 2.5
```
The `op` passed to foldLeft(acc) should have the first parameter of same type as the acc (in the above example Tuple(Double,Int).<br/>
In general, the foldLeft signature is as below
```
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
```
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
```
val pair = ("answer",42)
```
We can get the value from the pair like below (this is actually pattern matching)
```
val (label,value) = pair
//here label will hold the first value and value will hold the second value

val test = ("a",1,2.3)
val (x,y,z) = test
```

#### Pairs from List *using splitAt*
```
val pair = xs splitAt index
```


#### Tuple class
e.g., of a Tuple 2 class
```
case class Tuple2[T1,T2](_1: +T1, _2: +T2) {
  overrdie def toString = "("+_1 +","+ _2 +")"
}
```
Since scala names the tuple filed as _1,_2 etc that is why we are able to access the tuple values of scala using ._1, ._2 etc

### Implicit parameters

If a function defines a parameter as implicit, then while executing that function, if the parameter is avaiable in the scope, it will be accessible inside the function body without having to pass the parameter to the function call.
```
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


