import java.util.ArrayList;

public interface IRVisitor {
    IRProgram visit (Program prog);
    IRFunction visit (Function func);
    ArrayList<IRTemp> visit (FormalParameterList params);
    ArrayList<IRTemp> visit (ExpressionList params);
    IRTemp visit (FormalParameter param);
    IRFunction visit (FunctionDeclaration funcdecl);
    IRTemp visit (Identifier id);
    String visit (Type type);
    String visit (PrimitiveType type);
    IRBody visit (FunctionBody body);
    IRTemp visit (VariableDeclaration vdecl);
    IRTemp visit (Statement stmt);
    IRTemp visit (SimpleStatement stmt);
    IRTemp visit (IfElseStatement stmt);
    IRTemp visit (PrintStatement stmt);
    IRTemp visit (PrintlnStatement stmt);
    IRTemp visit (WhileStatement stmt);
    IRTemp visit (ReturnStatement ret);
    IRTemp visit (BooleanConstant bool);
    IRTemp visit (CharConstant character);
    IRTemp visit (FloatConstant cfloat);
    IRTemp visit (IntegerConstant cint);
    IRTemp visit (StringConstant cstring);
    IRTemp visit (AssignmentStatement astmt);
    IRTemp visit (CompareExpression cmpexpr);
    IRTemp visit (LessThanExpression ltexpr);
    IRTemp visit (MultiplicationExpression mexpr);
    IRTemp visit (PlusMinusExpression pmexpr);
    IRTemp visit (Expression expr);
    IRTemp visit (VariableDereference varderef);
    IRTemp visit (ArrayDereference deref);
    IRTemp visit (FunctionCall call);
    IRTemp visit (Block block);
}