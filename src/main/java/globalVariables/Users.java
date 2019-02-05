package globalVariables;

public enum Users {
    PETER("epam", "1234", "PITER CHAILOVSKII");

    public String login;
    public String password;
    public String nick;

    Users(String login, String password, String nick) {
        this.login = login;
        this.password = password;
        this.nick = nick;
    }
}
