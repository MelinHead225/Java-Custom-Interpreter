/**
 * Node used for Assignment operations
 */
public class NodeAssn extends Node {

    private String id;
    private NodeExpr expr;

    /**
     * Node used for assignment operations
     */
    public NodeAssn(String id, NodeExpr expr) {
	this.id=id;
	this.expr=expr;
    }

    /**
	 * Evaluation method
	 * @param o1
	 * @param o2
	 * @return
	 * @throws EvalException
	 */
    public double eval(Environment env) throws EvalException {
	return env.put(id,expr.eval(env));
    }

}
