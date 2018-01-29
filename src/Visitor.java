public interface Visitor
{
	void visit (ArrayDereference arrd);
	void visit (AssignmentStatement astmt);
	// void visit (BaseElement base);
	void visit (Block block);
	void visit (BooleanConstant bool);
	void visit (CharConstant character);
	void visit (CompareExpression cmpexpr);
	void visit (CompoundType ctype);
	// void visit (Expression expr);
	void visit (ExpressionList exprlist);
	void visit (FloatConstant cfloat);
	void visit (FormalParameter param);
	void visit (FormalParameterList params);
	void visit (Function func);
	void visit (FunctionBody body);
	void visit (FunctionCall funccall);
	void visit (FunctionDeclaration funcdecl);
	void visit (Identifier id);
	void visit (IfElseStatement ifelsestmt);
	void visit (IntegerConstant cint);
	void visit (LessThanExpression ltexpr);
	void visit (MultiplicationExpression mexpr);
	void visit (PlusMinusExpression pmexpr);
	void visit (PrintlnStatement println);
	void visit (PrintStatement print);
	void visit (Program prog);
	void visit (ReturnStatement retstmt);
	void visit (SimpleStatement stmt);

	void visit (StringConstant cstring);
	void visit (Type type);
	void visit (VariableDeclaration vardecl);
	void visit (VariableDereference varderef);
	void visit (WhileStatement whilestmt);
}