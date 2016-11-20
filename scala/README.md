# Scala Notes - Basics

***These are notes for self referance***

<blockquote>March 5th, 2016</blockquote>
### constructor
Constructor can be created by def this. e.g., below code calls a two arg constructor from single arg<br/>
```scala
class Rational(x:Int,y:Int) {
  def this(x:Int) = this(x,1)
```
### var and val
`var` stands for variable. The value can be changed/ re-assigned.<br/>
`val` stands for value. The value cannot be re-assigned, but the value itself can be changed, if it is mutable
```scala
class Mutable {
var x: Int = _
}
defined class Mutable

val y = new Mutable

scala> y.x = 10
y.x: Int = 10

scala> y.x = 11
y.x: Int = 11
```
A variable can be initialized to it's default value by using `_`. E.g., the var x in class Mutable is initialized to it's default value.
### Loops
```scala
for(x <- 0 to 10) println(x) //prints 0 to 10
for(x <- 0 until 10) println(x) //prints 0 to 9

val ls = List(1,2,3)
for (l <- ls) println(l) //prints 1,2,3
ls.foreach{println} //prints 1,2,3

val ls = List(1,2,3,1)
for( l <- ls if l == 1) println(l) //prints 1,1
```
### require()

can be used to specify a condition/requirement
e.g., 
```scala
class Rational(x:Int,y:Int) {
  require(y!=0,"denominator must be non-zero")
}
```
### assert()

We can also use assert.<br/>
Assert throws assertion error, require throws illegal argument exception

<blockquote>March 6th, 2016</blockquote>
### infix notation

suppose there is function add in class Rational that takes another rational number, then instead of writing
r.add(s)  where r and s are objects of class Rational, we can write
```scala
r add s
```

### symbolic identifier
a function can have symbols also as a function name<br/>
e.g.,<br/>
the add method in Rational can be replaced with +
```scala
def +(that: Rational) =
		new Rational(numer*that.denom + that.numer*denom,
		denom*that.denom)

// this can be used by 
r + s
```
*prefix opeartor*
to simulate prefix operator such as negative (-) we can add unary_ before the function definion like below
```scala
def unary_- = new Rational(-1*numer,denom)

//and later use this as
-x  //where x is an object of Rational
```

### abstract class
similar to java, in scala also use the keyword abstract.
and the methods can be abstract/undefined. i.e., it can have without the =
e.g.,
```scala
abstract class MyAbsClass {
	def partial(x:Int): Int
}

class SomeOtherClass extends IntSet {
  def partial(x:Int): Int = x*2
}
```
### singleton object
- instead of `class` use `object` 
- to create a singleton object, all that has to be done is to make it an object instead of class
- singleton objects are created when it is accessed first time
- we cannot create another instance of singleton object using `new`


### imports in scala
```scala
import somepackage.SomeClass
import somepackage._
import somepackage.{ClassA,ClassB}
import somepackage.SomeObject.something
```

### traits
traits are like interfaces, a class can implement many traits.
traits can contains fields and concrete methods which is differant from java, where we cannot have concrete methods

e.g.,
```scala
trait Shape {
	def height: Int
	def weight = 20
}

```
we extend a trait if we want to implement only one trait
```scala
class MyShape extends Shape {
     def height = 10
}
```
if there are multiple interfaces to be implemented then we use **extend** for one of them(first one) and **with** for the rest
```scala
trait CanRotate
trait Drawable

class MyShape extends Shape with Drawable with CanRotate {
     def height = 10
}
```
Scala allows multiple inheritance using **with(mixin)**, - the conflict(diamond problem) is avoided by making use of hierarchy. The trait that is infront in the hierarchy is given priority e.g.,
```scala
trait Vehicle { def move() = println("vehicle moving") }
trait Car extends Vehicle { override def move() = println("car moving") }
trait HasWheels extends Vehicle { override def move() = println("wheels moving") }
trait FourWheeled extends HasWheels { override def move() = println("four wheels moving") }

import scala.reflect.runtime.{universe => u} //for checking hierarchy

class Eclipse extends Vehicle with Car with FourWheeled
scala> new Eclipse().move
four wheels moving
val t = u.typeOf[Eclipse]
t.baseClasses
>res17: List[reflect.runtime.universe.Symbol] = List(class Eclipse, trait FourWheeled, trait HasWheels, trait Car, trait Vehicle, class Object, class Any)
//first Class/Trait with move() defined in the hierarchy is FourWheeled


class Eclipse extends Vehicle with FourWheeled with Car
scala> new Eclipse().move
car moving
val t = u.typeOf[Eclipse]
t.baseClasses
>res18: List[reflect.runtime.universe.Symbol] = List(class Eclipse, trait Car, trait FourWheeled, trait HasWheels, trait Vehicle, class Object, class Any)

class Eclipse extends Car with FourWheeled with Vehicle
scala> new Eclipse().move
four wheels moving
t.baseClasses
>res20: List[reflect.runtime.universe.Symbol] = List(class Eclipse, trait FourWheeled, trait HasWheels, trait Car, trait Vehicle, class Object, class Any)
```
In the last example,  even though Vehicle is mixed last by FourWheels is "infront" in the hierarchy because Vehicle was loaded up when Car was loaded, FourWheeled trait was loaded after this.


