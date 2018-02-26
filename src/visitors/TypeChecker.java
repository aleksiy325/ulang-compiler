import java.util.ArrayList;
import java.lang.Character;
import java.io.*;

public class TypeChecker implements TypeVisitor {
    Scope scope;
    ArrayList<String> lines;
    Identifier main = new Identifier("main");

    public TypeChecker(InputStreamReader input) throws IOException {
        lines = new ArrayList<String>();
        BufferedReader br = new BufferedReader(input);
        String line = null;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        scope = new Scope();
    }

    public void printError(int line, int charPos, String message) {
        System.out.println(line + ":" + charPos + " " + message);
        String strline = this.lines.get(line - 1);
        System.out.println("    " + strline);
        String pad = "    ";
        for (int i = 0; i < charPos; i++) {
            pad += ' ';
        }
        pad += "^";
        System.out.println(pad);
    }

    public void exprError(int line, int charPos, Type ltype, Type rtype, String operator) {
        printError(line, charPos, "Incompatible types " + "\"" + ltype  + "\"" + " and " + "\"" + rtype + "\"" + " for operator " + operator);
    }

    public Type visit (Program prog) {
        this.scope.enterLocalScope();
        for (int i = 0; i < prog.size(); i++) {
            prog.get(i).accept(this);
        }
        this.scope.exitLocalScope();
        FunctionSignature fs = new FunctionSignature(TypeDefs.voidType);

        if (!this.scope.functionExists(this.main)) {
            System.out.println("No main function found.");
            return TypeDefs.voidType;
        }

        FunctionSignature candidate = this.scope.getFunctionSignature(this.main);

        if (!fs.type.equals(candidate.type)) {
            printError(candidate.line, candidate.charPos, "Main function has return type of " + "\"" + candidate.type + "\"" + " expected " + "\"" + fs.type + "\"");
            return TypeDefs.voidType;
        }

        if (fs.paramsSize() != candidate.paramsSize()) {
            printError(candidate.line, candidate.charPos, "Main function has " + candidate.paramsSize() + " parameters expected " + fs.paramsSize());
            return TypeDefs.voidType;
        }

        return TypeDefs.voidType;
    }


    public Type visit (Function func) {
        this.scope.enterLocalScope();
        func.decl.accept(this);
        func.body.accept(this);
        this.scope.exitLocalScope();
        return TypeDefs.voidType;
    }


    public Type visit (FunctionDeclaration fdecl) {
        if (this.scope.functionExists(fdecl.id)) {
            printError(fdecl.line, fdecl.charPos, "Function " + "\"" + fdecl.id  + "\"" + " redefines function in same scope");
        }
        ArrayList<Type> types = fdecl.params.accept(this);
        FunctionSignature fs = new FunctionSignature(fdecl.type, types, fdecl.id);
        this.scope.putGlobalFunction(fdecl.id, fs);
        return fdecl.type.accept(this);
    }


    public ArrayList<Type> visit (FormalParameterList params) {
        ArrayList<Type> types = new ArrayList<Type>();
        for (int i = 0; i < params.size(); i++) {
            types.add(params.get(i).accept(this));
        }
        return types;
    }

    public Type visit (FormalParameter param) {
        if (scope.variableExists(param.id)) {
            printError(param.id.line, param.id.charPos, "Function parameter " + "\"" + param.id + "\"" + " redefines variable in same scope");
            return param.type;
        }
        if (param.type.equals(TypeDefs.voidType)) {
            printError(param.line, param.charPos, "Function parameter cannot be declared as type " + "\"" + TypeDefs.voidType + "\"" );
            return param.type;
        }
        scope.putLocalVariable(param.id, param.type);
        return param.type;
    }


    public Type visit (FunctionBody body) {
        for (int i = 0; i < body.varDeclSize(); i++) {
            body.getVarDecl(i).accept(this);
        }
        for (int i = 0; i < body.statementSize(); i++) {
            body.getStatementList(i).accept(this);
        }
        return TypeDefs.voidType;
    }


    public Type visit (Block block) {
        this.scope.enterLocalScope();
        for (int i = 0; i < block.size(); i++) {
            block.get(i).accept(this);
        }
        this.scope.exitLocalScope();
        return TypeDefs.voidType;
    }


