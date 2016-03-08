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
