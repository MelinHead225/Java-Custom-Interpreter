import java.util.*;

/**
 * This class is a recursive-descent parser,
  modeled after the programming language's grammar.
  It constructs and has-a Scanner for the program
  being parsed.
 */
public class Parser {

    private Scanner scanner;

	/**
	 * adds new tokens to scanner
	 */
    private void match(String s) throws SyntaxException {
	scanner.match(new Token(s));
    }

	/**
	 * current token
	 */
    private Token curr() throws SyntaxException {
	return scanner.curr();
    }

	/**
	 * current pos
	 */
    private int pos() {
	return scanner.pos();
    }

	/**
	 * create mulops node if current token is of the required token
	 */
    private NodeMulop parseMulop() throws SyntaxException {
	if (curr().equals(new Token("*"))) {	//if a '*' is found
	    match("*");
	    return new NodeMulop(pos(),"*");
	}
	if (curr().equals(new Token("/"))) {	//if a '/' is found
	    match("/");
	    return new NodeMulop(pos(),"/");
	}
	return null;
    }

	/**
	 * create addop node if current token is of the required token
	 */
    private NodeAddop parseAddop() throws SyntaxException {
	if (curr().equals(new Token("+"))) {	//if a '+' is found
	    match("+");
	    return new NodeAddop(pos(),"+");	
	}
	if (curr().equals(new Token("-"))) {	//if a '-' is found
	    match("-");
	    return new NodeAddop(pos(),"-");
	}
	return null;
    }

	/**
	 * create fact node if current token is of the required token
	 */
    private NodeFact parseFact() throws SyntaxException {
	if (curr().equals(new Token("("))) {	//if a '(' is found
	    match("(");
	    NodeExpr expr=parseExpr();
	    match(")");
	    return new NodeFactExpr(expr);
	}
	if (curr().equals(new Token("-"))) {	//if a '-' is found
	    match("-");
	    return new NodeFactUnaryMinus(parseFact());
	}
	if (curr().equals(new Token("id"))) {	//whenever there is an id
	    Token id=curr();
	    match("id");
	    return new NodeFactId(pos(),id.lex());
	}
	Token num=curr();
	match("num");	//always match 'num' and create a FactNum
	return new NodeFactNum(num.lex());
    }

	/**
	 * create term node if current token is of the required token
	 */
    private NodeTerm parseTerm() throws SyntaxException {
	NodeFact fact=parseFact();
	NodeMulop mulop=parseMulop();
	
	if (mulop==null) {
	    return new NodeTerm(fact,null,null);
	}
	NodeTerm term=parseTerm();
	term.append(new NodeTerm(fact,mulop,null));
	return term;
    }

	/**
	 * create expr node if current token is of the required token
	 */
    private NodeExpr parseExpr() throws SyntaxException {
	NodeTerm term=parseTerm();
	NodeAddop addop=parseAddop();
	if (addop==null) {
	    return new NodeExpr(term,null,null);
	}
	NodeExpr expr=parseExpr();
	expr.append(new NodeExpr(term,addop,null));
	return expr;
    }

	/**
	 * create a node block if the current token is of ;
	 * @return
	 * @throws SyntaxException
	 */
	private NodeBlock parseBlock() throws SyntaxException {
		NodeBlock b = null;
		NodeStatement stmt = parseStmt();
		if(curr().equals(new Token(";"))) {		//recusively parse statements for every ';' that is found
			match(";");
			b = parseBlock();
		}
		return new NodeBlock(stmt, b);
	}

	/**
	 * create assignment node if current token is of the required token
	 */
    private NodeAssn parseAssn() throws SyntaxException {
	Token id=curr();
	match("id");
	match("=");
	NodeExpr expr=parseExpr();
	NodeAssn assn=new NodeAssn(id.lex(),expr);
	return assn;
    }

	/**
	 * create statement node if based on new stmt nodes
	 */
    private NodeStatement parseStmt() throws SyntaxException {
		if (curr().equals(new Token("begin"))) {	//if a begin token is found
			match("begin");
			NodeBlock b = parseBlock();
			match("end");
			NodeStmtBegin begin = new NodeStmtBegin(b);
			return begin;
		} else if(curr().equals(new Token("while"))) {	//if a while token is read
			match("while");	
			NodeBooleanExp boolEx = parseBooleanExp();
			match("do");
			NodeStatement whileStmt = parseStmt();
			NodeStmtWhile stmtWhile = new NodeStmtWhile(boolEx, whileStmt);
			return stmtWhile;
		} else if(curr().equals(new Token("rd"))) {	//if a rd is found
				match("rd");
				Token id = curr();
				match("id");
				return new NodeStmtRd(id.lex());
		} else if(curr().equals(new Token("wr"))) {	//if a wr is found
			match("wr");
			NodeExpr expr = parseExpr();
			return new NodeStmtWr(expr);
		} else if(curr().equals(new Token("if"))) {		//if statement must account for else statement after!
			match("if");
			NodeBooleanExp bool = parseBooleanExp();	//this statement is the reason for why I needed an extension for this assignment -_-
			match("then");
			NodeStatement ifThen = parseStmt();
			NodeStatement elseStmt = null;
			if(curr().lex().equals("else")) {	//if parser finds an 'else'
				match("else");
				elseStmt = parseStmt();
			}
			return new NodeStmtIf(bool, ifThen, elseStmt);	//will return else statement as null if 'else' is not found
		}
		NodeAssn as = parseAssn();	//always parse an assignment
		return new NodeStmtAssn(as);
    }

	/**
	 * creates a NodeBooleanExpression
	 * @return
	 * @throws SyntaxException
	 */
	private NodeBooleanExp parseBooleanExp() throws SyntaxException {
		NodeExpr a = parseExpr();
		NodeRelop relop = parseRelop();	//the relop MUST be parsed before the second expr and after the first expr
		NodeExpr b = parseExpr();
		
		NodeBooleanExp expr = new NodeBooleanExp(a, relop, b);
		return expr;
	}

	/**
	 * create relop node if current token is of the same type
	 * @return
	 * @throws SyntaxException
	 */
	private NodeRelop parseRelop() throws SyntaxException {
		if(curr().equals(new Token(">"))) {	//if a '>' is found
			match(">");
			return new NodeRelop(pos(), ">");
		} else if(curr().equals(new Token(">="))) {	//if a '>=' is found
			match(">=");
			return new NodeRelop(pos(), ">=");
		} else if(curr().equals(new Token("<>"))) {	//if a '<>' is found
			match("<>");
			return new NodeRelop(pos(), "<>");
		} else if(curr().equals(new Token("=="))) {	//if a '==' is found
			match("==");
			return new NodeRelop(pos(), "==");
		} else if(curr().equals(new Token("<"))) {	//if a '<' is found		
			match("<");
			return new NodeRelop(pos(), "<");
		} else if(curr().equals(new Token("<="))) {	//if a '<=' is found
			match("<=");
			return new NodeRelop(pos(), "<=");
		}
		return null;
	}

	/**
	 * updated parse method from original.  Now accounts for several blocks of code
	 * provides my the parseProgBlock method.  The interpreter now can run multiple
	 * statements at once!
	 * @param program
	 * @return
	 * @throws SyntaxException
	 */
    public Node parse(String program) throws SyntaxException {
		scanner=new Scanner(program);
		scanner.next();
		NodeBlock prog = parseBlock();
		return prog;
    }
}
