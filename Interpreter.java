/**
 * Object containing main method to run programs
 */
public class Interpreter {

    public static void main(String[] args) {
	Parser parser=new Parser();
	Environment env=new Environment();
	for (String stmt: args)
	    try {
			parser.parse(stmt).eval(env);	 /*No longer need to call sysout here because the interpreter now has
			writing functionality! */
		} 
		catch (SyntaxException e) {
			System.err.println(e);
	    } 
		catch (EvalException e) {
			System.err.println(e);
	    }
    }

}
