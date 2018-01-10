
GNAME= ulNoActions
GSRC= $(GNAME).g
BIN = bin
SRC = src
TESTS = tests


all: grammar compiler

grammar: $(GSRCS)
	java org.antlr.Tool -fo $(BIN) $(SRC)/$(GSRC) 

compiler:
	mkdir -p $(BIN)
	javac -d $(BIN) $(BIN)/*.java
	javac -d $(BIN) $(SRC)/*.java

clean:
	rm -rf bin

test:
	find tests -name *.ul | xargs java -classpath $(CLASSPATH):$(BIN) Compiler
 