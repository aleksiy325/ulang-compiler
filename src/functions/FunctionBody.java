import java.util.ArrayList;

public class FunctionBody extends BaseElement {
    ArrayList<VariableDeclaration> varDeclList;
    ArrayList<Statement> statementList;

    public FunctionBody () {
        varDeclList = new ArrayList<VariableDeclaration>();
        statementList = new ArrayList<Statement>();
    }

    public void addVarDecl(VariableDeclaration element) {
        varDeclList.add(element);
    }

    public void addStatement(Statement element) {
        statementList.add(element);
    }

    public VariableDeclaration getVarDecl(int index) {
        return varDeclList.get(index);
    }

    public Statement getStatementList(int index) {
        return statementList.get(index);
    }

    public int varDeclSize() {
        return varDeclList.size();
    }

    public int statementSize() {
        return statementList.size();
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public Type accept (TypeVisitor v) {
        return v.visit(this);
    }
}
