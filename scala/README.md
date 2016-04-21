## Notes

***These are notes for self referance***

<blockquote>March 5th, 2016</blockquote>
**constructor**
Constructor can be created by def this. e.g., below code calls a two arg constructor from single arg<br/>
```
class Rational(x:Int,y:Int) {
  def this(x:Int) = this(x,1)
```

**require()**

can be used to specify a condition/requirement
e.g., 
```
class Rational(x:Int,y:Int) {
  require(y!=0,"denominator must be non-zero")
}
```
**assert()**

We can also use assert.<br/>
Assert throws assertion error, require throws illegal argument exception

<blockquote>March 6th, 2016</blockquote>
**infix notation**

suppose there is function add in class Rational that takes another rational number, then instead of writing
r.add(s)  where r and s are objects of class Rational, we can write
```
r add s
```

**symbolic identifier**
a function can have symbols also as a function name<br/>
e.g.,<br/>
the add method in Rational can be replaced with +
```
def +(that: Rational) =
		new Rational(numer*that.denom + that.numer*denom,
		denom*that.denom)

// this can be used by 
r + s
```
*prefix opeartor*
to simulate prefix operator such as negative (-) we can add unary_ before the function definion like below
```
def unary_- = new Rational(-1*numer,denom)

//and later use this as
-x  //where x is an object of Rational
```

**abstract class**
similar to java, in scala also use the keyword abstract.
and the methods can be abstract/undefined. i.e., it can have without the =
e.g.,
```
abstract class MyAbsClass {
	def partial(x:Int): Int
}

class SomeOtherClass extends IntSet {
  def partial(x:Int): Int = x*2
}
```
**singleton class**
to create a singleton class, all that has to be done is to make it an object instead of class

**imports in scala**
```
import somepackage.SomeClass
import somepackage._
import somepackage.{ClassA,ClassB}
import somepackage.SomeObject.something
```

**traits**
traits are like interfaces, a class can implement many traits. In scala it is done using the keyword with.
e.g.,
```
trait Shape {
	def height: Int
	def width: Int
	def surface = height * weight
}

class Square with Shape
```
traits can contains fields and concrete methods which is differant from java, where we cannot have concrete methods

**exception**
throw Exc

<blockquote>April 15th, 2016</blockquote>

**pacakges**
same as in java. e.g., 
```
package com.vishnuviswanath

object MyObject {
}
```

**Nothing**

It is used to denote nothing, e.g., when there is nothing returned from a function because of some exception.
It is subtype of all other type.

**Null**

Subtype of all reference type
```
val x = null //set x as Null
val y:String = x //assign x to y, (works because y is of type reference)
val z:Int = null //try to assign null to z, this will not work because z is not of type refernce in the scala class hierarchy. ( Int is a value type)
```
**Scala class hierarchy**
<img src="http://www.scala-lang.org/old/sites/default/files/images/classhierarchy.png"/>

**Value Parameters**

```
class Student(val name:String, val id:Int)

#is same as

class Student(_name:String,_id:Int) {
	val name =_name
	val id = _id
}
```

**Type Parameters**

e.g.,
```
train MyTrain[T]
class Student[T](val name:String, val id: T)
```

A type parameter can also be used for functions.<br/>e.g.,
```
def myfun[T](input: T) = {
}

// it can be called by
myfun[Int](1)
myfun[String]("hii")
```
while calling such function, we don't need explicity specify the type, scala can figure it out by itself.

*Note:* A `val` is evaluated when the object is first initialized, def is evaluated each time it is referenced. 


<blockquote>April 16th, 2016</blockquote>

**Functions as Objects**

Functions are objects in scala. Functions are objects with apply methods.
```
//function type A=>B
package scala
train Function1[A,B] {
	def apply(x:A):B
}

//e.g., 2 (anonymous function)
(x:Int) => x*x
new Function1[Int,Int] {
	def apply(x:Int) = x*x
}

//e.g., 3

```

**Type Bound**

*Upper Bound* 
```
//here the type S is a subtype ot IntSet
def assertAllPos[S<:IntSet](r:S): S 
```
*LowerBounds*
```
//here the type S is a supertype of NonEmpty
//or NonEmpty is subtype of S 
def assertAllPos[S>:NonEmpty](r:S): S 
```
*LowerAndUpper Bound*
```
def assertAllPos[S>:NonEmpty <: IntSet](r:S): S 
```
<br/>
<blockquote>April 19th, 2016</blockquote>

**Covaraint, Contravariant and Invaraint**
<br/><br/>
covaraint: if a type A <: B, and C[A] <: C[B] <br/>
contravariant: if a type A <: B, and C[A] >: C[B] <br/>
invariant: none of the above holds <br/>
*In scala, arrays are not covariant but lists are covaraint*
<br/><br/>
In scala functions arugments are contravariant, and covariant in result types
```
//covariance is represented by + sign and contravariant by -. eg.,
trait Room[-T,+U] {
  def open(x:T):U
}
```


**Type Test and Type Cast**<br/>
*(use is discouraged)*<br>
x.isInstanceOf[T] = check if x is intance of type T
x.asInstanceOf[T] = (T) x  (i.e., cast x to type T)

**Case Classes**

```
train Expr
case class Number(n:Int) extends Expr
```
case classes implicitly defines companion objects with apply methods
```
object Number  {
	def apply(n:Int) = new Number(n)
}
```

**Pattern Matching**<br/>
Compared to java, Scala can have switch cases to match whole *class hierarchy*
```
def eval(e:Expr): Int = e match {
	case Number(e) => n
	case Sum(e1,e2) => eval(e1) + eval(e2)
}
```
Pattern match syntax
```
e match {
 case pattern => expr,
 .
 .
 case pattern => expr
}
```
Here *e* is matched with each *pattern* <br/>

Pattern examples:<br/>
1. Constructors. e.g., Number(n, )Number(_) (**_** doesn't care of the parameter)<br/>
 	*(will match all values of type (e.g., Number) or its subtype that has been constructed using the arguments that intern matches the patterns argument)*<br/>
2. variables. e.g., n,e1 etc<br/>
	*a variable pattern x matches any value, and that value is bound to x so that it can be used in the expression*<br/>
3. wildcard patterns _<br/>
4. constants. e.g., 1,"Something" : name constants/constant literals<br/>
5. combine above and build complicated patterns<br/>

<blockquote>April 20th, 2016</blockquote>
**Collections**

**Lists**
```
val fruits = List("apples","oranges")
val nums: List[Int] = List(1,2,3)
val listList: List[List[Int]] = List(List(1,3,4),List(2,3,4))
val empty: List[Nothing] = List()

```
lists are immutable in Scala

**Cons**<br/>
construction operation `::`
```
fruits = "apples" :: "oranges"
```
