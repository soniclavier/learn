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
