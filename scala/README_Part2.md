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



