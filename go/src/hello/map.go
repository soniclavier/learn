package main

import (
	"fmt"
)

type user struct {
	name string
	age int
}

func main() {

	users := make(map[string]user)

	users["vishnu"] = user{"Vishnu Viswanath", 30}
	users["vani"] = user{"Meenakshi Moorthy", 30}

	for name, user := range users {
		fmt.Printf("%s -> %v\n", name, user)
	}

	delete(users, "vishnu")

	vishnu, found := users["vishnu"]
	fmt.Printf("%t, %v\n", found, vishnu)

	vani, found := users["vani"]
	fmt.Printf("%t, %v\n", found, vani)
}