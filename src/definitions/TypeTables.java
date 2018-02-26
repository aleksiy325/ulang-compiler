
public final class TypeTables  {
    public static final TypeTable AssignmentTable = new TypeTable() {
        {
            putBoth(TypeDefs.integerPrimitive, TypeDefs.integerPrimitive, TypeDefs.integerPrimitive);
            putBoth(TypeDefs.floatPrimitive, TypeDefs.floatPrimitive, TypeDefs.floatPrimitive);
            putBoth(TypeDefs.charPrimitive, TypeDefs.charPrimitive, TypeDefs.charPrimitive);
            putBoth(TypeDefs.stringPrimitive, TypeDefs.stringPrimitive, TypeDefs.stringPrimitive);
            putBoth(TypeDefs.booleanPrimitive, TypeDefs.booleanPrimitive, TypeDefs.booleanPrimitive);
        }
    };

    public static final TypeTable AdditionTable = new TypeTable() {
        {
            putBoth(TypeDefs.integerPrimitive, TypeDefs.integerPrimitive, TypeDefs.integerPrimitive);
            putBoth(TypeDefs.floatPrimitive, TypeDefs.floatPrimitive, TypeDefs.floatPrimitive);
            putBoth(TypeDefs.charPrimitive, TypeDefs.charPrimitive, TypeDefs.charPrimitive);
            putBoth(TypeDefs.stringPrimitive, TypeDefs.stringPrimitive, TypeDefs.stringPrimitive);
        }
    };

    public static final TypeTable SubtractionTable = new TypeTable() {
        {
            putBoth(TypeDefs.integerPrimitive, TypeDefs.integerPrimitive, TypeDefs.integerPrimitive);
            putBoth(TypeDefs.floatPrimitive, TypeDefs.floatPrimitive, TypeDefs.floatPrimitive);
            putBoth(TypeDefs.charPrimitive, TypeDefs.charPrimitive, TypeDefs.charPrimitive);
        }
    };

    public static final TypeTable MultiplicationTable = new TypeTable() {
        {
            putBoth(TypeDefs.integerPrimitive, TypeDefs.integerPrimitive, TypeDefs.integerPrimitive);
            putBoth(TypeDefs.floatPrimitive, TypeDefs.floatPrimitive, TypeDefs.floatPrimitive);
        }
    };

    public static final TypeTable LessThanTable = new TypeTable() {
        {
            putBoth(TypeDefs.integerPrimitive, TypeDefs.integerPrimitive, TypeDefs.booleanPrimitive);
            putBoth(TypeDefs.floatPrimitive, TypeDefs.floatPrimitive, TypeDefs.booleanPrimitive);
            putBoth(TypeDefs.charPrimitive, TypeDefs.charPrimitive, TypeDefs.booleanPrimitive);
            putBoth(TypeDefs.stringPrimitive, TypeDefs.stringPrimitive, TypeDefs.booleanPrimitive);
            putBoth(TypeDefs.booleanPrimitive, TypeDefs.booleanPrimitive, TypeDefs.booleanPrimitive);
        }
    };

    public static final TypeTable EqualityTable = new TypeTable() {
        {
            putBoth(TypeDefs.integerPrimitive, TypeDefs.integerPrimitive, TypeDefs.booleanPrimitive);
            putBoth(TypeDefs.floatPrimitive, TypeDefs.floatPrimitive, TypeDefs.booleanPrimitive);
            putBoth(TypeDefs.charPrimitive, TypeDefs.charPrimitive, TypeDefs.booleanPrimitive);
            putBoth(TypeDefs.stringPrimitive, TypeDefs.stringPrimitive, TypeDefs.booleanPrimitive);
            putBoth(TypeDefs.booleanPrimitive, TypeDefs.booleanPrimitive, TypeDefs.booleanPrimitive);
        }
    };
}
