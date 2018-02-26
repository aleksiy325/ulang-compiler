import java.util.ArrayList;

public class FunctionSignature  {
    Type type;
    ArrayList<Type> paramTypes;

    FunctionSignature() {
        paramTypes = new ArrayList<Type>();
    }

    FunctionSignature(Type type, ArrayList<Type> paramTypes) {
        this.type = type;
        this.paramTypes = paramTypes;
    }

    public int paramsSize() {
        return paramTypes.size();
    }

    public Type getParamType(int i) {
        return paramTypes.get(i);
    }

}