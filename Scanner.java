import java.util.*;

/**
 * Scanner class used to scan test to be interpreted
 * @author ericm
 */
public class Scanner {

    private String program;	// source program being interpreted
    private int pos;		// index of next char in program
    private Token token;	// last/current scanned token

    // sets of various characters and lexemes
    private Set<String> whitespace=new HashSet<String>();
    private Set<String> digits=new HashSet<String>();
    private Set<String> letters=new HashSet<String>();
    private Set<String> legits=new HashSet<String>();
    private Set<String> keywords=new HashSet<String>();
    private Set<String> operators=new HashSet<String>();

    // initializers for previous sets

	/**
	 * filler initialization
	 * @param s	 -location being stored
	 * @param lo -low bound being stored
	 * @param hi -high bound being stored
	 */
    private void fill(Set<String> s, char lo, char hi) {
	for (char c=lo; c<=hi; c++)
	    s.add(c+"");
    }    
	
	/**
	 * initialize acceptable whitespace char(s)
	 * @param s
	 */
    private void initWhitespace(Set<String> s) {
	s.add(" ");
	s.add("\n");
	s.add("\t");
    }

	/**
	 * initialize accecptable digits
	 * @param s
	 */
    private void initDigits(Set<String> s) {
	fill(s,'0','9');
    }

	/**
	 * initialize acceptable letters
	 * @param s
	 */
    private void initLetters(Set<String> s) {
	fill(s,'A','Z');
	fill(s,'a','z');
    }

	/**
	 * initialize all acceptable letters & digits
	 * @param s
	 */
    private void initLegits(Set<String> s) {
	s.addAll(letters);
	s.addAll(digits);
    }

	/**
	 * initialize all acceptable operators
	 * @param s
	 */
    private void initOperators(Set<String> s) {
		s.add("=");
		s.add("+");
		s.add("-");
		s.add("*");
		s.add("/");
		s.add("(");
		s.add(")");
		s.add(";");
		s.add("<");		//Added new relop operators for ia2
		s.add("<=");
		s.add(">");
		s.add(">=");
		s.add("<>");
		s.add("==");
    }
	
	/**
	 * initialized all keywords for ia2
	 * @param s
	 */
    private void initKeywords(Set<String> s) {
		s.add("rd");
		s.add("wr");
		s.add("if");
		s.add("then");
		s.add("else");
		s.add("while");
		s.add("do");
		s.add("begin");
		s.add("end");
    }

    // constructor:
    //   - squirrel-away source program
    //   - initialize sets
    public Scanner(String program) {
		this.program=program;
		pos=0;
		token=null;
		initWhitespace(whitespace);
		initDigits(digits);
		initLetters(letters);
		initLegits(legits);
		initKeywords(keywords);
		initOperators(operators);
    }

    // handy string-processing methods

	/**
	 * check if end of file is reached
	 * @return - true if at end of file
	 */
    public boolean done() {
	return pos>=program.length();
    }

	/**
	 * skip past multiples
	 * @param s
	 */
    private void many(Set<String> s) {
	while (!done() && s.contains(program.charAt(pos)+""))
	    pos++;
    }
    
    // This method advances the scanner,
    // until the current input character
    // is just after a sequence of one or more
    // of a particular character.
    // Arguments:
    //     c = the character to search for
    // Members:
    //     program = the scanner's input
    //     pos = index of current input character
    private void past(char c) {
	while (!done() && c!=program.charAt(pos))
	    pos++;
	if (!done() && c==program.charAt(pos))
	    pos++;
    }

    // scan various kinds of lexeme

    private void nextNumber() {
	int old=pos;
	many(digits);
	token=new Token("num",program.substring(old,pos));
    }

    private void nextKwId() {
	int old=pos;
	many(letters);
	many(legits);
	String lexeme=program.substring(old,pos);
	token=new Token((keywords.contains(lexeme) ? lexeme : "id"),lexeme);
    }

    private void nextOp() {
	int old=pos;
	pos=old+2;
	if (!done()) {
	    String lexeme=program.substring(old,pos);
	    if (operators.contains(lexeme)) {
		token=new Token(lexeme); // two-char operator
		return;
	    }
	}
	pos=old+1;
	String lexeme=program.substring(old,pos);
	token=new Token(lexeme); // one-char operator
    }

	/**
	 * Format - #This is a comment#
	 * Skip everything until next # is found
	 */
	private void skipComments() {
		pos++;
        while (!(program.charAt(pos) + "").equals("#")) {
            pos++;
        }
        pos++;
        next();
	}

    // This method determines the kind of the next token (e.g., "id"),
    // and calls a method to scan that token's lexeme (e.g., "foo").
    public boolean next() {
	many(whitespace);
	if (done()) {
	    token=new Token("EOF");
	    return false;
	}
	String c=program.charAt(pos)+"";
	if (digits.contains(c))
	    nextNumber();
	else if (letters.contains(c))
	    nextKwId();
	else if (operators.contains(c))
	    nextOp();
	else if (c.equals("#")) //if there is a # skip all chars until next #
		skipComments();
	else {
	    System.err.println("illegal character at position "+pos);
	    pos++;
	    return next();
	}
	return true;
    }

    // This method scans the next lexeme,
    // if the current token is the expected token.
    public void match(Token t) throws SyntaxException {
	if (!t.equals(curr()))
	    throw new SyntaxException(pos,t,curr());
	next();
    }

	/**
	 * return current token
	 * @return
	 * @throws SyntaxException
	 */
    public Token curr() throws SyntaxException {
	if (token==null)
	    throw new SyntaxException(pos,new Token("ANY"),new Token("EMPTY"));
	return token;
    }

    public int pos() { return pos; }

    // for unit testing
    public static void main(String[] args) {
	try {
	    Scanner scanner=new Scanner(args[0]);
	    while (scanner.next())
		System.out.println(scanner.curr());
	} catch (SyntaxException e) {
	    System.err.println(e);
	}
    }

}
