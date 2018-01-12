grammar ulNoActions;

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

program : function +;

function: functionDecl functionBody ;

functionDecl: compoundType identifier '(' formalParameters* ')' ;

formalParameters : compoundType identifier moreFormals* ;

moreFormals : ',' compoundType identifier ;

functionBody: '{' varDecl* statement* '}' ;

varDecl: compoundType identifier ';' ;

compoundType  : type ( '[' INTEGER_LITERAL ']' )? ;

statement
  : ';'
  | expr ';'
  | 'if' '(' expr ')' block ( 'else' block )?
  | 'while' '(' expr ')' block
  | 'print' expr ';'
  | 'println' expr ';'
  | 'return' expr ';'
  | identifier ( '[' expr ']' )? '=' expr ';'
  ;

block : '{' statement* '}' ;

exprList : expr exprMore* ;

exprMore : ',' expr;

expr : cmpExpr ;

cmpExpr : lessExpr ( '==' lessExpr )* ;

lessExpr : plusMinusExpr ( '<' plusMinusExpr )* ;

plusMinusExpr : multiExpr ( ( '+' | '-' ) multiExpr )* ;

multiExpr : atom ( '*' atom )* ;

atom
  :
  | constant
  | '(' expr ')'
  | identifier
  | identifier '(' exprList ')'

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

STRING_LITERAL : '"' ( LETTER | DIGIT )* '"' ;

FLOAT_LITERAL : DIGIT+ '.' DIGIT+ ;

CHAR_LITERAL : '\'' ( LETTER | DIGIT )? '\'' ;

fragment LETTER : ('A'..'Z' | 'a'..'z' | '_') ;

fragment DIGIT : '0'..'9' ;

WS      : ( '\t' | ' ' | ('\r' | '\n') )+ { $channel = HIDDEN;}
        ;

COMMENT : '//' ~('\r' | '\n')* ('\r' | '\n') { $channel = HIDDEN;}
        ;
