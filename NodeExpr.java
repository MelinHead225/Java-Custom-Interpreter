/**
 * Node used for expressions
 */
public class NodeExpr extends Node {

    private NodeTerm term;
    private NodeAddop addop;
    private NodeExpr expr;

	/**
	 * Constructor
	 * @param term
	 * @param addop
	 * @param expr
	 */
    public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
	this.term=term;
	this.addop=addop;
	this.expr=expr;
    }

	/**
	 * attaches add operations to create an expression
	 * @param expr
	 */
    public void append(NodeExpr expr) {
	if (this.expr==null) {
	    this.addop=expr.addop;
	    this.expr=expr;
	    expr.addop=null;
	} else
	    this.expr.append(expr);
    }

	/**
	 * evalutation method
	 * @param env
	 * @return
	 * @throws EvalException
	 */
    public double eval(Environment env) throws EvalException {
		if(expr == null) {
			return term.eval(env);
		} else {
			return addop.op(expr.eval(env), term.eval(env));
		}
    }

}
