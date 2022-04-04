/**
 * node class for factor expressions
 */
public class NodeFactExpr extends NodeFact {

    private NodeExpr expr;

    /**
     * constructor
     * @param expr
     */
    public NodeFactExpr(NodeExpr expr) {
	this.expr=expr;
    }

    /**
     * evaluation
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
	return expr.eval(env);
    }

}
