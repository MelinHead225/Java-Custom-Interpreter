/**
 * exception class for when there is invalid syntax
 */
public class SyntaxException extends Exception {

    private int pos;
    private Token expected;
    private Token found;

    /**
     * constructor
     * @param pos
     * @param expected
     * @param found
     */
    public SyntaxException(int pos, Token expected, Token found) {
	this.pos=pos;
	this.expected=expected;
	this.found=found;
    }

    /**
     * Prints information regarding the reason for the exception being thrown
     */
    public String toString() {
	return "syntax error"
	    +", pos="+pos
	    +", expected="+expected
	    +", found="+found;
    }

}
