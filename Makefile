SHELL := /bin/bash
GNAME= ulang
GSRC= $(GNAME).g
BIN = bin
SRC = src
TESTS = tests
PROG = Compiler
GREEN=\e[0;32m
RED=\033[0;31m
END=\e[0m


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
	@echo "accept"
	@for f in tests/accept/*.ul; do java $(PROG) $$f; if [ $$? -eq 0 ]; then echo -e "${GREEN}PASSED${END}" $$f; else echo -e "${RED}FAILED${END}" $$f; fi done
	@echo "reject"
	@for f in tests/reject/*.ul; do java $(PROG) $$f; if [ $$? -eq 0 ]; then echo -e "${RED}FAILED${END}" $$f;  else echo -e "${GREEN}PASSED${END}" $$f; fi done
