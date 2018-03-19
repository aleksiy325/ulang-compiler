import java.util.HashMap;
import java.util.ArrayList;

public class IRGenerator implements IRVisitor {
    HashMap<PrimitiveType, String> typeMap;
    IRScope scope;
    Scope funcScope;
    ArrayList<String> lines;
    String curFuncRetType = "V";
    String name;

    public IRGenerator(String name) {
        this.name = name;
        scope = new IRScope();
        funcScope = new Scope();
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
        IRProgram program = new IRProgram(this.name);
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
        this.funcScope.putGlobalFunction(fdecl.id, new FunctionSignature(fdecl.type));
        return new IRFunction(fdecl.id, irparams, irftype);
    }


    public ArrayList<IRTemp> visit (FormalParameterList params) {
        ArrayList<IRTemp> irparams = new ArrayList<IRTemp>();
        for (int i = 0; i < params.size(); i++) {
            irparams.add(params.get(i).accept(this));
        }
        return irparams;
    }

    public ArrayList<IRTemp> visit (ExpressionList exprs) {
        ArrayList<IRTemp> temps = new ArrayList<IRTemp>();
        for (int i = 0; i < exprs.size(); i++) {
            temps.add(exprs.get(i).accept(this));
        }
        return temps;
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
        IRTemp temp = this.scope.newTemp(vdecl.id, t);
        if (vdecl.type.isArray) {
            String size = String.valueOf(vdecl.type.size.val);
            String arrType = temp.getArrayType();
            this.lines.add(temp + " := NEWARRAY " + arrType + size + ";");
        }
        return temp;
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
        this.lines.add(temp.toString() + " := " + "'" + String.valueOf(character.val) + "'" + ";");
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
        if (astmt.isArray) {
            IRTemp deref = astmt.deref.expr.accept(this);
            this.lines.add(n + "[" + deref + "]" + " := " + t + ";");
        } else {
            this.lines.add(n + " := " + t + ";");
        }
        return n;
    }

    public IRTemp visit (IfElseStatement stmt) {
        IRLabel clbl = this.scope.newLabel();
        IRTemp cond = stmt.expr.accept(this);
        this.lines.add(cond + " := " + cond.type + "! " + cond  + ";");
        this.lines.add("IF " + cond + " GOTO " + clbl + ";");
        stmt.ifblock.accept(this);
        if (stmt.hasElse) {
            IRLabel elbl = this.scope.newLabel();
            this.lines.add("GOTO " + elbl + ";");
            this.lines.add(clbl + ":;");
            stmt.elseblock.accept(this);
            this.lines.add(elbl + ":;");
        } else {
            this.lines.add(clbl + ":;");
        }
        return new IRTemp();
    }

    public IRTemp visit (PrintStatement stmt) {
        IRTemp t = stmt.expr.accept(this);
        this.lines.add("PRINT" + t.type + " " + t + ";");
        return t;
    }

    public IRTemp visit (PrintlnStatement stmt) {
        IRTemp t = stmt.expr.accept(this);
        this.lines.add("PRINTLN" + t.type + " " + t + ";");
        return t;
    }

    public IRTemp visit (WhileStatement stmt) {
        IRLabel enter = this.scope.newLabel();
        IRLabel exit = this.scope.newLabel();
        this.lines.add(enter + ":;");
        IRTemp cond = stmt.expr.accept(this);
        this.lines.add(cond + " := " + cond.type + "! " + cond  + ";");
        this.lines.add("IF " + cond + " GOTO " + exit + ";");
        stmt.block.accept(this);
        this.lines.add("GOTO " + enter + ";");
        this.lines.add(exit  + ":;");
        return new IRTemp();
    }

    public IRTemp visit (CompareExpression expr) {
        IRTemp left = expr.left.accept(this);
        if ( expr.right != null ) {
            IRTemp right = expr.right.accept(this);
            IRTemp ret = this.scope.newTemp("Z");
            this.lines.add(ret + " := " + left + " " + left.type + "== " + right + ";");
            return ret;
        }
        return left;
    }

    public IRTemp visit (LessThanExpression expr) {
        IRTemp left = expr.left.accept(this);
        if ( expr.right != null ) {
            IRTemp right = expr.right.accept(this);
            IRTemp ret = this.scope.newTemp("Z");
            this.lines.add(ret + " := " + left + " " + left.type + "< " + right + ";");
            return ret;
        }
        return left;
    }

    public IRTemp visit (MultiplicationExpression expr) {
        IRTemp left = expr.left.accept(this);
        if ( expr.right != null ) {
            IRTemp right = expr.right.accept(this);
            IRTemp ret = this.scope.newTemp(right.type);
            this.lines.add(ret + " := " + left + " " + left.type + "* " + right + ";");
            return ret;
        }
        return left;
    }

    public IRTemp visit (PlusMinusExpression expr) {
        IRTemp left = expr.left.accept(this);
        if ( expr.right != null ) {
            IRTemp right = expr.right.accept(this);
            String sign = "-";
            if (expr.isAddition) {
                sign = "+";
            }
            IRTemp ret = this.scope.newTemp(right.type);
            this.lines.add(ret + " := " + left + " " + left.type + sign + " " + right + ";");
            return ret;
        }
        return left;
    }

    public IRTemp visit (Expression expr) {
        return new IRTemp();
    }

    public IRTemp visit (ArrayDereference deref) {
        IRTemp array = this.scope.getVariableSymbol(deref.id);
        IRTemp expr = deref.expr.accept(this);
        String arrType = array.getArrayType();
        IRTemp temp = this.scope.newTemp(arrType);
        this.lines.add(temp + " := " + array + "[" + expr + "];");
        return temp;
    }

    public IRTemp visit (VariableDereference deref) {
        return deref.id.accept(this);
    }

    public IRTemp visit (FunctionCall call) {
        IRTemp ret = new IRTemp();
        String line = "CALL " + call.id.val + "(";
        Type retType = this.funcScope.getFunctionSignature(call.id).type;
        if (!retType.equals(TypeDefs.voidType)) {
            ret = this.scope.newTemp(retType.accept(this));
            line = ret + " := " + line;
        }
        ArrayList<IRTemp> args = call.exprs.accept(this);
        for (IRTemp arg : args) {
            line += arg;
        }
        this.lines.add(line + ");");
        return ret;
    }

    public IRTemp visit (Block block) {
        for (int i = 0; i < block.size(); i++) {
            block.get(i).accept(this);
        }
        return new IRTemp();
    }

    public IRTemp visit (ReturnStatement ret) {
        String line = "RETURN";
        if (!ret.isEmpty) {
            IRTemp t = ret.expr.accept(this);
            line += " " + t + ";";
            this.lines.add(line);
            return t;
        }
        this.lines.add(line + ";");
        return new IRTemp();
    }

}