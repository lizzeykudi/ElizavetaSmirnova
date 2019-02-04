package hw3;

public enum Users {
    PETER("", "", "");

    String login;
    String password;
    String nick;

    Users(String login, String password, String nick) {
        this.login = login;
        this.password = password;
        this.nick = nick;
    }
}
