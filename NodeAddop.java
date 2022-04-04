/**
 * Node used for addition operations
 */
public class NodeAddop extends Node {
	
	private int index;
    private String addop;

	/**
 	* Node used for addition operations
 	*/
    public NodeAddop(int index, String addop) {
	this.index=index;
	this.addop=addop;
    }

	/**
	 * Evaluation method
	 * @param o1
	 * @param o2
	 * @return
	 * @throws EvalException
	 */
    public double op(double a, double b) throws EvalException {
	if (addop.equals("+"))
	    return a+b;
	if (addop.equals("-"))
	    return a-b;
	throw new EvalException(index,"bogus addop: "+ addop);
    }

}
