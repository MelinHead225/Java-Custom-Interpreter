/**
 * node class for while statements
 */
public class NodeStmtWhile extends NodeStatement {

    private NodeBooleanExp boolEx;
    private NodeStatement a;

    /**
     * contructor
     */
    public NodeStmtWhile(NodeBooleanExp boolEx, NodeStatement a) {
        this.boolEx = boolEx;
        this.a = a;
    }

    /**
     * evaluation method based on enviorment input
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        while(boolEx.eval(env) == 1.0) {    //if boolEx is true then run through while loop
            a.eval(env);
        }
        return 0.0; //else return 0.0
    }

}