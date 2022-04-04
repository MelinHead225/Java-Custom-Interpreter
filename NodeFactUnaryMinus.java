/**
 * node class for unary minus expressions
 */
public class NodeFactUnaryMinus extends NodeFact {

    private NodeFact num;

    /**
     * constructor
     * @param expr
     */
    public NodeFactUnaryMinus(NodeFact num) {
	this.num=num;
    }

    /**
     * evaluation
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
	return (0 - num.eval(env));
    }
}
