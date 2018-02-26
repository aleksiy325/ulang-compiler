import java.util.ArrayList;

public interface TypeVisitor {
    Type visit (ArrayDereference arrd);
    Type visit (AssignmentStatement astmt);
    Type visit (Block block);
    Type visit (BooleanConstant bool);
    Type visit (CharConstant character);
    Type visit (CompareExpression cmpexpr);
    ArrayList<Type> visit (ExpressionList exprlist);
    Type visit (FloatConstant cfloat);
    Type visit (FormalParameter param);
    ArrayList<Type>  visit (FormalParameterList params);
    Type visit (Function func);
    Type visit (FunctionBody body);
    Type visit (FunctionCall funccall);
    Type visit (FunctionDeclaration funcdecl);
    Type visit (Identifier id);
    Type visit (IfElseStatement ifelsestmt);
    Type visit (IntegerConstant cint);
    Type visit (LessThanExpression ltexpr);
    Type visit (MultiplicationExpression mexpr);
    Type visit (PlusMinusExpression pmexpr);
    Type visit (PrintlnStatement println);
    Type visit (PrintStatement print);
    Type visit (Program prog);
    Type visit (ReturnStatement retstmt);
    Type visit (SimpleStatement stmt);
    Type visit (StringConstant cstring);
    Type visit (Type type);
    Type visit (PrimitiveType type);
    Type visit (VariableDeclaration vardecl);
    Type visit (VariableDereference varderef);
    Type visit (WhileStatement whilestmt);
}