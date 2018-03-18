import java.util.HashMap;
import java.util.ArrayList;

public class IRGenerator implements IRVisitor {
    HashMap<PrimitiveType, String> typeMap;
    IRScope scope;

    public IRGenerator() {
        scope = new IRScope();
        typeMap = new HashMap<PrimitiveType, String>();
        typeMap.put(TypeDefs.integerPrimitive, "I");
        typeMap.put(TypeDefs.floatPrimitive, "F");
        typeMap.put(TypeDefs.charPrimitive, "C");
        typeMap.put(TypeDefs.stringPrimitive, "U");
        typeMap.put(TypeDefs.booleanPrimitive, "Z");
        typeMap.put(TypeDefs.voidPrimitive, "V");
    }

    public IRProgram visit (Program prog) {
        IRProgram program = new IRProgram();
        for (int i = 0; i < prog.size(); i++) {
            program.addFunction(prog.get(i).accept(this));
        }
        return program;
    }

    public IRFunction visit (Function func) {
        this.scope.reset();
        IRFunction function = func.decl.accept(this);
        function.addBody(func.body.accept(this));
        return function;
    }

    public IRFunction visit (FunctionDeclaration fdecl) {
        String irftype = fdecl.type.accept(this);
        ArrayList<IRTemp> irparams = fdecl.params.accept(this);
        return new IRFunction(fdecl.id, irparams, irftype);
    }


    public ArrayList<IRTemp> visit (FormalParameterList params) {
        ArrayList<IRTemp> irparams = new ArrayList<IRTemp>();
        for (int i = 0; i < params.size(); i++) {
            irparams.add(params.get(i).accept(this));
        }
        return irparams;
    }

    public IRTemp visit (FormalParameter param) {
        int id = param.id.accept(this);
        String type = param.type.accept(this);
        return new IRTemp(id, type);
    }

    public int visit (Identifier id) {
        int symbol;
        if (this.scope.containsVariable(id)) {
            symbol = this.scope.getVariableSymbol(id);
        } else {
            symbol = this.scope.putVariable(id);
        }
        return symbol;
    }

    public String visit (Type type) {
        String t = type.primType.accept(this);
        if (type.isArray) {
            return "A" + t;
        }
        return t;
    }

    public String visit (PrimitiveType type) {
        return this.typeMap.get(type);
    }

    public IRBody visit (FunctionBody body) {
        IRBody irb = new IRBody();

        for (int i = 0; i < body.varDeclSize(); i++) {
            irb.addTemp(body.getVarDecl(i).accept(this));
        }
        for (int i = 0; i < body.statementSize(); i++) {
            irb.addStatement(body.getStatementList(i).accept(this));
        }

        return irb;
    }

    public IRTemp visit (VariableDeclaration vdecl) {
        String t = vdecl.type.accept(this);
        int id = vdecl.id.accept(this);
        return new IRTemp(id, t);
    }

    public String visit (BooleanConstant bool) {
        if (bool.val) {
            return "TRUE";
        }
        return "FALSE";
    }

    public String visit (CharConstant character) {
        return String.valueOf(character.val);
    }

    public String visit (FloatConstant cfloat) {
        return String.valueOf(cfloat.val);
    }

    public String visit (IntegerConstant cint) {
        return String.valueOf(cint.val);
    }

    public String visit (StringConstant cstring) {
        //TODO: fix
        return cstring.val;
    }

    public String visit (SimpleStatement stmt) {
        if (!stmt.isEmpty) {
            return stmt.expr.accept(this);
        }
        return "";
    }

    public String visit (AssignmentStatement astmt) {
        int id = astmt.id.accept(this);
        String expr = astmt.expr.accept(this);
        return String.valueOf(id) + " := " + expr;
    }

    public int visit (MultiplicationExpression mexpr) {
        return 0;
    }

}