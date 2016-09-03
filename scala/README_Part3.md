<blockquote>Sep 2nd, 2016</blockquote>
## Streams
Streams are similar to lists, except that the tail is not evaluated until asked for.<br/>
The Stream would know the head, and would also know how to generate the tail.
```
 val xs = Stream.cons(1, Stream.cons(2, Stream.empty))   //> xs  : Stream.Cons[Int] = Stream(1, ?)
 val ys = List(1,2,3)                                    //> ys  : List[Int] = List(1, 2, 3)
```
Here there is a **?** for the tail of the Stream, indicating it is not evaluated. Whereas for a List, it is evaluated.

### Concatinate Strams
**#::** is the Cons operator for Streams
```
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
