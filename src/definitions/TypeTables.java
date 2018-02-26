
public final class TypeTables  {
    public static final TypeTable AssignmentTypeTable = new TypeTable() {
        {
            putBoth(TypeDefs.integerType, TypeDefs.integerType, Integer.TYPE);
        }
    };

    public static final TypeTable AdditionTypeTable = new TypeTable() {
        {

        }
    };
}