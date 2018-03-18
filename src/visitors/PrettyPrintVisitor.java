public class PrettyPrintVisitor implements Visitor {
    private static final String SPACE = " ";
    private static final String LBRACK = "(";
    private static final String RBRACK = ")";
    private static final String LBRACE = "{";
    private static final String RBRACE = "}";
    private static final String LSQR = "[";
    private static final String RSQR = "]";
    private static final String COMMA = ",";
    private static final String INDENT = SPACE + SPACE + SPACE + SPACE;
    private static final String NL = "\n";
    private static final String SEMICOLON = ";";
    private static final String IF = "if";
    private static final String ELSE = "else";
    private static final String CMP = "==";
    private static final String LT = "<";
    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MULTI = "*";
    private static final String WHILE = "while";
    private static final String PRINT = "print";
    private static final String PRINTLN = "println";
    private static final String RETURN = "return";
    private static final String EQUALS = "=";
    private static final String SINGQUOTE = "'";

    int indent;

    public PrettyPrintVisitor() {
        indent = 0;

    }

    private void printIndent() {
        for (int i = 0; i < indent; i++) {
            System.out.print(INDENT);
        }
    }

    private void indent() {
        this.indent++;
    }

    private void unindent() {
        this.indent--;
    }

    public void visit (ArrayDereference arrd) {
        arrd.id.accept(this);
        System.out.print(LSQR);
        arrd.expr.accept(this);
        System.out.print(RSQR);
    }

    public void visit (AssignmentStatement astmt) {
        astmt.id.accept(this);
        System.out.print(EQUALS);
        if ( astmt.isArray ) {
            astmt.deref.accept(this);
        }
        astmt.expr.accept(this);
        System.out.print(SEMICOLON);
    }

    public void visit (Block block) {
        this.printIndent();
        System.out.print(LBRACE + NL);
        this.indent();
        for (int i = 0; i < block.size(); i++) {
            this.printIndent();
            block.get(i).accept(this);
            System.out.print(NL);
        }
        this.unindent();
        this.printIndent();
        System.out.print(RBRACE);
    }

    public void visit (BooleanConstant bool) {
        System.out.print(bool.val);
    }

    public void visit (CharConstant character) {
        System.out.print(SINGQUOTE);
        System.out.print(character.val);
        System.out.print(SINGQUOTE);

    }

    public void visit (CompareExpression expr) {
        expr.left.accept(this);
        if ( expr.right != null ) {
            System.out.print(CMP);
            expr.right.accept(this);
        }
    }

    public void visit (Type ctype) {
        ctype.primType.accept(this);
        if ( ctype.isArray ) {
            System.out.print(LSQR);
            ctype.size.accept(this);
            System.out.print(RSQR);
        }
    }


    public void visit (ExpressionList exprlist) {
        System.out.println("ExpressionList");
    }

    public void visit (FloatConstant cfloat) {
        System.out.print(cfloat.val);
    }

    public void visit (FormalParameter param) {
        param.type.accept(this);
        System.out.print(SPACE);
        param.id.accept(this);
    }

    public void visit (FormalParameterList params) {
        System.out.print(SPACE + LBRACK);
        for (int i = 0; i < params.size(); i++) {
            params.get(i).accept(this);
            if (i < params.size() - 1) {
                System.out.print(COMMA + SPACE);
            }
        }
        System.out.print(RBRACK + SPACE);
    }

    public void visit (Function func) {
        func.decl.accept(this);
        func.body.accept(this);
    }

    public void visit (FunctionBody body) {
        System.out.print(NL + LBRACE + NL);
        this.indent();
        for (int i = 0; i < body.varDeclSize(); i++) {
            this.printIndent();
            body.getVarDecl(i).accept(this);
            System.out.print(NL);
        }
        for (int i = 0; i < body.statementSize(); i++) {
            this.printIndent();
            body.getStatementList(i).accept(this);
            System.out.print(NL);
        }
        this.unindent();
        System.out.print(RBRACE);
    }

    public void visit (FunctionCall funccall) {
        funccall.id.accept(this);
        System.out.print(LBRACK);
        for (int i = 0; i < funccall.exprs.size(); i++) {
            funccall.exprs.get(i).accept(this);
            if (i < funccall.exprs.size() - 1) {
                System.out.print(COMMA);
            }
        }
        System.out.print(RBRACK);
    }

    public void visit (FunctionDeclaration fdecl) {
        fdecl.type.accept(this);
        System.out.print(SPACE);
        fdecl.id.accept(this);
        fdecl.params.accept(this);
    }


    public void visit (Identifier id) {
        System.out.print(id.val);
    }

    public void visit (IfElseStatement ifelsestmt) {
        System.out.print(IF + SPACE + LBRACK);
        ifelsestmt.expr.accept(this);
        System.out.print(RBRACK + NL);
        ifelsestmt.ifblock.accept(this);
        if ( ifelsestmt.hasElse ) {
            System.out.print(NL);
            this.printIndent();
            System.out.print(ELSE + NL);
            ifelsestmt.elseblock.accept(this);
        }
    }

    public void visit (IntegerConstant cint) {
        System.out.print(cint.val);
    }

    public void visit (LessThanExpression expr) {
        expr.left.accept(this);
        if ( expr.right != null ) {
            System.out.print(LT);
            expr.right.accept(this);
        }
    }

    public void visit (MultiplicationExpression expr) {
        expr.left.accept(this);
        if ( expr.right != null ) {
            System.out.print(MULTI);
            expr.right.accept(this);
        }
    }

    public void visit (PlusMinusExpression expr) {
        expr.left.accept(this);
        if ( expr.right != null ) {
            if ( expr.isAddition ) {
                System.out.print(ADD);
            } else {
                System.out.print(SUB);
            }
            expr.right.accept(this);
        }
    }

    public void visit (PrintlnStatement println) {
        System.out.print(PRINTLN + SPACE);
        println.expr.accept(this);
        System.out.print(SEMICOLON);
    }

    public void visit (PrintStatement print) {
        System.out.print(PRINT + SPACE);
        print.expr.accept(this);
        System.out.print(SEMICOLON);
    }

    public void visit (Program prog) {
        for (int i = 0; i < prog.size(); i++) {
            prog.get(i).accept(this);
            if ( i < prog.size() - 1 ) {
                System.out.print(NL + NL);
            }
        }
    }

    public void visit (ReturnStatement retstmt) {
        System.out.print(RETURN + SPACE);
        if ( !retstmt.isEmpty ) {
            retstmt.expr.accept(this);
        }
        System.out.print(SEMICOLON);

    }

    public void visit (SimpleStatement stmt) {
        if (!stmt.isEmpty) {
            stmt.expr.accept(this);
        }
        System.out.print(SEMICOLON);
    }

    public void visit (StringConstant cstring) {
        System.out.print(cstring.val);
    }

    public void visit (PrimitiveType type) {
        System.out.print(type.id);
    }

    public void visit (VariableDeclaration vardecl) {
        vardecl.type.accept(this);
        System.out.print(SPACE);
        vardecl.id.accept(this);
        System.out.print(SEMICOLON);
    }

    public void visit (VariableDereference varderef) {
        varderef.id.accept(this);
    }

    public void visit (WhileStatement whilestmt) {
        System.out.print(WHILE + SPACE + LBRACK);
        whilestmt.expr.accept(this);
        System.out.print(RBRACK + NL);
        whilestmt.block.accept(this);
    }
}