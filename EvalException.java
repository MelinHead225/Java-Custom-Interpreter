/**
 * Evaluation Exception to be thrown when exp and out != each other
 */
public class EvalException extends Exception {

    private int pos;
    private String msg;

    /**
     * Constructor
     * @param pos - position
     * @param msg - message
     */
    public EvalException(int pos, String msg) {
        this.pos=pos;
        this.msg=msg;
    }

    /**
     * Return a string message
     */
    public String toString() {
	return "eval error"
	    +", pos="+pos
	    +", "+msg;
    }
}
