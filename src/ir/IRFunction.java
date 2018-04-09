import java.util.ArrayList;
import java.util.HashMap;

public class IRFunction {
    Identifier id;
    ArrayList<IRTemp> params;
    IRBody body;
    String rtype;
    FunctionSignature signature;
    HashMap<PrimitiveType, String> fTypeMap;

    public IRFunction () {
        params = new ArrayList<IRTemp>();
    }

    public IRFunction (Identifier id, ArrayList<IRTemp> params, String rtype, FunctionSignature signature) {
        this.id = id;
        this.params = params;
        this.rtype = rtype;
        this.signature = signature;
        fTypeMap = new HashMap<PrimitiveType, String>();
        fTypeMap.put(TypeDefs.integerPrimitive, "I");
        fTypeMap.put(TypeDefs.floatPrimitive, "F");
        fTypeMap.put(TypeDefs.charPrimitive, "C");
        fTypeMap.put(TypeDefs.stringPrimitive, "U");
        fTypeMap.put(TypeDefs.booleanPrimitive, "Z");
        fTypeMap.put(TypeDefs.voidPrimitive, "V");
    }

    public void addBody(IRBody body) {
        this.body = body;
    }

    public void print() {
        System.out.println();
        System.out.print("FUNC " + id.val + " (");
        for (IRTemp param : params) {
            System.out.print(param.type);
        }
        System.out.println(")" + rtype);
        System.out.println("{");
        body.print();
        System.out.println("}");
    }

    public void printJVM() {
        System.out.println();
        if(id.val.equals("main")){
            System.out.print(".method public static __" + id.val + "(");
        }else{
            System.out.print(".method public static " + id.val + "(");
        }
        for (Type type : signature.paramTypes){
            System.out.print(this.fTypeMap.get(type.primType));
        }
        System.out.println(")" + this.fTypeMap.get(signature.type.primType));
        body.printJVM(signature.paramTypes.size());
        System.out.println(".end method");
    }
}