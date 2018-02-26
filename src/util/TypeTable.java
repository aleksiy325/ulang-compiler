import java.util.HashMap;
import java.lang.reflect.Type;

public class TypeTable {
    private HashMap < CompoundType, HashMap<CompoundType, Class>> table;


    public TypeTable() {
        this.table = HashMap < CompoundType, HashMap<CompoundType, Class>>();
    }

    public void put(CompoundType ltype, CompoundType rtype, Class typeClass) {
        if (!this.table.containsKey(ltype)) {
            this.table.put(ltype, new HashMap < CompoundType, Class>());
        }
        this.table.get(ltype).put(rtype, typeClass);
    }

    public void putBoth(CompoundType ltype, CompoundType rtype, Class typeClass) {
        this.put(ltype, rtype, typeClass);
        this.put(rtype, ltype, typeClass);
    }

    public containsKeys(CompoundType ltype, CompoundType rtype) {
        if (!this.table.containsKey(ltype)) {
            return false;
        }
        return this.table.get(ltype).containsKey(rtype);
    }

    public Class get(CompoundType ltype, CompoundType rtype) {
        return this.table.get(ltype).get(rtype);
    }
}