import java.util.HashMap;
import java.util.ArrayList;

public class JVMGenerator implements JVMVisitor {
    HashMap<PrimitiveType, String> typeMap;
    HashMap<String, String> aTypeMap;
    IRScope scope;
    Scope funcScope;
    ArrayList<String> lines;
    String curFuncRetType = "V";
    String name;

    public JVMGenerator(String name) {
        this.name = name;
        scope = new IRScope();
        funcScope = new Scope();
        typeMap = new HashMap<PrimitiveType, String>();
        typeMap.put(TypeDefs.integerPrimitive, "i");
        typeMap.put(TypeDefs.floatPrimitive, "f");
        typeMap.put(TypeDefs.charPrimitive, "i");
        typeMap.put(TypeDefs.stringPrimitive, "i");
        typeMap.put(TypeDefs.booleanPrimitive, "i"); //TODO check
        typeMap.put(TypeDefs.voidPrimitive, "V");

        aTypeMap = new HashMap<String, String>();
        aTypeMap.put("i", "int");
        aTypeMap.put("f", "float");

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
            String arrType = aTypeMap.get(temp.getArrayType());
            this.lines.add("ldc " + size);
            this.lines.add("newarray " + arrType);
        } else{
            // TODO: add float?
            this.lines.add("ldc 0");
            this.lines.add(temp.type + "store " + temp.toNString());
        }
        return temp;
    }

    public IRTemp visit (BooleanConstant bool) {
        IRTemp temp = this.scope.newTemp("i");
        if (bool.val) {
            this.lines.add("ldc 1");
        } else {
            this.lines.add("ldc 0");
        }
        this.lines.add(temp.type + "store " + temp.toNString());
        return temp;
    }

    public IRTemp visit (CharConstant character) {
        IRTemp temp = this.scope.newTemp("i");
        int val = character.val;
        this.lines.add("ldc " + String.valueOf(val));
        this.lines.add(temp.type + "store " + temp.toNString());
        return temp;
    }

    public IRTemp visit (FloatConstant cfloat) {
        IRTemp temp = this.scope.newTemp("f");
        this.lines.add("ldc "  + String.valueOf(cfloat.val));
        this.lines.add(temp.type + "store " + temp.toNString());
        return temp;
    }

    public IRTemp visit (IntegerConstant cint) {
        IRTemp temp = this.scope.newTemp("i");
        this.lines.add("ldc " + String.valueOf(cint.val));
        this.lines.add(temp.type + "store " + temp.toNString());
        return temp;
    }

    public IRTemp visit (StringConstant cstring) {
        IRTemp temp = this.scope.newTemp("ai");
        this.lines.add("ldc " + cstring.val);
        this.lines.add("astore " + temp.toNString());
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
            this.lines.add(n + "[" + deref + "]" + " := " + t + ";"); // TODO: Array assigment
        } else {
            this.lines.add(t.type + "load " + t.toNString());
            this.lines.add(n.type + "store " + n.toNString());
        }
        return n;
    }

    public IRTemp visit (IfElseStatement stmt) {
        IRLabel clbl = this.scope.newLabel();
        IRTemp cond = stmt.expr.accept(this);
        this.lines.add(cond + " := " + cond.type + "! " + cond  + ";");
        this.lines.add("IF " + cond + " goto " + clbl + ";");
        stmt.ifblock.accept(this);
        if (stmt.hasElse) {
            IRLabel elbl = this.scope.newLabel();
            this.lines.add("goto " + elbl + ";");
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
        this.lines.add("IF " + cond + " goto " + exit + ";");
        stmt.block.accept(this);
        this.lines.add("goto " + enter + ";");
        this.lines.add(exit  + ":;");
        return new IRTemp();
    }

    public IRTemp visit (CompareExpression expr) {
        IRTemp left = expr.left.accept(this);
        if ( expr.right != null ) {
            IRTemp right = expr.right.accept(this);
            IRTemp ret = this.scope.newTemp("i");
            IRLabel trueLabel = this.scope.newLabel();
            IRLabel falseLabel = this.scope.newLabel();
            this.lines.add(left.type  + "load " + left.toNString());
            this.lines.add(right.type + "load " + right.toNString());
            this.lines.add(left.type  + "sub");
            this.lines.add("ifeq " + trueLabel.toJVMString());
            this.lines.add("ldc 0");
            this.lines.add("goto " + falseLabel.toJVMString());
            this.lines.add(trueLabel.toJVMString() + ":");
            this.lines.add("ldc 1");
            this.lines.add(falseLabel.toJVMString() + ":");
            this.lines.add(ret.type + "store " + ret.toNString());
            return ret;
        }
        return left;
    }

    public IRTemp visit (LessThanExpression expr) {
        IRTemp left = expr.left.accept(this);
        if ( expr.right != null ) {
            IRTemp right = expr.right.accept(this);
            IRTemp ret = this.scope.newTemp("i");
            IRLabel trueLabel = this.scope.newLabel();
            IRLabel falseLabel = this.scope.newLabel();
            this.lines.add(left.type  + "load " + left.toNString());
            this.lines.add(right.type + "load " + right.toNString());
            this.lines.add(left.type  + "sub");
            this.lines.add("iflt " + trueLabel.toJVMString());
            this.lines.add("ldc 0");
            this.lines.add("goto " + falseLabel.toJVMString());
            this.lines.add(trueLabel.toJVMString() + ":");
            this.lines.add("ldc 1");
            this.lines.add(falseLabel.toJVMString() + ":");
            this.lines.add(ret.type + "store " + ret.toNString());
            return ret;
        }
        return left;
    }

    public IRTemp visit (MultiplicationExpression expr) {
        IRTemp left = expr.left.accept(this);
        if ( expr.right != null ) {
            IRTemp right = expr.right.accept(this);
            IRTemp ret = this.scope.newTemp(right.type);
            this.lines.add(left.type  + "load " + left.toNString());
            this.lines.add(right.type + "load " + right.toNString());
            this.lines.add(right.type + "mul");
            this.lines.add(ret.type + "store " + ret.toNString());
            return ret;
        }
        return left;
    }

    public IRTemp visit (PlusMinusExpression expr) {
        IRTemp left = expr.left.accept(this);
        if ( expr.right != null ) {
            IRTemp right = expr.right.accept(this);
            String operation = "sub";
            if (expr.isAddition) {
                operation = "add";
            }
            IRTemp ret = this.scope.newTemp(right.type);
            this.lines.add(left.type  + "load " + left.toNString());
            this.lines.add(right.type + "load " + right.toNString());
            this.lines.add(right.type + operation);
            this.lines.add(ret.type + "store " + ret.toNString());
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
        String line = "return";
        if (!ret.isEmpty) {
            IRTemp t = ret.expr.accept(this);
            line += " " + t + ";";
            this.lines.add(line);
            return t;
        }
        this.lines.add(line);
        return new IRTemp();
    }
}