/**
 * node statement class for begin statements
 */
public class NodeStmtBegin extends NodeStatement {
    
    private NodeBlock b;

    /**
     * constructor
     * @param b
     */
    public NodeStmtBegin(NodeBlock b) {
        this.b = b;
    }

    /**
     * eval method
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return b.eval(env);
    }
}