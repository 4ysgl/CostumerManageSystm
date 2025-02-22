import business.UserController;
import core.Database;
import core.Helper;
import entity.User;
import view.DashboardUI;
import view.LoginUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App
{
    public static void main(String[] args)
    {
//Connection connect1= Database.getInstance();// baglantı saglandı

        Helper.setTheme();
      //  LoginUI loginUI=new LoginUI();
        UserController userController=new UserController();
        User user= userController.findByLogin("demo1@gmail.com","123");

        DashboardUI dashboardUI=new DashboardUI(user);


    }





}
