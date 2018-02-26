import java.util.ArrayList;

public class FunctionSignature  {
    Type type;
    ArrayList<Type> paramTypes;
    int line;
    int charPos;

    FunctionSignature(Type type) {
        this.type = type;
        paramTypes = new ArrayList<Type>();
    }

    FunctionSignature(Type type, ArrayList<Type> paramTypes) {
        this.type = type;
        this.paramTypes = paramTypes;
    }

    FunctionSignature(Type type, ArrayList<Type> paramTypes, Identifier id) {
        this.type = type;
        this.paramTypes = paramTypes;
        this.line = id.line;
        this.charPos = id.charPos;
    }
    public int paramsSize() {
        return paramTypes.size();
    }

    public Type getParamType(int i) {
        return paramTypes.get(i);
    }

}