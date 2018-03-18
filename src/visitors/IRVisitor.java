import java.util.ArrayList;

public interface IRVisitor {
    IRProgram visit (Program prog);
    IRFunction visit (Function func);
    ArrayList<IRTemp> visit (FormalParameterList params);
    IRTemp visit (FormalParameter param);
    IRFunction visit (FunctionDeclaration funcdecl);
    int visit (Identifier id);
    String visit (Type type);
    String visit (PrimitiveType type);
    IRBody visit (FunctionBody body);
    IRTemp visit (VariableDeclaration vdecl);
    String visit (SimpleStatement stmt);
    String visit (BooleanConstant bool);
    String visit (CharConstant character);
    String visit (FloatConstant cfloat);
    String visit (IntegerConstant cint);
    String visit (StringConstant cstring);
    String visit (AssignmentStatement astmt);
}