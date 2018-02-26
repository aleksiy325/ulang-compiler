import java.util.HashMap;
import java.lang.reflect.Type;

public class TypeTable {
    private HashMap<PrimitiveType, HashMap<PrimitiveType, Class>> table;

    public TypeTable() {
        this.table = new HashMap<PrimitiveType, HashMap<PrimitiveType, Class>>();
    }

    public void put(PrimitiveType ltype, PrimitiveType rtype, Class typeClass) {
        if (!this.table.containsKey(ltype)) {
            this.table.put(ltype, new HashMap < PrimitiveType, Class>());
        }
        this.table.get(ltype).put(rtype, typeClass);
    }

    public void putBoth(PrimitiveType ltype, PrimitiveType rtype, Class typeClass) {
        this.put(ltype, rtype, typeClass);
        this.put(rtype, ltype, typeClass);
    }

    public boolean compatibleTypes(PrimitiveType ltype, PrimitiveType rtype) {
        if (!this.table.containsKey(ltype)) {
            return false;
        }
        return this.table.get(ltype).containsKey(rtype);
    }

    public Class get(PrimitiveType ltype, PrimitiveType rtype) {
        this.table.get(ltype).get(rtype);
    }

    public void put(Type ltype, Type rtype, Class typeClass) {
        this.put(ltype.primType, rtype.primType, typeClass);
    }

    public void putBoth(Type ltype, Type rtype, Class typeClass) {
        this.put(ltype, rtype, typeClass);
        this.put(rtype, ltype, typeClass);
    }

    public boolean compatibleTypes(Type ltype, Type rtype) {
        this.compatibleTypes(ltype.primType, rtype.primType);
    }

    public Class get(Type ltype, Type rtype) {
        return this.get(ltype.primType, rtype.primType);
    }
}