/**
 * Abstract class for blocks
 */
public class NodeBlock extends NodeStatement {

    private NodeBlock blk;
    private NodeStatement stm;

    /**
     * constructor
     * @param blk
     * @param stm
     */
    public NodeBlock(NodeStatement stm, NodeBlock blk) {
        this.blk = blk;
        this.stm = stm;
    }

    /**
     * eval method
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        if(blk == null) {
            return stm.eval(env);  //if there's no block then evaluate the statement
        } else {
            stm.eval(env);  //if there is a block then evaluate the statement and return the block evaluation
            return blk.eval(env);
        }
    }
}