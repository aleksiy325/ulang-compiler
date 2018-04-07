public class IRLabel {
    int id;

    public IRLabel(int id) {
        this.id = id;
    }

    public String toString() {
        return "L" + String.valueOf(this.id);
    }

    public String toJVMString() {
    	return "L_" + String.valueOf(this.id);
    }
}