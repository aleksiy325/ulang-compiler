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

program returns [Program p]
@init
{
  p = new Program();
} 
  :
  ( f=function { p.add(f); } )+ EOF
  ;

function returns [Function f]
  : 
  decl=functionDecl body=functionBody { f = new Function(decl, body); }
  ;

functionDecl returns [FunctionDeclaration decl]
  : 
  ctype=compoundType id=identifier '(' params=formalParameters ')' { decl = new FunctionDeclaration(ctype, id, params); } 
  ;

formalParameters returns [FormalParameterList params]
@init
{
  params = new FormalParameterList();
} 
  : ctype=compoundType id=identifier { params.add(new FormalParameter(ctype, id)); } ( param=moreFormals { params.add(param); } )* 
  |
  ;

moreFormals returns [ FormalParameter param ]
  : ',' ctype=compoundType id=identifier { param = new FormalParameter(ctype, id); }
  ;

functionBody returns [ FunctionBody body ]
@init
{
  body = new FunctionBody();
} 
  : 
  '{' (vdecl=varDecl { body.addVarDecl(vdecl); })* (stmt=statement { body.addStatement(stmt); } )* '}'
  ;

varDecl returns [ VariableDeclaration vdecl]
  : 
  ctype=compoundType id=identifier ';' { vdecl = new VariableDeclaration(ctype, id); }
  ;

compoundType returns [ CompoundType ctype ]
  : ttype=type { ctype = new CompoundType(ttype); } ( '[' lint=integerConstant ']' { ctype.makeArray(lint); } )? 
  ;

statement returns [ Statement s ]
options { backtrack = true; }
  : ';' { s = new SimpleStatement(); } 
  | lexpr=expr ';' { s = new SimpleStatement(lexpr); }
  | 'if' '(' lexpr=expr ')' ifblock=block  { s = new IfElseStatement(lexpr, ifblock); } ( 'else' elseblock=block { s = new IfElseStatement(lexpr, ifblock, elseblock); })? 
  | 'while' '(' lexpr=expr ')' lblock=block {s = new WhileStatement(lexpr, lblock); }
  | 'print' lexpr=expr ';' { s = new PrintStatement(lexpr); }
  | 'println' lexpr=expr ';' { s = new PrintlnStatement(lexpr); }
  | 'return' ';' { s = new ReturnStatement(); } 
  | 'return' lexpr=expr ';' { s = new ReturnStatement(lexpr); }
  | id=identifier '=' lexpr=expr ';' { s = new AssignmentStatement(id, lexpr); }
  | id=identifier '[' size=expr ']' '=' lexpr=expr ';' { s = new AssignmentStatement(id, size, lexpr); }
  ;

block returns [ Block blockList ]
@init
{
  blockList = new Block();
} 
  : 
  '{' (stmt=statement { blockList.add(stmt); })* '}' 
  ;

exprList returns [ ExpressionList exprlist ]
@init
{
  exprlist = new ExpressionList();
} 
  : lexpr=expr { exprlist.add(lexpr); } (mexpr=exprMore { exprlist.add(mexpr); })*  
  | 
  ;

exprMore returns [ Expression rexpr ]
  : ',' lexpr=expr  { rexpr = lexpr; }
  ;

expr returns [ Expression rexpr ]
  : lexpr=cmpExpr { rexpr = lexpr; }
  ;

cmpExpr returns [ CompareExpression cexpr ]
  : lexpr=lessExpr { cexpr = new CompareExpression(lexpr); } ( '==' rexpr=lessExpr { cexpr = new CompareExpression(cexpr, rexpr); } )* 
  ;

lessExpr returns [ LessThanExpression ltexpr ]
  : lexpr=plusMinusExpr { ltexpr = new LessThanExpression(lexpr); } ( '<' rexpr=plusMinusExpr { ltexpr = new LessThanExpression(ltexpr, rexpr);  } )* 
  ;

plusMinusExpr returns [ PlusMinusExpression pmexpr ]
  : lexpr=multiExpr  { pmexpr = new PlusMinusExpression(lexpr); } ( op=( '+' | '-' ) rexpr=multiExpr { pmexpr =  new PlusMinusExpression(pmexpr, rexpr, op.getText()); } )* 
  ;

multiExpr returns [ MultiplicationExpression mexpr ]
  : la=atom { mexpr = new MultiplicationExpression(la); } ( '*' ra=atom  { mexpr = new MultiplicationExpression(mexpr, ra); } )* 
  ;

atom returns [ Atom atom ]
  : cons=constant { atom = cons; }
  | '(' lexpr=expr ')' { atom = lexpr; }
  | id=identifier '(' exprl=exprList ')' { atom = new FunctionCall(id, exprl); }
  | id=identifier '[' lexpr=expr ']' { atom = new ArrayDereference(id, lexpr); }
  | id=identifier  { atom = new VariableDereference(id); }
  ;

identifier returns [ Identifier id ]
  : val=ID_LITERAL { id = new Identifier(val.getText()); } 
  ;

type returns [ Type t]
  : val=TYPE { t = new Type(val.getText()); } 
  ;

TYPE
  : 'int'
  | 'float'
  | 'char'
  | 'string'
  | 'boolean'
  | 'void'
  ;

constant returns [ Constant cons ]
  : ic=integerConstant { cons = ic; }
  | sc=stringConstant { cons = sc; }
  | fc=floatConstant { cons = fc; }
  | cc=charConstant { cons = cc; }
  | bc=booleanConstant { cons = bc; }
  ;

integerConstant returns [ IntegerConstant ic ]
  : val=INTEGER_LITERAL { ic = new IntegerConstant(Integer.parseInt(val.getText()), "int"); } 
  ;

stringConstant returns [ StringConstant sc ]
  : val=STRING_LITERAL { sc = new StringConstant(val.getText(), "string"); }
  ; 

floatConstant returns [ FloatConstant fc ]
  : val=FLOAT_LITERAL { fc = new FloatConstant(Float.parseFloat(val.getText()), "float"); }
  ;

charConstant returns [ CharConstant cc ]
  : val=CHAR_LITERAL { cc = new CharConstant(val.getText().charAt(1), "char"); }
  ;

booleanConstant returns [ BooleanConstant bc]
  : val=BOOL_LITERAL { bc = new BooleanConstant(Boolean.parseBoolean(val.getText()), "boolean"); }
  ;

BOOL_LITERAL : ('true' | 'false') ;

ID_LITERAL  : LETTER ( LETTER | DIGIT )* ;

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
