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

functionDecl: TYPE ID '(' ')' ;

functionBody: '{' varDecl* statement*'}' ;

varDecl: compoundType ID ';' ;

compoundType: TYPE ('[' INTEGER ']') ? ;

statement : expr ';' ;

expr
  : baseExpr OP expr
  | ID
  | LITERAL
  ;

baseExpr 
  : 
  | ID
  | LITERAL
  | ID '[' expr ']'
  ;

/* Lexer */
TYPE
  : 'int'
  | 'float'
  | 'char'
  | 'string'
  | 'boolean'
  | 'void'
  ;

LITERAL 
  : INTEGER
  | STRING
  | FLOAT
  | CHAR
  | 'true'
  | 'false'
  ;

OP 
  : CMP
  | LESS
  | PLUS
  | MINUS 
  | TIMES
  ;

ID  : LETTER ( LETTER | DIGIT )* ;

INTEGER : DIGIT * ;

STRING : '"' ( LETTER | DIGIT )* '"' ;

FLOAT : DIGIT+ '.' DIGIT+ ;

CHAR : '\'' ( LETTER | DIGIT )? '\'' ;

CMP : '==' ;

LESS : '<' ;

PLUS : '+' ;

MINUS : '-' ;

TIMES : '*' ;

fragment LETTER : ('A'..'Z' | 'a'..'z' | '_') ;

fragment DIGIT : '0'..'9' ;

WS      : ( '\t' | ' ' | ('\r' | '\n') )+ { $channel = HIDDEN;}
        ;

COMMENT : '//' ~('\r' | '\n')* ('\r' | '\n') { $channel = HIDDEN;}
        ;