package main

func main() {

	var matrix [2]int16

	matrix[0] = 1
	matrix[1] = 2

	for i, v := range matrix {
		matrix[i] = 0 //range creates a copy of the array, the value does not get udpated
		println(v)
	}

	//row traversal is faster than column traversal
	//predictable access pattern :  (how data is loaded into cpu)
}
