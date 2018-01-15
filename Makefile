SHELL := /bin/bash
GNAME= ulang
GSRC= $(GNAME).g
BIN = bin
SRC = src
TESTS = tests
PROG = Compiler
ACCEPT = $(TESTS)/accept
ACCEPT_LINE = $(TESTS)/accept_line
REJECT_LINE = $(TESTS)/reject_line
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
	rm -rf $(BIN)
	rm -rf $(ACCEPT_LINE)
	rm -rf $(REJECT_LINE)

generate: 
	mkdir -p $(ACCEPT_LINE)
	mkdir -p $(REJECT_LINE)
	cp $(ACCEPT)/*.ul $(ACCEPT_LINE)/
	@ let count=0; while read line; do echo -e "void main(){\n	$$line}" > "$(ACCEPT_LINE)/accept_$$count.ul"; ((count++)) ; done < tests/accept.txt
	@ let count=0; while read line; do echo -e "void main(){\n	$$line}" > "$(REJECT_LINE)/reject_$$count.ul"; ((count++)) ; done < tests/reject.txt

accept:
	@echo "---------------- Accept Tests ----------------"
	@for f in ./$(ACCEPT_LINE)/*.ul; do java $(PROG) $$f; if [ $$? -eq 0 ]; then echo -e "${GREEN}PASSED${END} $$?" $$f; else echo -e "${RED}FAILED${END} $$?" $$f; fi done

reject:
	@echo "---------------- Reject Tests ----------------"
	@for f in $(REJECT_LINE)/*.ul; do java $(PROG) $$f; if [ $$? -eq 0 ]; then echo -e "${RED}FAILED${END} $$?" $$f;  else echo -e "${GREEN}PASSED${END} $$?" $$f; fi done 

test: generate accept reject
	

