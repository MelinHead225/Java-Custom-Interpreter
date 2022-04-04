/**
 * node class for factor ids
 */
public class NodeFactId extends NodeFact {
    private int index;
    private String s;

    /**
     * constructor
     * @param index
     * @param s
     */
    public NodeFactId(int index, String s) {
	this.index=index;
	this.s=s;
    }

    /**
     * evaluation method
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
	return env.get(index,s);
    }

}
