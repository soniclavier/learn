<blockquote>Sep 2nd, 2016</blockquote>
## Streams
Streams are similar to lists, except that the tail is not evaluated until asked for.<br/>
The Stream would know the head, and would also know how to generate the tail.
```scala
 val xs = Stream.cons(1, Stream.cons(2, Stream.empty))   //> xs  : Stream.Cons[Int] = Stream(1, ?)
 val ys = List(1,2,3)                                    //> ys  : List[Int] = List(1, 2, 3)
```
Here there is a **?** for the tail of the Stream, indicating it is not evaluated. Whereas for a List, it is evaluated.

A collection such as a List or Set can be converted to stream by uinsg toStream method. e.g.,
```scala
List(1,2,3).toStream
//res1: scala.collection.immutable.Stream[Int] = Stream(1, ?)
```

we can get the head of the Stream by calling stream.head
```scala
val s = List(1,2,3,4,5,6,7,8,9,10).toStream
s.head
```
**take(n)** returns a Stream with n elements from the current stream
```scala
s.take(2)
```
**takeWhile()** returns the longest prefix of the stream till the predicte holds true
```scala
s.takeWhile(_<5)
```
### Concatinate Strams
**#::** is the Cons operator for Streams
```scala
0 #:: xs   //> res0: scala.collection.immutable.Stream[Int] = Stream(0, ?)
```
the cons method in Stream takes to parameters, head and tail. But the tail is a Name parameter(**called by name**, instead of value). Hence tail is not evaluted. Also, the tail is a `lazy` evaluated.


### Lazy evaluation
**val** : Evaluated when executed, evaluated only once
**lazy** : Evaluated only once and only when executed
**def** : Evaluated only when executed, but evaluted on each execution. (def defines a method)
[link1](http://stackoverflow.com/questions/4437373/use-of-def-val-and-var-in-scala/4440614#4440614) [link2](http://stackoverflow.com/questions/9449474/def-vs-val-vs-lazy-val-evaluation-in-scala)
[link3](http://stackoverflow.com/questions/11386559/val-mutable-versus-var-immutable-in-scala)

### Infinite Stream
Since tail of a Stream is evaluated lazily, we can have a function that creates an infinite Stream.<br/>
