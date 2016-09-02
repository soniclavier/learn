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