    public Type visit (VariableDeclaration vardecl) {
        if (this.scope.variableExists(vardecl.id)) {
            printError(vardecl.line, vardecl.charPos, "Local variable declaration " + "\"" + vardecl.id + "\"" + " redefines variable in same scope");
            return vardecl.type;
        }
        if (vardecl.type.equals(TypeDefs.voidType)) {
            printError(vardecl.line, vardecl.charPos, "Local variable cannot be declared as type " + "\"" + TypeDefs.voidType + "\"" );
            return vardecl.type;
        }

        this.scope.putLocalVariable(vardecl.id, vardecl.type);
        return vardecl.type;
    }


    public Type visit (AssignmentStatement astmt) {
        Type exprType = astmt.expr.accept(this);
        if (!this.scope.variableExists(astmt.id)) {
            printError(astmt.line, astmt.charPos, "Assignment to undeclared variable " + "\"" + astmt.id + "\"");
            return exprType;
        }
        Type astmtType;
        if (astmt.isArray) {
            astmtType = astmt.deref.accept(this);
        } else {
            astmtType = this.scope.getVariableType(astmt.id);
        }

        if (!TypeTables.AssignmentTable.isCompatible(astmtType, exprType)) {
            exprError(astmt.line, astmt.charPos, astmtType, exprType, "=");
            return exprType;
        }

        return exprType;
    }


    public Type visit (IntegerConstant cint) {
        return cint.type;
    }


    public Type visit (FloatConstant cfloat) {
        return cfloat.type;
    }


    public Type visit (BooleanConstant bool) {
        return bool.type;
    }


    public Type visit (CharConstant character) {
        return character.type;
    }

    public Type visit (StringConstant cstring) {
        return cstring.type;
    }

    public Type visit (Type type) {
        return type;
    }

    public Type visit (ArrayDereference arrd) {
        if (!this.scope.variableExists(arrd.id)) {
            printError(arrd.line, arrd.charPos, "Attempted to dereference array " + "\"" + arrd.id + "\"" + " but it does exist in scope");
            return TypeDefs.voidType;
        }

        //todo add global
        Type etype = arrd.expr.accept(this);
        if (TypeDefs.integerType != etype) {
            printError(arrd.line, arrd.charPos, "Attempted to dereference array " + "\"" + arrd.id + "\"" + " incompatible expression type "  + "\"" + etype + "\"");
            return TypeDefs.voidType;
        }

        Type arrayType = this.scope.getVariableType(arrd.id);
        Type primType = new Type(arrayType.primType);
        return primType;
    }

    public Type visit (CompareExpression expr) {
        Type ltype = expr.left.accept(this);
        if ( expr.right != null ) {
            Type rtype = expr.right.accept(this);

            if (!TypeTables.EqualityTable.isCompatible(ltype, rtype)) {
                exprError(expr.line, expr.charPos, ltype, rtype, "==");
                return ltype;
            }
            return TypeTables.EqualityTable.get(ltype, rtype);
        }
        return ltype;
    }

    public ArrayList<Type> visit (ExpressionList exprlist) {
        ArrayList<Type> types = new ArrayList<Type>();
        for (int i = 0; i < exprlist.size(); i++) {
            types.add(exprlist.get(i).accept(this));
        }
        return types;
    }

    public Type visit (IfElseStatement ifelsestmt) {
        Type wexpr = ifelsestmt.expr.accept(this);
        if (!wexpr.equals(TypeDefs.booleanType)) {
            printError(ifelsestmt.line, ifelsestmt.charPos, "If statement expression is of type \"" + wexpr + "\" expected \"" + TypeDefs.booleanType + "\"");
        }
        ifelsestmt.ifblock.accept(this);
        if (ifelsestmt.hasElse) {
            ifelsestmt.elseblock.accept(this);
        }
        return TypeDefs.voidType;
    }

