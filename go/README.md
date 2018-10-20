# Go learn

## Value semantics vs Pointer semantics - when to use what
Built it types - Value semantics
Reference types - Value semantics
  - Except Unmarshal/Decode/Marshal/Encode - Pointer semantics
Struct types - Pointer/Value (start with Pointer semantics if not sure.)
Files - Pointer semantics
### Do not make copies of what pointer points to.
