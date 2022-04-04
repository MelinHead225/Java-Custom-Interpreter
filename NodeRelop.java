/**
 * Node used for addition operations
 */
public class NodeRelop extends Node {
    private int index;
    private String relop;

	/**
    * Node used for rel operations
    */
    public NodeRelop(int index, String relop) {
	this.index=index;
	this.relop=relop;
    }

	/**
	 * Evaluation method
	 * @param o1
	 * @param o2
	 * @return
	 * @throws EvalException
	 */
    public double op(double a, double b) throws EvalException {
        if (relop.equals("<")) {
            return trueOrFalse(a < b);
        }
        if (relop.equals("<=")) {
            return trueOrFalse(a <= b);
        }
        if (relop.equals(">")) {
            return trueOrFalse(a > b);
        }
        if (relop.equals(">=")) {
            return trueOrFalse(a >= b);
        }
        if (relop.equals("<>")) {
            return trueOrFalse(a != b);
        }
        if (relop.equals("==")) {
            return trueOrFalse(a == b);
        }
        throw new EvalException(index,"invalid relop: "+relop);
    }

    /**
     * return 1.0 on true and 0.0 on false
     * @param expr
     * @return
     */
    private double trueOrFalse(boolean expr) {
        if(expr) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
}