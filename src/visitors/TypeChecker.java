public class TypeChecker implements TypeVisitor {
    public TypeChecker() {

    }

    public Type visit (ArrayDereference arrd) {
        arrd.id.accept(this);
        arrd.expr.accept(this);
        return new Type("null");
    }

    public Type visit (AssignmentStatement astmt) {
        astmt.id.accept(this);
        if ( astmt.isArray ) {
            astmt.size.accept(this);
        }
        astmt.expr.accept(this);
        return new Type("null");
    }

    public Type visit (Block block) {
        for (int i = 0; i < block.size(); i++) {
            block.get(i).accept(this);
        }
        return new Type("null");
    }

    public Type visit (BooleanConstant bool) {
        return bool.type;
    }

    public Type visit (CharConstant character) {
        return character.type;
    }

    public Type visit (CompareExpression expr) {
        expr.left.accept(this);
        if ( expr.right != null ) {
            expr.right.accept(this);
        }
        return new Type("null");
    }

    public Type visit (CompoundType ctype) {
        ctype.type.accept(this);
        if ( ctype.isArray ) {
            ctype.size.accept(this);
        }
        return new Type("null");
    }


    public Type visit (ExpressionList exprlist) {
        return new Type("null");
    }

    public Type visit (FloatConstant cfloat) {
        return cfloat.type;
    }

    public Type visit (FormalParameter param) {
        param.type.accept(this);
        param.id.accept(this);
        return new Type("null");
    }

    public Type visit (FormalParameterList params) {
        for (int i = 0; i < params.size(); i++) {
            params.get(i).accept(this);
        }
        return new Type("null");
    }

    public Type visit (Function func) {
        func.decl.accept(this);
        func.body.accept(this);
        return new Type("null");
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

    public Type visit (FunctionCall funccall) {
        funccall.id.accept(this);
        for (int i = 0; i < funccall.exprs.size(); i++) {
            funccall.exprs.get(i).accept(this);
        }
        return new Type("null");
    }

    public Type visit (FunctionDeclaration fdecl) {
        fdecl.type.accept(this);
        fdecl.id.accept(this);
        fdecl.params.accept(this);
        return new Type("null");
    }


    public Type visit (Identifier id) {
        return new Type("null");
    }

    public Type visit (IfElseStatement ifelsestmt) {
        ifelsestmt.expr.accept(this);
        ifelsestmt.ifblock.accept(this);
        if ( ifelsestmt.hasElse ) {
            ifelsestmt.elseblock.accept(this);
        }
        return new Type("null");
    }

    public Type visit (IntegerConstant cint) {
        return cint.type;
    }

    public Type visit (LessThanExpression expr) {
        expr.left.accept(this);
        if ( expr.right != null ) {
            expr.right.accept(this);
        }
        return new Type("null");
    }

    public Type visit (MultiplicationExpression expr) {
        expr.left.accept(this);
        if ( expr.right != null ) {
            expr.right.accept(this);
        }
        return new Type("null");
    }

    public Type visit (PlusMinusExpression expr) {
        expr.left.accept(this);
        if ( expr.right != null ) {
            expr.right.accept(this);
        }
        return new Type("null");
    }

    public Type visit (PrintlnStatement println) {
        println.expr.accept(this);
        return new Type("null");
    }

    public Type visit (PrintStatement print) {
        print.expr.accept(this);
        return new Type("null");
    }

    public Type visit (Program prog) {
        for (int i = 0; i < prog.size(); i++) {

            prog.get(i).accept(this);
        }
        return new Type("null");
    }

    public Type visit (ReturnStatement retstmt) {
        if ( !retstmt.isEmpty ) {
            retstmt.expr.accept(this);
        }
        return new Type("null");
    }

    public Type visit (SimpleStatement stmt) {
        return new Type("null");
    }

    public Type visit (StringConstant cstring) {
        return cstring.type;
    }

    public Type visit (Type type) {
        return new Type("null");
    }

    public Type visit (VariableDeclaration vardecl) {
        vardecl.type.accept(this);
        vardecl.id.accept(this);
        return new Type("null");
    }

    public Type visit (VariableDereference varderef) {
        varderef.id.accept(this);
        return new Type("null");
    }

    public Type visit (WhileStatement whilestmt) {
        whilestmt.expr.accept(this);
        whilestmt.block.accept(this);
        return new Type("null");
    }

}