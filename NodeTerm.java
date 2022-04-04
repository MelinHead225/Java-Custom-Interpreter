/**
 * node class for terms
 */
public class NodeTerm extends Node {

    private NodeFact fact;
    private NodeTerm term;
	private NodeMulop mulop;

	/**
	 * constructor
	 * @param fact
	 * @param mulop
	 * @param term
	 */
    public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term) {
	this.fact=fact;
	this.mulop=mulop;
	this.term=term;
    }

	/**
	 * attach method
	 * @param term
	 */
    public void append(NodeTerm term) {
	if (this.term==null) {	//if null begin assignments
	    this.mulop=term.mulop;
	    this.term=term;
	    term.mulop=null;
	} else	//else append term to term
	    this.term.append(term);
    }

	/**
	 * evalutation method
	 * @param env
	 * @return
	 * @throws EvalException
	 */
    public double eval(Environment env) throws EvalException {
		if(term == null) {
			return fact.eval(env);
		} else {
			return mulop.op(term.eval(env), fact.eval(env));
		}
	}
}
