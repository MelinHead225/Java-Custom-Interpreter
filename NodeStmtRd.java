import java.util.Scanner;

/**
 * node class for rd statements
 */
public class NodeStmtRd extends NodeStatement {

    private Scanner s = new Scanner(System.in);
    private String ret;

    /**
     * contructor
     */
    public NodeStmtRd(String ret) {
        this.ret = ret;
    }

    /**
     * evaluation method based on enviorment input
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return env.put(ret, s.nextDouble());
    }

}