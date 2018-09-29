package main

import "fmt"
/*
	Learn the variables in go & size occupied by each variable type
*/
func main() {
	//zero values (uninitialized)
	//the bytes allocated are assinged 0 value if not initialized (not null)
	//e.g., string = [nil, φ]

	//in a 64 bit arch (64 bit address), a word size = 64. => int is 64 bit
	var id int

	//string is a two word datastrcutre. word 1 points to array of bytes, word 2 its size.
	//a zero value string means its empty string. 
	var name string

	//takes 64 bit
	var perc float64

	//declare & initialize variables
	a := 10
	b := 1.234
	c := "test"
	d := true	

	fmt.Printf("%s\n", name)
	fmt.Printf("%d\n", id)
	fmt.Printf("%f\n", perc)
	fmt.Printf("%d\n", a)
	fmt.Printf("%f\n", b)
	fmt.Printf("%s\n", c)
	fmt.Printf("%t\n", d)
}