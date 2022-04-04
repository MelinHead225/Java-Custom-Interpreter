/**
 * Token class which contains
 * 1) the token itself (e.g., "id" or "+")
 * 2) the token's lexeme (e.g., "foo")
 */
public class Token {

    private String token;
    private String lexeme;

    /**
     * constructor
     * @param token
     * @param lexeme
     */
    public Token(String token, String lexeme) {
	this.token=token;
	this.lexeme=lexeme;
    }

    /**
     * constructor
     * @param token
     */
    public Token(String token) {
	this(token,token);
    }

    /**
     * return token
     * @return
     */
    public String tok() { return token; } 
    /**
     * return lexeme
     * @return
     */
    public String lex() { return lexeme; }

    /**
     * checks if two tokens are equal
     * @param t
     * @return
     */
    public boolean equals(Token t) {
	return token.equals(t.token);
    }

    /**
     * prints token and lexeme
     */
    public String toString() {
	return "<"+tok()+","+lex()+">";
    }

}
