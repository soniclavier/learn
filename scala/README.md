## Notes

<blockquote>March 5th</blockquote>
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
