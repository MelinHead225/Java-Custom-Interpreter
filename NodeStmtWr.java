/**
 * node statment class for Wr output
 */
public class NodeStmtWr extends NodeStatement {
    
    private NodeExpr expr;

    /**
     * constructor
     * @param expr
     */
    public NodeStmtWr(NodeExpr expr) {
        this.expr = expr;
    }

    /**
     * evaluation method.  Prints out program to console.
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        double evaluation = expr.eval(env);
        System.out.println(evaluation);
        return evaluation;
    }
}