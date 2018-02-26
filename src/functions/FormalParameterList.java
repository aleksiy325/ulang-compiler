import java.util.ArrayList;

public class FormalParameterList extends BaseElement {
    ArrayList<FormalParameter> parameterList;

    public FormalParameterList () {
        parameterList = new ArrayList<FormalParameter>();
    }

    public void add (FormalParameter param) {
        parameterList.add(param);
    }

    public FormalParameter get (int index) {
        return parameterList.get(index);
    }

    public int size () {
        return parameterList.size();
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public CompoundType accept (TypeVisitor v) {
        return v.visit(this);
    }
}