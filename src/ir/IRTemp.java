public class IRTemp {
    int id;
    String type;

    public IRTemp() {

    }

    public IRTemp(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public void printDecl() {
        System.out.println("TEMP " + id + ":" + type + ";");
    }
}