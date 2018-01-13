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
	rm -rf tests/accept
	rm -rf tests/reject

generate: 
	mkdir -p tests/accept
	mkdir -p tests/reject
	@ let count=0; while read line; do echo -e "void main(){\n	$$line}" > "tests/accept/accept_$$count.ul"; ((count++)) ; done < tests/accept.txt
	@ let count=0; while read line; do echo -e "void main(){\n	$$line}" > "tests/reject/reject_$$count.ul"; ((count++)) ; done < tests/reject.txt

test: generate
	@echo "---------------- Accept Tests ----------------"
	@for f in tests/accept/*.ul; do java $(PROG) $$f; if [ $$? -eq 0 ]; then echo -e "${GREEN}PASSED${END} $$?" $$f; else echo -e "${RED}FAILED${END} $$?" $$f; fi done
	@echo "---------------- Reject Tests ----------------"
	@for f in tests/reject/*.ul; do java $(PROG) $$f; if [ $$? -eq 0 ]; then echo -e "${RED}FAILED${END} $$?" $$f;  else echo -e "${GREEN}PASSED${END} $$?" $$f; fi done
