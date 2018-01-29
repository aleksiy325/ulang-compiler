# Compiler

### Compiling
Add `./bin:./src:./src/expressions:./src/functions:./src/statements:./src/types:./src/visitors:./src/types:./src/elements:./src/constants:./src/atoms` to classpath then `make`

### Generating Tests
`make generate` to generate some cases from txt files so that they can be tested indepedently. `make test` will call this Make rule. 

### Running Grammar Tests
`make test`

Currently having a weird problem where for some errors the exception isn't passed all the way up but still reported. This casuses the program to terminate normally and the script to think it failed. However, an error is reported to the log. I haven't quite been able to fix this.

### Running Pretty Printer Tests
`make pretty`

You can also `make run` to print `sample.ul` program.
