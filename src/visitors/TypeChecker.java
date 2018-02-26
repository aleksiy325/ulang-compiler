
public class TypeChecker implements TypeVisitor {
    Scope scope;

    public TypeChecker() {
        scope = new Scope();
    }

    public Type visit (Program prog) {
        this.scope.enterLocalScope();
        for (int i = 0; i < prog.size(); i++) {
            prog.get(i).accept(this);
        }
        this.scope.exitLocalScope();
        return new Type("null");
    }


    public Type visit (Function func) {
        this.scope.enterLocalScope();
        func.decl.accept(this);
        func.body.accept(this);
        this.scope.exitLocalScope();
        return new Type("null");
    }


    public Type visit (FunctionDeclaration fdecl) {
        if (this.scope.functionExists(fdecl.id)) {
            System.out.println("Duplicate function");
        }
        this.scope.putLocalFunction(fdecl.id, fdecl.type);

        //fdecl.id.accept(this);
        fdecl.params.accept(this);
        return fdecl.type.accept(this);
    }


    public Type visit (FormalParameterList params) {
        for (int i = 0; i < params.size(); i++) {
            params.get(i).accept(this);
        }
        return TypeDefs.voidType;
    }

    public Type visit (FormalParameter param) {
        if (scope.variableExists(param.id)) {
            System.out.println("Function parameter redefines variable in same scope.");
        }
        scope.putLocalVariable(param.id, param.type);
        // param.type.accept(this);
        // param.id.accept(this);
        return param.type;
    }


    public Type visit (FunctionBody body) {
        for (int i = 0; i < body.varDeclSize(); i++) {
            body.getVarDecl(i).accept(this);
        }
        for (int i = 0; i < body.statementSize(); i++) {
            body.getStatementList(i).accept(this);
        }
        return new Type("null");
    }


    public Type visit (Block block) {
        this.scope.enterLocalScope();
        for (int i = 0; i < block.size(); i++) {
            block.get(i).accept(this);
        }
        this.scope.exitLocalScope();
        return new Type("null");
    }


    public Type visit (VariableDeclaration vardecl) {
        if (this.scope.variableExists(vardecl.id)) {
            System.out.println("Redefinition of variable.");
        }
        // vardecl.type.accept(this);
        // vardecl.id.accept(this);
        return vardecl.type;
    }


    public Type visit (AssignmentStatement astmt) {
        if (!this.scope.variableExists(astmt.id)) {
            System.out.println("Variable does not exist");
        }

        return TypeDefs.voidType;
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
        return TypeDefs.voidType;
    }
    public Type visit (CompareExpression cmpexpr) {
        return TypeDefs.voidType;
    }
    public Type visit (ExpressionList exprlist) {
        return TypeDefs.voidType;
    }
    public Type visit (IfElseStatement ifelsestmt) {
        return TypeDefs.voidType;
    }
    public Type visit (LessThanExpression ltexpr) {
        return TypeDefs.voidType;
    }
    public Type visit (MultiplicationExpression mexpr) {
        return TypeDefs.voidType;
    }
    public Type visit (PlusMinusExpression pmexpr) {
        return TypeDefs.voidType;
    }
    public Type visit (PrintlnStatement println) {
        return TypeDefs.voidType;
    }
    public Type visit (PrintStatement print) {
        return TypeDefs.voidType;
    }
    public Type visit (ReturnStatement retstmt) {
        return TypeDefs.voidType;
    }
    public Type visit (SimpleStatement stmt) {
        return TypeDefs.voidType;
    }
    public Type visit (PrimitiveType type) {
        return TypeDefs.voidType;
    }
    public Type visit (VariableDereference varderef) {
        return TypeDefs.voidType;
    }
    public Type visit (WhileStatement whilestmt) {
        return TypeDefs.voidType;
    }

    public Type visit (FunctionCall funccall) {
        return TypeDefs.voidType;
    }

    public Type visit (Identifier id) {
        return TypeDefs.voidType;
    }

}