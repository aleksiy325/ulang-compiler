
public class TypeChecker implements TypeVisitor {
    Scope scope;

    public TypeChecker() {
        scope = new Scope();
    }

    public CompoundType visit (ArrayDereference arrd) {
        arrd.id.accept(this);
        arrd.expr.accept(this);
        return new CompoundType("null");
    }

    public CompoundType visit (AssignmentStatement astmt) {
        astmt.id.accept(this);
        if ( astmt.isArray ) {
            astmt.size.accept(this);
        }
        astmt.expr.accept(this);
        return new CompoundType("null");
    }

    public CompoundType visit (Block block) {
        this.scope.enterLocalScope();
        for (int i = 0; i < block.size(); i++) {
            block.get(i).accept(this);
        }
        this.scope.exitLocalScope();
        return new CompoundType("null");
    }

    public CompoundType visit (BooleanConstant bool) {
        return bool.type;
    }

    public CompoundType visit (CharConstant character) {
        return character.type;
    }

    public CompoundType visit (CompareExpression expr) {
        expr.left.accept(this);
        if ( expr.right != null ) {
            expr.right.accept(this);
        }
        return new CompoundType("null");
    }

    public CompoundType visit (CompoundType ctype) {
        ctype.type.accept(this);
        if ( ctype.isArray ) {
            ctype.size.accept(this);
        }
        return ctype;
    }


    public CompoundType visit (ExpressionList exprlist) {
        return new CompoundType("null");
    }

    public CompoundType visit (FloatConstant cfloat) {
        return cfloat.type;
    }

    public CompoundType visit (FormalParameter param) {
        if (scope.variableExists(param.id)) {
            System.out.println("Function parameter redefines variable in same scope.");
        }
        scope.putLocalVariable(param.id, param.type);
        // param.type.accept(this);
        // param.id.accept(this);
        return param.type;
    }

    public CompoundType visit (FormalParameterList params) {
        for (int i = 0; i < params.size(); i++) {
            params.get(i).accept(this);
        }
        return new CompoundType("null");
    }

    public CompoundType visit (Function func) {
        this.scope.enterLocalScope();
        func.decl.accept(this);
        func.body.accept(this);
        this.scope.exitLocalScope();
        return new CompoundType("null");
    }

    public CompoundType visit (FunctionBody body) {
        for (int i = 0; i < body.varDeclSize(); i++) {
            body.getVarDecl(i).accept(this);
        }
        for (int i = 0; i < body.statementSize(); i++) {
            body.getStatementList(i).accept(this);
        }
        return new CompoundType("null");
    }

    public CompoundType visit (FunctionCall funccall) {
        funccall.id.accept(this);
        for (int i = 0; i < funccall.exprs.size(); i++) {
            funccall.exprs.get(i).accept(this);
        }
        return new CompoundType("null");
    }

    public CompoundType visit (FunctionDeclaration fdecl) {
        if (this.scope.functionExists(fdecl.id)) {
            System.out.println("Duplicate function");
        }
        this.scope.putLocalFunction(fdecl.id, fdecl.type);

        //fdecl.id.accept(this);
        fdecl.params.accept(this);
        return fdecl.type.accept(this);
    }


    public CompoundType visit (Identifier id) {
        return new CompoundType("null");
    }

    public CompoundType visit (IfElseStatement ifelsestmt) {
        ifelsestmt.expr.accept(this);
        ifelsestmt.ifblock.accept(this);
        if ( ifelsestmt.hasElse ) {
            ifelsestmt.elseblock.accept(this);
        }
        return new CompoundType("null");
    }

    public CompoundType visit (IntegerConstant cint) {
        return cint.type;
    }

    public CompoundType visit (LessThanExpression expr) {
        expr.left.accept(this);
        if ( expr.right != null ) {
            expr.right.accept(this);
        }
        return new CompoundType("null");
    }

    public CompoundType visit (MultiplicationExpression expr) {
        expr.left.accept(this);
        if ( expr.right != null ) {
            expr.right.accept(this);
        }
        return new CompoundType("null");
    }

    public CompoundType visit (PlusMinusExpression expr) {
        expr.left.accept(this);
        if ( expr.right != null ) {
            expr.right.accept(this);
        }
        return new CompoundType("null");
    }

    public CompoundType visit (PrintlnStatement println) {
        println.expr.accept(this);
        return new CompoundType("null");
    }

    public CompoundType visit (PrintStatement print) {
        print.expr.accept(this);
        return new CompoundType("null");
    }

    public CompoundType visit (Program prog) {
        this.scope.enterLocalScope();
        for (int i = 0; i < prog.size(); i++) {
            prog.get(i).accept(this);
        }
        this.scope.exitLocalScope();
        return new CompoundType("null");
    }

    public CompoundType visit (ReturnStatement retstmt) {
        if ( !retstmt.isEmpty ) {
            retstmt.expr.accept(this);
        }
        return new CompoundType("null");
    }

    public CompoundType visit (SimpleStatement stmt) {
        return new CompoundType("null");
    }

    public CompoundType visit (StringConstant cstring) {
        return cstring.type;
    }

    public CompoundType visit (Type type) {
        return new CompoundType(type);
    }

    public CompoundType visit (VariableDeclaration vardecl) {
        if (this.scope.variableExists(vardecl.id)) {
            System.out.println("Redefinition of variable.");
        }
        // vardecl.type.accept(this);
        // vardecl.id.accept(this);
        return vardecl.type;
    }

    public CompoundType visit (VariableDereference varderef) {
        varderef.id.accept(this);
        return new CompoundType("null");
    }

    public CompoundType visit (WhileStatement whilestmt) {
        whilestmt.expr.accept(this);
        whilestmt.block.accept(this);
        return new CompoundType("null");
    }

}