# Compiler

### Compiling
Add `./bin` to classpath then `make`

### Generating Tests
`make generate` to generate some cases from txt files so that they can be tested indepedently. `make test` will call this Make rule. 

### Running Tests
`make test`

Currently having a weird problem where for some errors the exception isn't passed all the way up but still reported. This casuses the program to terminate normally and the script to think it failed. However, an error is reported to the log. I haven't quite been able to fix this.