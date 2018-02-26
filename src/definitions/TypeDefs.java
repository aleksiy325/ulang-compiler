public final class TypeDefs {
    public static final PrimitiveType integerPrimitive = new PrimitiveType("int");
    public static final PrimitiveType floatPrimitive = new PrimitiveType("float");
    public static final PrimitiveType charPrimitive = new PrimitiveType("char");
    public static final PrimitiveType stringPrimitive = new PrimitiveType("string");
    public static final PrimitiveType booleanPrimitive = new PrimitiveType("boolean");
    public static final PrimitiveType voidPrimitive = new PrimitiveType("void");
    public static final Type integerType = new Type(integerPrimitive);
    public static final Type floatType = new Type(floatPrimitive);
    public static final Type charType = new Type(charPrimitive);
    public static final Type stringType = new Type(stringPrimitive);
    public static final Type booleanType = new Type(booleanPrimitive);
    public static final Type voidType = new Type(voidPrimitive);
}