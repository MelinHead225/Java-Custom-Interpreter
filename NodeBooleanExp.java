/**
 * Node used for boolean expressions
 */
public class NodeBooleanExp extends Node {

    private NodeExpr firstExpr; //need two expression for each relational operator
    private NodeExpr lastExpr;
    private NodeRelop relop;    //relational operator

	/**
    * Constructor 
    * Take parameters in order of first expression, relational operator, last expression 
    */
    public NodeBooleanExp(NodeExpr firstExpr, NodeRelop relop, NodeExpr lastExpr) {
        this.firstExpr=firstExpr;
        this.lastExpr=lastExpr;
        this.relop=relop;
    }

    /**
     * eval method for boolean expressions
     * @param env
     * @return
     * @throws EvalException
     */
	public double eval(Environment env) throws EvalException {
        return relop.op(firstExpr.eval(env), lastExpr.eval(env));
    }

}