    public Type visit (LessThanExpression expr) {
        Type ltype = expr.left.accept(this);
        if ( expr.right != null ) {
            Type rtype = expr.right.accept(this);
            if (!TypeTables.LessThanTable.isCompatible(ltype, rtype)) {
                exprError(expr.line, expr.charPos, ltype, rtype, "<");
                return ltype;
            }
            return TypeTables.LessThanTable.get(ltype, rtype);
        }
        return ltype;
    }
    public Type visit (MultiplicationExpression expr) {
        Type ltype = expr.left.accept(this);
        if ( expr.right != null ) {
            Type rtype = expr.right.accept(this);
            if (!TypeTables.MultiplicationTable.isCompatible(ltype, rtype)) {
                exprError(expr.line, expr.charPos, ltype, rtype, "*");
                return ltype;
            }
            return TypeTables.MultiplicationTable.get(ltype, rtype);
        }
        return ltype;
    }
    public Type visit (PlusMinusExpression expr) {
        Type ltype = expr.left.accept(this);
        if ( expr.right != null ) {
            if ( expr.isAddition ) {
                Type rtype = expr.right.accept(this);
                if (!TypeTables.AdditionTable.isCompatible(ltype, rtype)) {
                    exprError(expr.line, expr.charPos, ltype, rtype, "+");
                    return ltype;
                }
                return TypeTables.AdditionTable.get(ltype, rtype);
            } else {
                Type rtype = expr.right.accept(this);
                if (!TypeTables.SubtractionTable.isCompatible(ltype, rtype)) {
                    exprError(expr.line, expr.charPos, ltype, rtype, "-");
                    return ltype;
                }
                return TypeTables.SubtractionTable.get(ltype, rtype);
            }
        }
        return ltype;
    }

    public Type visit (PrintlnStatement println) {
        Type type = println.expr.accept(this);
        if (!StatementDefs.printable.contains(type)) {
            printError(println.line, println.charPos, "Attempting to print unprintable type " + "\"" + type + "\"");

        }
        return TypeDefs.voidType;
    }

    public Type visit (PrintStatement print) {
        Type type = print.expr.accept(this);
        if (!StatementDefs.printable.contains(type)) {
            printError(print.line, print.charPos, "Attempting to print unprintable type " + "\"" + type + "\"");

        }
        return TypeDefs.voidType;
    }

    public Type visit (ReturnStatement retstmt) {
        return TypeDefs.voidType;
    }

    public Type visit (SimpleStatement stmt) {
        if (!stmt.isEmpty) {
            return stmt.expr.accept(this);
        }
        return TypeDefs.voidType;
    }

    public Type visit (PrimitiveType type) {
        return new Type(type);
    }

    public Type visit (VariableDereference varderef) {
        if (!this.scope.variableExists(varderef.id)) {
            printError(varderef.line, varderef.charPos, "Attempted to dereference variable " + "\"" + varderef.id + "\"" + " but it does exist in scope");
            return TypeDefs.voidType;
        }
        return this.scope.getVariableType(varderef.id);
    }
    public Type visit (WhileStatement whilestmt) {
        Type wexpr = whilestmt.expr.accept(this);
        if (!wexpr.equals(TypeDefs.booleanType)) {
            printError(whilestmt.line, whilestmt.charPos, "While statement expression is of type \"" + wexpr + "\" expected \"" + TypeDefs.booleanType + "\"");
        }
        whilestmt.block.accept(this);
        return TypeDefs.voidType;
    }

    public Type visit (FunctionCall funccall) {
        if (!this.scope.functionExists(funccall.id)) {
            printError(funccall.line, funccall.charPos, "Function call " + "\"" + funccall.id + "\"" + " is not in scope");
            return TypeDefs.voidType;
        }
        FunctionSignature fs = this.scope.getFunctionSignature(funccall.id);
        ArrayList<Type> types = funccall.exprs.accept(this);
        if (fs.paramsSize() != types.size()) {
            printError(funccall.line, funccall.charPos, "Function call " + "\"" + funccall.id + "\"" + " has " + types.size() + " parameters expected " + fs.paramsSize());
            return fs.type;
        }
        for (int i = 0; i < fs.paramsSize(); i++ ) {
            Type ptype = fs.getParamType(i);
            Type ctype = types.get(i);
            if (! ptype.equals(ctype)) {
                printError(funccall.line, funccall.charPos, "Function call " + "\"" + funccall.id + "\"" + " parameter " + i + " incompatible type got " + "\"" + ctype + "\" expected " + "\"" + ptype + "\"");
            }
        }
        return fs.type;
    }

    public Type visit (Identifier id) {
        return TypeDefs.voidType;
    }

}