public class TermNode {
    int coefficient, x_val, y_val, z_val;
    int sign;

    TermNode prev, next;

    public TermNode(){
        coefficient = x_val = y_val = z_val = 0;
        sign = 1;
        next = prev = null;
    }

    public TermNode(int coefficient, int x_val, int y_val, int z_val,
                    int sign){
        this.coefficient = coefficient;
        this.x_val = x_val;
        this.y_val = y_val;
        this.z_val = z_val;
        this.sign = sign;
    }

    public int getNodeWeight(){
        return x_val + y_val + z_val;
    }
}
