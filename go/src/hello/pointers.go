package main

//import "fmt"

type rs struct {
	id int
}

//increment the value of the pointer.
func increment(inc *int) {
	*inc++
}

func funWithPointerReturn() *rs {
	r := rs {
		id: 1,
	}

	return &r
}

func main() {
	i := 10
	println("Pointer variable value before passing reference",i)
	increment(&i) // pass the address
	println("Pointer variable value after passing reference", i)

	//fmt makes values escape to heap??

	//the value escapes to heap, since the stack in which the value r was constrcuted does not exist(can be overwritten in the next call) after the function
	//* so no need of garbage collection in stack, because the next call will initialize the memory to 'zero-value'. => having the value in stack is performant.
	r := funWithPointerReturn()
	println(r)
}


