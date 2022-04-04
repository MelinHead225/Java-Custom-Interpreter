/**
 * node class for if statements
 */
public class NodeStmtIf extends NodeStatement {

    private NodeBooleanExp boolExpression;
    private NodeStatement a;
    private NodeStatement b;

    /**
     * contructor
     */
    public NodeStmtIf(NodeBooleanExp boolExpression, NodeStatement a, NodeStatement b) {
        this.boolExpression = boolExpression;
        this.a = a;
        this.b = b;
    }

    /**
     * evaluation method based on enviorment input
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        if(boolExpression.eval(env) == 1.0) { //true was set to be 1.0 false is 0.0
            return a.eval(env); //if true evaluate first statement
        } else if(b != null) {  //if there's an else statement
            return b.eval(env); //if false evaluate second statement
        } else {
            return 0.0;
        }
    }

}