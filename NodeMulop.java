/**
 * node class for multiplication operations
 */
public class NodeMulop extends Node {
	private int index;
    private String mulop;

	/**
	 * constructor
	 * @param index
	 * @param mulop
	 */
    public NodeMulop(int index, String mulop) {
	this.index=index;
	this.mulop=mulop;
    }

	/**
	 * opertation method which does multiplication operations with o1 and o2
	 * @param o1
	 * @param o2
	 * @return
	 * @throws EvalException
	 */
    public double op(Double a, double b) throws EvalException {
	if (mulop.equals("*"))
	    return a*b;
	if (mulop.equals("/"))
	    return a/b;
	throw new EvalException(index,"invalid mulop: "+mulop);
    }

}
