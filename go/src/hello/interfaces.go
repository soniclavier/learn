package main

//interfaces should be some action(verb) or behaviour, should not be a noun such as user
type reader interface {
	read(b []byte) int
}

type localfs struct {
	name string
}

//the interface reader is implemented by file.
//Note there is no implements keyword in the file or in the Method declaration
func (localfs) read(b []byte) int {
	return 0
}

type dfs struct {
	name string
}

func retrieve(r reader) {

}

//Polymorphism , dfs also implements the interface reader
func (dfs) read(b []byte) int {
	return 0
}

func main() {

}
