import java.util.HashMap;
import java.util.ArrayList;

public class IRGenerator implements IRVisitor {
    HashMap<PrimitiveType, String> typeMap;
    IRScope scope;
    ArrayList<String> lines;

    public IRGenerator() {
        scope = new IRScope();
        typeMap = new HashMap<PrimitiveType, String>();
        typeMap.put(TypeDefs.integerPrimitive, "I");
        typeMap.put(TypeDefs.floatPrimitive, "F");
        typeMap.put(TypeDefs.charPrimitive, "C");
        typeMap.put(TypeDefs.stringPrimitive, "U");
        typeMap.put(TypeDefs.booleanPrimitive, "Z");
        typeMap.put(TypeDefs.voidPrimitive, "V");

        this.lines = new ArrayList<String>();

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
        String type = param.type.accept(this);
        return this.scope.newTemp(param.id, type);
    }

    public IRTemp visit (Identifier id) {
        return this.scope.getVariableSymbol(id);
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
            body.getVarDecl(i).accept(this);
        }
        for (int i = 0; i < body.statementSize(); i++) {
            body.getStatementList(i).accept(this);
        }
        irb.addTemps(this.scope.getTemps());
        irb.addLines(this.lines);
        this.lines = new ArrayList<String>();


        return irb;
    }

    public IRTemp visit (VariableDeclaration vdecl) {
        String t = vdecl.type.accept(this);
        return this.scope.newTemp(vdecl.id, t);
    }

    public IRTemp visit (BooleanConstant bool) {
        IRTemp temp = this.scope.newTemp("Z");
        if (bool.val) {
            this.lines.add(temp.toString() + " := " + "TRUE" + ";");
        } else {
            this.lines.add(temp.toString() + " := " + "FALSE" + ";");
        }
        return temp;
    }

    public IRTemp visit (CharConstant character) {
        IRTemp temp = this.scope.newTemp("C");
        this.lines.add(temp.toString() + " := " + String.valueOf(character.val) + ";");
        return temp;
    }

    public IRTemp visit (FloatConstant cfloat) {
        IRTemp temp = this.scope.newTemp("F");
        this.lines.add(temp.toString() + " := " + String.valueOf(cfloat.val) + ";");
        return temp;
    }

    public IRTemp visit (IntegerConstant cint) {
        IRTemp temp = this.scope.newTemp("I");
        this.lines.add(temp.toString() + " := " + String.valueOf(cint.val) + ";");
        return temp;
    }

    public IRTemp visit (StringConstant cstring) {
        IRTemp temp = this.scope.newTemp("U");
        this.lines.add(temp.toString() + " := " + cstring.val + ";");
        return temp;
    }

    public IRTemp visit (Statement stmt) {
        return new IRTemp();
    }

    public IRTemp visit (SimpleStatement stmt) {
        if (!stmt.isEmpty) {
            return stmt.expr.accept(this);
        }
        return new IRTemp();
    }

    public IRTemp visit (AssignmentStatement astmt) {
        IRTemp n = astmt.id.accept(this);
        IRTemp t = astmt.expr.accept(this);
        //String.valueOf(id) + " := " + expr + ";";
        return n;
    }

    public IRTemp visit (IfElseStatement stmt) {
        return new IRTemp();
    }

    public IRTemp visit (PrintStatement stmt) {
        return new IRTemp();
    }

    public IRTemp visit (PrintlnStatement stmt) {
        return new IRTemp();
    }

    public IRTemp visit (WhileStatement stmt) {
        return new IRTemp();
    }

    public IRTemp visit (CompareExpression expr) {
        IRTemp left = expr.left.accept(this);
        if ( expr.right != null ) {
            IRTemp right = expr.right.accept(this);
            this.lines.add(left.toString() + " := " + left.toString() + " " + left.type + "== " + right.toString() + ";");
        }
        return left;
    }

    public IRTemp visit (LessThanExpression expr) {
        IRTemp left = expr.left.accept(this);
        if ( expr.right != null ) {
            IRTemp right = expr.right.accept(this);
        }
        return left;
    }

    public IRTemp    visit (MultiplicationExpression expr) {
        IRTemp left = expr.left.accept(this);
        if ( expr.right != null ) {
            IRTemp right = expr.right.accept(this);
        }
        return left;
    }

    public IRTemp visit (PlusMinusExpression expr) {
        IRTemp left = expr.left.accept(this);
        if ( expr.right != null ) {
            IRTemp right = expr.right.accept(this);
        }
        return left;
    }

    public IRTemp visit (Expression expr) {
        return new IRTemp();
    }

    public IRTemp visit (ArrayDereference deref) {
        return new IRTemp();
    }

    public IRTemp visit (VariableDereference deref) {
        return new IRTemp();
    }


    public IRTemp visit (FunctionCall call) {
        return new IRTemp();
    }

}