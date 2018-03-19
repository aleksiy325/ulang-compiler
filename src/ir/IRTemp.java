public class IRTemp {
    int id;
    String type;

    public IRTemp() {

    }

    public IRTemp(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getArrayType() {
        return String.valueOf(this.type.charAt(this.type.length() - 1));
    }

    public String toString() {
        return "T" + String.valueOf(this.id);
    }

    public void printDecl() {
        System.out.println("TEMP " + id + ":" + type + ";");
    }
}