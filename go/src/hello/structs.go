package main

import "fmt"

type employee struct {
	active bool //1 byte
	id int16 //16 bits
	salary int64 //64 bits
} 
/* 
total size != 1 + 2 + 8 => 11 bytes
because in go adds 1 byte padding  b/w bool and int16.
int16 => 2 bytes => should start on 2 byte addresses (0,2,4 etc)
int64 => 8 bytes => should start on 8 byte addresses (0,8,16 etc)

[1 byte bool | 1 byte padding | 2 bytes int | 4 bytes padding | 8 bytes int64]
total size = 16 bytes
*/

func main() {
	var e1 employee
	fmt.Printf("%+v\n", e1)

	e2 := employee {
		active: true,
		id: 100,
		salary: 200000000,
	}

	fmt.Printf("%+v\n", e2)
	fmt.Printf("%d\n", e2.id)

	//anonymous structs
	c1 := struct {
		zipcode int32
		name string
	}{
		name: "google",
		zipcode: 95512,
	}

	fmt.Printf("%+v\n", c1)

	//empty struct (zero allocation)
	var data struct{}

}