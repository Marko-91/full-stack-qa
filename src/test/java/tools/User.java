package tools;

public enum User {
    USERNAME ("username"),
    LASTNAME("lastName"),
    PASSWORD("password"),
    FIRSTNAME ("firstName");

    private final String element;

    public String getName() {
        return element;
    }

    User(String element) {
        this.element = element;
    }

}
