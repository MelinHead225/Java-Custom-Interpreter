/**
 * node class for statement assignments
 */
public class NodeStmtAssn extends NodeStatement {

    private NodeAssn nodeAssn;

    /**
     * contructor
     * @param id -type of assignment
     * @param expr -expression
     */
    public NodeStmtAssn(NodeAssn nodeAssn) {
        this.nodeAssn = nodeAssn;
    }

    /**
     * evaluation method based on eviornment input
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return nodeAssn.eval(env);
    }

}