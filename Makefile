
GNAME= ulNoActions
GSRC= $(GNAME).g
BIN = bin
SRC = src
TESTS = tests
PROG = Compiler


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
	@echo "ACCEPT"
	for f in tests/accept/*.ul; do echo $$f; java $(PROG) $$f; done
	@echo "REJECT"
	for f in tests/reject/*.ul; do echo $$f; java $(PROG) $$f; done
