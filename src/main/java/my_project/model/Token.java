package my_project.model;

public class Token<TType, TName> {
    private final TType tokenType;
    private final TName tokenName;

    public Token(TType tokenType, TName tokenName) {
        this.tokenType = tokenType;
        this.tokenName = tokenName;
    }

    public TType getTokenType() {
        return tokenType;
    }

    public TName getTokenName() {
        return tokenName;
    }
}