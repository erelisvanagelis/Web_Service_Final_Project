package lt.viko.eif.pyritefarmers.taxesapi.Repositories;

import lt.viko.eif.pyritefarmers.taxesapi.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private static List<User> userList;
    private static User loggedInUser;
    private static User registeredUser;

    public UserRepo(){
        if (userList == null) {
            userList = new ArrayList<>();
        }
        userList.add(new User("Adam1","Traore1","XXTentac"));
        userList.add(new User("Adam2","Traore2","TankiOnline"));

    }

    public void Login(String nickName, String password) throws Exception {
        loggedInUser = null;
        for (User u : userList){

            if(u.getNickname().equals(nickName) && u.getPassword().equals(password)){
                loggedInUser=u;
                System.out.println(u);
                break;
            }
        }
        if (loggedInUser == null){
            throw new Exception("wrong credentials");
        }
    }
    public void Register(String nickName, String Password, String Email) throws Exception {
        registeredUser = null;
    boolean used = false;
        for (User u : userList){

            if(u.getNickname().equals(nickName) || u.getPassword().equals(Email)){

                System.out.println("Used credencials");
                used=true;
                break;
            }

        }
        if(used==false) {
            userList.add(new User(nickName, Password, Email));
            for (User s : userList) {
                if (s.getNickname().equals(nickName) && s.getEmail().equals(Email)) {
                    System.out.println(s + "Registered user");
                    registeredUser = s;
                }
            }

        } }

        public List<User> GetUsers(){ return userList; }
    public User GetLoggedInUser ()
    {
        return loggedInUser;
    }
    public User GetRegisteredUser ()
    {
        return registeredUser;
    }
}
