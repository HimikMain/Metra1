package sample;

public class Operands {
    private int i;
    private String operand;
    private int f2i;

    public Operands(int i, String operand, int f2i) {
        this.i = i;
        this.operand = operand;
        this.f2i = f2i;
    }

    public int getI() { return i; }
    public String getOperand() { return operand; }
    public int getF2i() { return f2i; }

    public void setI(int j) { this.i = i; }
    public void setOperand(String operator) { this.operand = operand; }
    public void setF2i(int f1j) { this.f2i = f2i; }
}
