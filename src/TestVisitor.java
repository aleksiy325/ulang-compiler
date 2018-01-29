public class TestVisitor implements Visitor {

	public void visit (ArrayDerefrence arrd) {
		System.out.println("ArrayDerefrence");
	}

	public void visit (Atom atom) {
		System.out.println("Atom");
	}

	public void visit (BaseElement base) {
		System.out.println("BaseElement");
	}

	public void visit (Block block) {
		System.out.println("Block");
	}

	public void visit (BooleanConstant bool) {
		System.out.println("BooleanConstant");
	}

	public void visit (CharConstant character) {
		System.out.println("CharConstant");
	}

	public void visit (CompareExpression cmpexpr) {
		System.out.println("CompareExpression");
	}

	public void visit (CompoundType ctype) {
		System.out.println("CompoundType");
		visit(ctype.type);
		if ( ctype.isArray ){
			visit(ctype.size);
		}
	}

	public void visit (Constant constant) {
		System.out.println("Constant");
	}

	public void visit (Expression expr) {
		System.out.println("Expression");
	}

	public void visit (ExpressionList exprlist) {
		System.out.println("ExpressionList");
	}

	public void visit (FloatConstant cfloat) {
		System.out.println("FloatConstant");
	}

	public void visit (FormalParameter param) {
		System.out.println("FormalParameter");
		visit(param.type);
		visit(param.id);
	}

	public void visit (FormalParameterList params) {
		System.out.println("FormalParameterList");
		for(int i = 0; i < params.size(); i++){
			visit(params.get(i));
		}
	}

	public void visit (Function func) {
		System.out.println("Function");
		visit(func.decl);
		visit(func.body);
	}

	public void visit (FunctionBody body) {
		System.out.println("FunctionBody");
		for(int i = 0; i < body.varDeclSize(); i++){
			visit(body.getVarDecl(i));
		}
		for(int i = 0; i < body.statementSize(); i++){
			visit(body.getStatementList(i));
		}
	}

	public void visit (FunctionCall funccall) {
		System.out.println("FunctionCall");
	}
	
	public void visit (FunctionDeclaration fdecl) {
		System.out.println("FunctionDeclaration");
		visit(fdecl.type);
		visit(fdecl.id);
		visit(fdecl.params);
	}


	public void visit (Identifier id) {
		System.out.println("Identifier");
		System.out.println(id.val);
	}

	public void visit (IfElseStatement ifelsestmt) {
		System.out.println("IfElseStatement");
	}

	public void visit (IntegerConstant cint){
		System.out.println("IntegerConstant");
		System.out.println(cint.val);
	}

	public void visit (LessThanExpression ltexpr) {
		System.out.println("LessThanExpression");
	}

	public void visit (MultiplicationExpression mexpr){ 
		System.out.println("MultiplicationExpression");
	}

	public void visit (PlusMinusExpression pmexpr) {
		System.out.println("PlusMinusExpression");
	}

	public void visit (PrintlnStatement println) {
		System.out.println("PrintlnStatement");
	}
	
	public void visit (PrintStatement print) {
		System.out.println("PrintStatement");
	}

	public void visit (Program prog) {
		System.out.println("Program");
		for(int i = 0; i < prog.size(); i++) {
			visit(prog.get(i));
		}
	}

	public void visit (ReturnStatement retstmt) {
		System.out.println("retstmt");
	}

	public void visit (Statement stmt){
		System.out.println("Statement");
	}

	public void visit (StringConstant cstring) {
		System.out.println("StringConstant");
	}

	public void visit (Type type) {
		System.out.println("Type");
		System.out.println(type.val);
	}

	public void visit (VariableDeclaration vardecl) {
		System.out.println("VariableDeclaration");
		visit(vardecl.type);
		visit(vardecl.id);
	}

	public void visit (VariableDerefrence varderef) {
		System.out.println("VariableDerefrence");
	}

	public void visit (WhileStatement whilestmt) {
		System.out.println("WhileStatement");
	}

}