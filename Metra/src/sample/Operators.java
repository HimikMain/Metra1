package sample;

public class Operators {
    private int j;
    private String operator;
    private int f1j;

    public Operators(int j, String operator, int f1j) {
        this.j = j;
        this.operator = operator;
        this.f1j = f1j;
    }

    public int getJ() { return j; }
    public String getOperator() { return operator; }
    public int getF1j() { return f1j; }

    public void setJ(int j) { this.j = j; }
    public void setOperator(String operator) { this.operator = operator; }
    public void setF1j(int f1j) { this.f1j = f1j; }
}
