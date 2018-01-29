public class Function extends BaseElement{
	FunctionDeclaration decl;
	FunctionBody body;
		
	public Function (FunctionDeclaration decl, FunctionBody body)
	{
		this.decl = decl;
		this.body = body;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}
}