# Go learn

## Receiver/Behavior
When defining a method the parameter between func keyworkd and func name
e.g.,
```go
func (u user) notify() {
}
```

## Decoupling
Decoupling has cost - the data has to be allocated in heap (for both pointer and value semantics). Because of double indirection. (during escape analysis)\n
e.g.,
```go
vishnu := user{"Vishnu"}
f1 := vishnu.hello
//f1 will be a two word pointer. first word is pointer to code. second word a pointer to the data (vishnu)
f1()
```
Costs indirection and allocation


## Value semantics vs Pointer semantics - when to use what
Value semantics means more allocation, but no side effects.\n
Pointer semantics means less allocation, but can have side effects.\n
Do not make copies of what pointer points to.\n
- Built it types - Value semantics
- Reference types - Value semantics
    - Except Unmarshal/Decode/Marshal/Encode - Pointer semantics
- Struct types - Pointer/Value (start with Pointer semantics if not sure.)
- Files - Pointer semantics
- Do not mix value and pointer semantics
e.g.,
```go
users := []user{
  {"vishnu,
  {"meenakshi"}
}

// ranging over copy of users (value semantic). Which is not good since update doesnt have any effect on the users.
for i, u := users {
  u.update()
}
```

## Interfaces
- Should define a behavior (a verb, not a noun)
- A Method on a Receiver that has same signature is said to implement the interface. (No need for the struct to explicitly "implement" it)
- Two word structure
  - First word - Points to iTable (a two word structure)
    - First word points to type of value that is stored in interface
    - Second word points to the implementation
  - Second word points to the concrete data
