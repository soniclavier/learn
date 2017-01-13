# Scalding basics and Examples
There are two types of API in scalding, TypeSafe and Field based

# Field based
## Reading input

```scala
val emps = Csv("/Users/vviswanath/Documents/emp.csv")
```
reads a csv file into a `com.twitter.scalding.Csv`. <br/>

### Define schema
```scala
val empScheme = List('name, 'age, 'office)
val emps = Csv("/Users/vviswanath/Documents/emp.csv", fields=empScheme)
emps.local
```


# TypeSafe
`TextLine`, `TypedTsv`
```scala
val test = TextLine("/users/vviswanath/mygit/learn/README.md")
val testTypedPipe = TypedPipe.from(test)
testTypedPipe.dump
```

## project
we can project fields from the source, using project and [Scala symbols](http://stackoverflow.com/questions/1324466/practical-examples-of-using-symbols-in-scala)

```scala
test.project('line) //line is a default field read by scalding while using TextLine

//e.g.,if car is having color and make,
car.project('make) //only projects the make of the car
```

## map, flatMap
Similar to scala map, flatMap. Additionaly these functions take a map of (existing -> new_field) before the map function
```scala
val lines = TypePipe.from(TextLine(args("input")))
lines.flatMap{ line => line.split(" ") } //without the mapping

//map one field to another
cars.map('make -> 'type){make: String => make.substring(6) } 

//map multiple fields
cars.map(('make, 'color) -> ('type, 'colorCat)) { t: (String, String) => 
(t._1.subtring(6), t._2(0)) }

```

## groupBy
similar to scala, Also takes an additional parameter fields
```scala
val lines = TextLine(args("input"))
val words = lines.flatMap{ line => line.split(" ") }
words.groupBy{ word => word(0)}

cars.groupBy('make){ _.
```
