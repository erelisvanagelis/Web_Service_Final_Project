package lt.viko.eif.pyritefarmers.taxesapi.Repositories;

import lt.viko.eif.pyritefarmers.taxesapi.models.User;

import java.lang.annotation.Annotation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private static List<User> userList;
    private static User loggedInUser;
    private static User registeredUser;
    private Connection Conn;
    public UserRepo(){
        if (userList == null) {
            userList = new ArrayList<>();
        }
        try {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject", "root", "root");
            System.out.println("All good connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong.");
        }
        try {
            Statement stmt;
            ResultSet rs;
            stmt = Conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM finalproject.user");
            while (rs.next()) {
                int id=(rs.getInt("id"));
                String name= String.valueOf(rs.getString("nickname"));
                String password= String.valueOf(rs.getString("password"));
                String email= rs.getString("email");

                userList.add(new User(id,name,password,email));

            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
     //   userList.add(new User("Adam1","Traore1","XXTentac"));
       // userList.add(new User("Adam2","Traore2","TankiOnline"));

    }

    public void Login(String nickName, String password) throws Exception {
        new UserRepo();
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
          //  userList.add(new User(nickName, Password, Email));
            Statement statement=Conn.createStatement();
            String query = "INSERT INTO finalproject.user (nickname, password, email)"+ " values (?, ?, ?)";
            PreparedStatement preparedStmt = Conn.prepareStatement(query);
            preparedStmt.setString(1, nickName);
            preparedStmt.setString(2, Password);
            preparedStmt.setString(3, Email);
            preparedStmt.execute();
           new UserRepo();

        int rowsaffected= statement.executeUpdate(query);
        System.out.println("Rows effected "+ rowsaffected);

            for (User s : userList) {
                if (s.getNickname().equals(nickName) && s.getEmail().equals(Email)) {
                    System.out.println(s + "Registered user");
                    registeredUser = s;
                }
            }

        } }

        public List<User> GetUsers(){
            new UserRepo();
        return userList;

    }
    public User GetLoggedInUser ()
    {
        return loggedInUser;
    }
    public User GetRegisteredUser ()
    {
        return registeredUser;
    }


}
