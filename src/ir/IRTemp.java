public class IRTemp {
    int id;
    String type;

    public IRTemp() {

    }

    public IRTemp(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public boolean isArray() {
        return this.type.length() > 1;
    }

    public String getArrayType() {
        return String.valueOf(this.type.charAt(this.type.length() - 1));
    }

    public String toString() {
        return "T" + String.valueOf(this.id);
    }

    public String toNString() {
        return String.valueOf(this.id);
    }

    public void printDecl() {
        System.out.println("TEMP " + id + ":" + type + ";");
    }

    public void printJVMDecl() {
        if (this.isArray()){
            System.out.println("aconst_null");
            System.out.println("astore " + this.toNString());
        }else{
            System.out.println("ldc 0");
            System.out.println(type + "store " + this.toNString());
        }
    }
}