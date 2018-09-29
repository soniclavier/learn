package main


import "fmt"

//slice is designed to use value semantics, should not share a slice using pointers. slices should stay on stack.
func main() {

	/*
	5 : length
	8 : capacity (for growing the length)
	*/
	words := make([]string, 5, 10) 
	words[0] = "vishnu"
	words[1] = "vani"
	words[2] = "jishnu"

	takeSlice(words)

	//nil slice (a slice set to zero value) : used to return an error
	var places []string //[nil, φ, φ]
	
	for i := 0; i <= 10; i++ {
		places = append(places, fmt.Sprintf("%s%d","india",i))
		//places[i] = "india" // is more effiicent than append, but need to set size of slice upfront. 
		placesCap := cap(places) //capacity doubles when current capacity is full.
		fmt.Printf("%d\n", placesCap)
	}


	subPlaces := places[3:3+4] //slice of slice

	fmt.Printf("-- places with side effect (india 7 over written by usa )--- \n")
	for i, v := range subPlaces {
		fmt.Printf("%d, %s\n", i, v)
	}

	//subPlaces & places are backed by same backing array. any change in subPlaces will have side effect on places
	subPlaces = append(subPlaces, "usa")
	for i, v := range places {
		fmt.Printf("%d, %s\n", i, v)
	}

	
	//to avoid the side effect, we need to slice with capacity as the length
	//so that the append will have to create a new backing array (since capacity is full)
	subPlacesNoSideEffects := places[3:3+4:7]
	subPlacesNoSideEffects = append(subPlacesNoSideEffects, "india")

	fmt.Printf("-- places with original side effect, but not the later one (usa not overwritten by india) --- \n ")
	for i, v := range places {
		fmt.Printf("%d, %s\n", i, v)
	}

	fmt.Printf("--- sub places with no side effect (usa overwritten by india) --- \n")
	for i, v := range subPlacesNoSideEffects {
		fmt.Printf("%d, %s\n", i, v)
	}
	

	//empty slice (a slice with 0 len and 0 cap, but there is a pointer.) : return no error, but no data
	// the pointer points to an empty struct. 
	jobs := []string{} 
	print(jobs) //printing to allow compile
}


//words []string  : a slice as a paramter
//words [4] string : an array as a paramter
func takeSlice(words []string) {
	for i, v := range words {
		fmt.Printf("%d ; %p ; %s \n", i, &words[i], v)
	}
}