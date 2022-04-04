/**
 * node factor for numbers
 */
public class NodeFactNum extends NodeFact {

    private String num;

    /**
     * constructor
     * @param num
     */
    public NodeFactNum(String num) {
	this.num=num;
    }

    /**
     * evaluation method
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
	return Double.parseDouble(num);
    }

}
