import java.util.Set;
import java.util.HashSet;


public final class StatementDefs {
    public static final Set<Type> printable = new HashSet<Type>() {
        {
            add(TypeDefs.integerType);
            add(TypeDefs.floatType);
            add(TypeDefs.charType);
            add(TypeDefs.stringType);
            add(TypeDefs.booleanType);
        }
    };

}