we need to use override keyword if we are redefining an already defined method from the trait 
```scala

class MyShape extends Shape with Drawable with CanRotate {
     def height = 10
     override def weight = 25
}
```
Side note : here height and weight are not variables, instead they are methods. these are methods that does not take any parameters and returns an int. these methods could also be written as
```scala
def height(): Int = {
     return 25
}
```

It is sligtly different case when there are vars and vals in trait. e.g.,
```scala
trait Test {
     var x: Int
     val y: Int
     val z = 20
     var a = 30
}
```
to implement this trait,
```scala
class MyTest extends Test {
     var x = 10           //does not need override keyword
     override val y = 20  //needs override keyword
     override val z = 15  //needs override keyword
     //override var a = 80  - we cannot do this, compiler will complain that variable a cannot override a mutable variable
}
```
 
**instantiate a trait??**
```scala
trait Test {
     val x = 10
}

new Test {
     print(x)
}
```
This is not actually instantiation of trait, instead this is a shorthand notation to create an anoymous class that extends the trait and instantiate it at the same time. This is equivalent to
```scala
class MyTest extends Test {
     print(x)
}

new MyTest()
```
[SO link1](http://stackoverflow.com/questions/16259168/how-does-curly-braces-following-trait-instantiation-work) [SO link2](http://stackoverflow.com/questions/12891321/why-are-traits-instantiable)
### exception
throw Exc

<blockquote>April 15th, 2016</blockquote>

### pacakges
same as in java. e.g., 
```scala
package com.vishnuviswanath

object MyObject {
}
```

### Objects, Case class vs Class
Consdier below examples of class, objects and case classes
```scala
class MyClass(name: String)  {
}
object myObject {
 def apply(x:String) = ???
}
object myObject2 {
}
case class MyCaseClass(name:String, age: Int)
```
MyClass can be instantiated as below
```scala
var x = new MyClass("something") // we cannot call var x = MyClass("something"), need the new keyword
```
myObject can be instantiated as below
```scala
var x = myObject("something") //we are able to pass the parameter because of the apply method which takes string parameter
//myObject2 can be instantiated as below
var x = myObject2
```
**case class**, MyCaseClass can be instantiated as follows
```scala
var x = MyCaseClass("something", 10)
//this is because, when a case class is created, scala implicitly creates a companion object for the case class with apply method, like.
object MyCaseClass {
 def apply(name: String, age: Int) = new MyCaseClss(name, age)
}
```
we can make a copy of the case class by calling **copy**
```scala
val y = x.copy(age=11) //created a new case class from x, by altering only the age field
```
A case class implements equals method by default, hence can be **compared** by using ==
```scala
x == y //will return false since we changed age 
val z = y.copy(age=10)
x == z //will return true
```

### Functions
```scala
def funName(arg1: ArgType,arg2: Arg2Type,..): ReturnType = {
  function body
  somevar (this will be returned)
}
```
- in scala, the last line executed in a function body will be returned.
- scala can interpret the return type by checking this `: ReturnType` is optional

<blockquote>ReturnType must be specified for recursive call, This is because scala checks the last line in fun body to identify the return type, and this will end up in infinite loop incase of recursive call</blockquote>

#### Functions with repeated parameters
```scala
def test(bs : Int*) {
  bs foreach println
}                                               
test(1,2,3) 
```
# Advanced 

### Type Alias
from [here](https://www.safaribooksonline.com/library/view/learning-scala/9781449368814/ch10.html).

Used for giving aliases. <br/>
It can be applied on classes.For e.g., Int,Tuple etc
```scala
type Whole = Int    //int is an abstract class
val x: Whole = 5

type User = Tuple2[Int,String]      //Tuple2 is an instantiable class
val u: User = new User(1,"Vishnu")


type T3[A,B,C] = Tuple3[A,B,C]   //a tuple3 of any 3 type parameters
val things = new T3(1,'a',true)
val things = new T3("vishnu",1.0,'a')
```
We can also have abstract Type Alias, e.g., in a Trait. In this case, the implementing class will have to define what actually those aliases are before using.
```scala
class User(val name: String)

trait Factory { 
	type A;  //the type alias is declared but not defined
	def create: A 
}
defined trait Factory

trait UserFactory extends Factory {
    type A = User   //here the abstract alias A is defined as User
    def create = new User("")
    }

```
Type alias can also defined on functions
```scala
type Set = Int => Boolean   // a function that takes Int as input and returns Boolean
def contains(s:Set,elem:Int): Boolean = s(elem)
```

### ??? (Not yet implemented)
<blockquote>This is my favorite! :)</blockquote>
In scala, we can use ??? to denote that the function/some part of the code is not yet implemented. e.g.,
```scala
def myFun(key: Int): Int = ??? //here only signature is defined, implementation is not provided
```
??? is not like an abstract method, but is like a placeholder. Later when we are know/decide how the function body has to be implemented, we can replace the ??? with the implementation.

```scala
if(???) ???
else if(???) ???
else sumotherfunction(???)
```
### Higher-Order Functions
functions that take functions as parameters or return functions
```scala
def sum(f:Int=>Int, a:Int,b:Int): Int = 
	if (a>b) 0
	else f(a) + sum(f,a+1,b)
	
def sumInts(a:Int,b:Int) = sum(id,a,b)
def sumCubes(a:Int,b:Int) = sum(cubes,a,b)

def id(x:Int) = x
def cubes(x:Int) = x*x*x
```
**function types**
`A => B`<br/>
is a function that takes argument of type A and returns result of type B

### Anonymous functions
*literals for functions (no names, like a string "abc")*
e.g.,
```scala
(x:Int,y:Int) => x*y
(x,y => x*y) //scala will interpret the types
```

### Currying

```scala
def sum(f:Int=>Int): (Int,Int)=>Int = {
  def sumF(a:Int,b:Int): Int = {
    if (a>b) 0
    else f(a)+sumF(a+1,b)
  }
  sumF 
}
```
***note that there is no need for writing return keyoword in scala***

now we can define the function sumCubes as
```scala
def sumCubes = sum(x=>x*x*x)
//this can be called as
sumCubes(1,10)
```
What happened here is that, when we called `sum(x=>x*x*x)`, the `x=>x*x*x` is set as a function **f** and the function **sumF** defined earlier is returned. => giving us a function that takes two arguments and returns an int, and which internally uses the cube function passed.<br/>
so we can define another function
`def sumInt = sum(x=>x)` and so on.
<br/><br/>
we can also define more functions like sumInt and sumCube. But there is a way by which we can avoid that. e.g., we can call
```scala
sum(cube)(1,10) 
//this is equivalent to (sum(cube))(1,10)
```
**This is called Currying**: it is a special syntax for cases where functions return functions

### Nothing

It is used to denote nothing, e.g., when there is nothing returned from a function because of some exception.
It is subtype of all other type.

### Null

Subtype of all reference type
```scala
val x = null //set x as Null
val y:String = x //assign x to y, (works because y is of type reference)
val z:Int = null //try to assign null to z, this will not work because z is not of type refernce in the scala class hierarchy. ( Int is a value type)
```
### Scala class hierarchy
<img src="http://www.scala-lang.org/old/sites/default/files/images/classhierarchy.png"/>

### Value Parameters

```scala
class Student(val name:String, val id:Int)

#is same as

class Student(_name:String,_id:Int) {
	val name =_name
	val id = _id
}
```

### Type Parameters

e.g.,
```scala
trait MyTrain[T]
class Student[T](val name:String, val id: T)
```

A type parameter can also be used for functions.<br/>e.g.,
```scala
def myfun[T](input: T) = {
}

// it can be called by
myfun[Int](1)
myfun[String]("hii")
```
while calling such function, we don't need explicity specify the type, scala can figure it out by itself.

*Note:* A `val` is evaluated when the object is first initialized, def is evaluated each time it is referenced. 

`_` can be used to indicate **any type**. e.g.,
```scala
scala> class Typed[T] {}
defined class Typed

scala> class Typed2[T] {}
defined class Typed2

scala> new Typed2[Typed[Int]]()
res11: Typed2[Typed[Int]] = Typed2@71e9ddb4  //(A)

scala> new Typed2[Typed[_]]()
res12: Typed2[Typed[_]] = Typed2@76ed1b7c    //(B)
```
In (A), Typed2 is created with a type of Typed of type Int. Where as in (B) Typed2 is created with Typed of any type.	
<blockquote>April 16th, 2016</blockquote>

### Functions as Objects

Functions are objects in scala. Functions are objects with apply methods.
```scala
//function type A=>B
package scala
trait Function1[A,B] {
	def apply(x:A):B
}

//e.g., 2 (anonymous function)
(x:Int) => x*x
new Function1[Int,Int] {
	def apply(x:Int) = x*x
}

//e.g., 3

```

### Type Bound

#### Upper Bound
```scala
//here the type S is a subtype ot IntSet
def assertAllPos[S<:IntSet](r:S): S 
```
#### LowerBounds
```scala
//here the type S is a supertype of NonEmpty
//or NonEmpty is subtype of S 
def assertAllPos[S>:NonEmpty](r:S): S 
```
#### LowerAndUpper Bound
```scala
def assertAllPos[S>:NonEmpty <: IntSet](r:S): S 
```
<br/>
<blockquote>April 19th, 2016</blockquote>

### Covaraint, Contravariant and Invaraint
covaraint: if a type A <: B, and C[A] <: C[B] <br/>
contravariant: if a type A <: B, and C[A] >: C[B] <br/>
invariant: none of the above holds <br/>
*In scala, arrays are not covariant but lists are covaraint*
<br/><br/>
In scala functions arugments are contravariant, and covariant in result types
```scala
//covariance is represented by + sign and contravariant by -. eg.,
trait Room[-T,+U] {
  def open(x:T):U
}
```

A good answer on SO [link](http://stackoverflow.com/questions/6135663/problem-with-bounded-type-parameterised-case-class-and-default-args-in-scala) that talks about variance.
Another [blog](http://like-a-boss.net/2012/09/17/variance-in-scala.html) about variance.


### Type Test and Type Cast
*(use is discouraged)*<br>
x.isInstanceOf[T] = check if x is intance of type T
x.asInstanceOf[T] = (T) x  (i.e., cast x to type T)

### Case classes

```scala
trait Expr
case class Number(n:Int) extends Expr
```
case classes implicitly defines companion objects with apply methods
```scala
object Number  {
	def apply(n:Int) = new Number(n)
}
```

### Sealed vs Final classes/trait
A final class cannot be overriden, but a sealed class can be overriden in the same file. This is because when a trait or a class is defined as sealed, the compiler checks for all the implementations of that sealed trait. This is useful in the case of pattern matching. e.g.,
```scala
sealed class MyClass
case class MySubclass1(a: Int) extends MyClass
case class MySubclass2(a: Int, b: Int) extends MyClass

def fun(): MyClass = {
	MySubclass2(1,2)
}
val x = fun()
```
We created a sealed class and two case classes that extends the sealed class and a function that returns a class of the type MyClass (which is the sealed class). Now let's create a pattern matching on this class
```scala
x match {
	case MySubclass2(_,y) => y
}
<console>:16: warning: match may not be exhaustive.
It would fail on the following inputs: MyClass(), MySubclass1(_)
       x match {
```
Reference links - [link1](http://underscore.io/blog/posts/2015/06/02/everything-about-sealed.html), [link2](http://stackoverflow.com/questions/32199989/what-are-the-differences-between-final-class-and-sealed-class-in-scala)
### Pattern Matching
Compared to java, Scala can have switch cases to match whole *class hierarchy*
```scala
def eval(e:Expr): Int = e match {
	case Number(e) => n
	case Sum(e1,e2) => eval(e1) + eval(e2)
}
```
Pattern match syntax
```scala
e match {
 case pattern => expr,
 .
 .
 case pattern => expr
}
```
Here *e* is matched with each *pattern* <br/>

Pattern examples:<br/>
1. **Constructors**.<br/>e.g., Number(n, )Number(_) (**_** doesn't care of the parameter)<br/>
 	*(will match all values of type (e.g., Number) or its subtype that has been constructed using the arguments that intern matches the patterns argument)*<br/>
2. **variables**<br/> e.g., n,e1 etc<br/>
	*a variable pattern x matches any value, and that value is bound to x so that it can be used in the expression*<br/>
3. **wildcard patterns _**<br/>
4. **constants** <br/> e.g., 1,"Something" : name constants/constant literals<br/>
5. combine above and build complicated patterns<br/>


<blockquote>May 21st, 2016</blockquote>
### Call-by-name and Call-by-value

[SO Question](http://stackoverflow.com/questions/13337338/call-by-name-vs-call-by-value-in-scala-clarification-needed)
#### Side-effects
A function that does not have side effect, just computes the result and returns it, where as a function with side-effect will also do something extra (may be call someother function) before returning the value.

#### Syntax
Call by name and value have different syntaxes<br/>
**Call-by-value (more common)**
```scala
def addCallByValue(x: Int) = { 
  println("in add, x = "+x)
  println("in add again, x = "+x)
}
```
**Call-by-name**
```scala
def addCallByName(x: => Int) = { 
  println("in add, x = "+x)
  println("in add again, x = "+x)
}
```
So the difference in syntax is that call-by-value is of the form **arg: Type** where as call-by-name is of the form **arg: => Type**
#### Difference wrt to evaluation
Consider the below function that returns an Integer ( this is an example of function with side-effect)
```scala
def getMeSomeNum() = {
  println("in get me some number, doing something extra here") //this is the side-effect part
  1 //returning 1
}
```
Now let's call our two versions of add with the above getMeSomeNum function
```scala
addCallByValue(getMeSomeNum())
addCallByName(geMeSomeNum())

//result of call by value
in get me some number, doing something extra here
in add, x = 1
in add again, x = 1

//result of call by name
in get me some number, doing something extra here
in add, x = 1
in get me some number, doing something extra here
in add again, x = 1
```
As we can see, in the call-by-name case the println inside getMeSomeNum exectued twice, where as in the call-by-value it was executed only once. This is because in call-by-value case, the function getMeSomeNum was executed before passing it on to the add function**(value is passed)**. In the case of call-by-name getMeSomeNum was executed everytime variable `x` was accessed inside the add function. This is because, here the function getMeSomeNum was passsed without execution**(passed as name)** not value, and was evaluated each time that name `x` was accessed.

For call-by-name, the evaluation of the passed expression is carried out only at the last( therefore, the expression/function is passed along with all the subsequent function calls before being evaluated). 

### Value definition
```scala
val x = 2
val y = square(x)
```
Here `y` is **by-value** defenition. i.e., it is evalauted  when `val y=square(x)` is defined.
we can do `def x = 2` but there is no point in doing that because we would rather want the variable x to be evaluated when defined, since there is no difference if it evaluated at runtime or at the time of defenition.

### Tail recursion
[reference](https://anadea.info/blog/tail-recursion-in-scala)
Tail recursion implies that in a recursive function, the last line to be executed is a recursive call itself.</br>
e.g., These functions does not have any logical meaning(only meant to show how tail recursion works).
```
def testTailRec(x: Int): Int = {
    if (x < 0) x
    else testTailRec(x - 1)
  }
```
Scala automaticaly converts a tail recursive call to a loop, as you can see below (goto indicates a loop) 
```scala
public int testTailRec(int);
    Code:
       0: iload_1
       1: iconst_0
       2: if_icmpge     7
       5: iload_1
       6: ireturn
       7: iload_1
       8: iconst_1
       9: isub
      10: istore_1
      11: goto          0
```
An example of non tail-recursion.
```scala
 def testTailRec(x: Int): Int = {
    if (x < 0) x
    val y =testTailRec(x - 1)
    y * 3
  }
```
compiles to below code. Line 20 shows recursive call to same method (testTailRec). i.e., scala did not convert this function to a loop since it was not a tail recursive function.
```scala
public int testTailRec(int);
    Code:
       0: iload_1
       1: iconst_0
       2: if_icmpge     12
       5: iload_1
       6: invokestatic  #31                 // Method scala/runtime/BoxesRunTime.boxToInteger:(I)Ljava/lang/Integer;
       9: goto          15
      12: getstatic     #37                 // Field scala/runtime/BoxedUnit.UNIT:Lscala/runtime/BoxedUnit;
      15: pop
      16: aload_0
      17: iload_1
      18: iconst_1
      19: isub
      20: invokevirtual #39                 // Method testTailRec:(I)I
      23: istore_2
      24: iload_2
      25: iconst_3
      26: imul
      27: ireturn
```
[Part2](https://github.com/soniclavier/learn/blob/master/scala/README_Part2.md)
