package lt.viko.eif.pyritefarmers.flightsapi.models;

import java.util.List;

public class User {
    private int id;
    private String nickname;
    private String password;
    private String email;
    private List<Options> optionsList;

    public User(int id, String nickname, String password, String email) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public User(String nickname, String password, String email) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Options> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<Options> optionsList) {
        this.optionsList = optionsList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", optionsList=" + optionsList +
                '}';
    }
}
