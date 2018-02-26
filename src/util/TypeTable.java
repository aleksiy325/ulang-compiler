import java.util.HashMap;

public class TypeTable {
    private HashMap<PrimitiveType, HashMap<PrimitiveType, PrimitiveType>> table;

    public TypeTable() {
        this.table = new HashMap<PrimitiveType, HashMap<PrimitiveType, PrimitiveType>>();
    }

    public void put(PrimitiveType ltype, PrimitiveType rtype, PrimitiveType typeClass) {
        if (!this.table.containsKey(ltype)) {
            this.table.put(ltype, new HashMap < PrimitiveType, PrimitiveType>());
        }
        this.table.get(ltype).put(rtype, typeClass);
    }

    public void putBoth(PrimitiveType ltype, PrimitiveType rtype, PrimitiveType type) {
        this.put(ltype, rtype, type);
        this.put(rtype, ltype, type);
    }

    public boolean isCompatible(PrimitiveType ltype, PrimitiveType rtype) {
        if (!this.table.containsKey(ltype)) {
            return false;
        }
        return this.table.get(ltype).containsKey(rtype);
    }

    public PrimitiveType get(PrimitiveType ltype, PrimitiveType rtype) {
        return this.table.get(ltype).get(rtype);
    }

    public void put(Type ltype, Type rtype, Type type) {
        this.put(ltype.primType, rtype.primType, type.primType);
    }

    public void putBoth(Type ltype, Type rtype, Type type) {
        this.put(ltype, rtype, type);
        this.put(rtype, ltype, type);
    }

    public boolean isCompatible(Type ltype, Type rtype) {
        if (ltype.isArray != rtype.isArray) {
            return false;
        }
        return this.isCompatible(ltype.primType, rtype.primType);
    }

    public Type get(Type ltype, Type rtype) {
        Type ntype = new Type(this.get(ltype.primType, rtype.primType));
        if (ltype.isArray) {
            ntype.makeArray(ltype.size);
        }
        return ntype;
    }
}