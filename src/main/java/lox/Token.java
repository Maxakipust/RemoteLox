package lox;

public class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final transient int line;
    final transient int location;

    Token(TokenType type, String lexeme, Object literal, int line, int location) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
        this.location = location;
    }

    public String toString() {
        return "[line: " + line + ": " + location + "] " + type + " " + lexeme + " " + literal;
    }

    public String toString(int tab, String str) {
        String ret = "";
        for (int i = 0; i < tab; i++) {
            ret += "\t";
        }
        ret += str;
        return ret;
    }

    public String toString(int i) {
        return toString(i, toString());
    }
}
