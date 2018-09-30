package main

import (
	"fmt"
)

type user struct {
	name string
	age int
	spouse string
}

/*
Methods vs Functions
Methods adds a behaviour to the struct.
*/
//value receiver (copy of user)
func (u user) sayHi() {
	fmt.Printf("Hi from : %s\n", u.name)
}

//value semantics
func (u user) marry(u2 user) user {
	u.spouse = u2.name
	return u
}

//pointer semantics. the user is shared.
func (u *user) birthday() {
	fmt.Printf("Happy birthday %s\n", u.name)
	u.age++
}

type sub_user user

func main() {
	vishnu := user{"Vishnu", 29, ""}
	vishnu.sayHi()
	vishnu.birthday()
	fmt.Printf("Vishnu is %d years old\n", vishnu.age)

	vani := user{"Meenakshi", 30, ""}
	marriedVishnu := vishnu.marry(vani)

	fmt.Printf("%v\n", vishnu)
	fmt.Printf("%v\n", marriedVishnu)

	p := sub_user{"p", 30, ""}
	//p.sayHi() //sayHi is undefined for sub_user
	fmt.Printf("%v\n", p)

	//function as a variable
	hi := vishnu.sayHi
	vishnu.name = "Vishnu Viswanath"

	//will print hi from Vishnu, not hi from Vishnu Viswanath. 
	//since h1 := vishnu.sayHi get a copy of user vishnu
	hi()
}


