# Compiler

### Compiling
Add `./bin:./src:./src/expressions:./src/functions:./src/statements:./src/types:./src/visitors:./src/types:./src/elements:./src/constants:./src/atoms:./src/util:./src/definitions:./src/ir` to classpath then `make`

### Running Type Checker Tests
`make typetest` to run withoutSubtypes tests

`make type` to run my tests

### Generating IR
Compile and then run `Compiler` located in the `bin` directory.


# export CLASSPATH=./libs/jasmin-2708/classes/:./libs/polyglot-1.3.4/classes/:./libs/soot-2708/classes/:./libs/polyglot-1.3.4/cup-classes/:./libs/antlr-3.0.1/lib/antlr-3.0.1.jar:./libs/antlr-3.0.1/lib/antlr-runtime-3.0.1.jar:./libs/antlr-3.0.1/lib/stringtemplate-3.1b1.jar:./libs/antlr-3.0.1/lib/antlr-2.7.7.jar:./bin:./src:./src/expressions:./src/functions:./src/statements:./src/types:./src/visitors:./src/types:./src/elements:./src/constants:./src/atoms:./src/util:./src/definitions:./src/ir