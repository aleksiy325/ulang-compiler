grammar ulang;

@header {
  import java.util.LinkedList;
}

@members
{
protected void mismatch (IntStream input, int ttype, BitSet follow)
        throws RecognitionException
{
        throw new MismatchedTokenException(ttype, input);
}
public void recoverFromMismatchedSet (IntStream input,
                                      RecognitionException e,
                                      BitSet follow)
        throws RecognitionException
{
        reportError(e);
        throw e;
}
}

@rulecatch {
        catch (RecognitionException ex) {
                reportError(ex);
                throw ex;
        }
}

program : function+ EOF ;

function: functionDecl functionBody ;

functionDecl: compoundType identifier '(' formalParameters ')' ;

formalParameters 
  : compoundType identifier moreFormals*
  |
  ;

moreFormals : ',' compoundType ID ;

functionBody: '{' varDecl* statement* '}' ;

varDecl: compoundType identifier ';' ;

compoundType  : type ( '[' INTEGER_LITERAL ']' )? ;

statement
options { backtrack = true; }
  : ';'
  | expr ';'
  | 'if' '(' expr ')' block ( 'else' block )?
  | 'while' '(' expr ')' block
  | 'print' expr ';'
  | 'println' expr ';'
  | 'return' expr? ';'
  | identifier '=' expr ';'
  | identifier '[' expr ']' '=' expr ';'
  ;

block : '{' statement* '}' ;

exprList 
  : expr exprMore* 
  | 
  ;

exprMore : ',' expr ;

expr : cmpExpr;

cmpExpr : lessExpr ( '==' lessExpr )* ;

lessExpr : plusMinusExpr ( '<' plusMinusExpr )* ;

plusMinusExpr : multiExpr ( ( '+' | '-' ) multiExpr )* ;

multiExpr : atom ( '*' atom )* ;

atom
  : constant
  | '(' expr ')'
  | identifier '(' exprList ')'
  | identifier '[' expr ']'
  | identifier
  ;

identifier : ID ;

type : TYPE ;

TYPE
  : 'int'
  | 'float'
  | 'char'
  | 'string'
  | 'boolean'
  | 'void'
  ;

constant
  : INTEGER_LITERAL
  | STRING_LITERAL
  | FLOAT_LITERAL
  | CHAR_LITERAL
  | 'true'
  | 'false'
  ;

ID  : LETTER ( LETTER | DIGIT )* ;

INTEGER_LITERAL : DIGIT+ ;

STRING_LITERAL : '"' STRING_GUTS '"' ;

FLOAT_LITERAL : DIGIT+ '.' DIGIT+ ;

CHAR_LITERAL : '\'' ~('\\'|'\'' | ' ') '\'' ;

fragment STRING_GUTS : ( ~('\\'|'"') )* ;

fragment LETTER : ('A'..'Z' | 'a'..'z' | '_') ;

fragment DIGIT : '0'..'9' ;

WS      : ( '\t' | ' ' | ('\r' | '\n') )+ { $channel = HIDDEN;}
        ;

COMMENT : '//' ~('\r' | '\n')* ('\r' | '\n' | EOF) { $channel = HIDDEN;}
        ;
