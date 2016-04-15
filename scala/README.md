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

