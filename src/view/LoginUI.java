package view;

import core.Helper;
import entity.User;
import business.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame
{
    private final Object userController;
    private JPanel contanier;
    private JPanel pnl;
    private JLabel lbl_top;
    private JPanel pnl_bottom;
    private JButton btn_giris;
    private JLabel lbl_mail;
    private JLabel lbl_password;
    private JPasswordField fld_password;
    private JTextField fld_mail;

    public  LoginUI()
    {
        this.userController=new UserController();
        this.add(contanier);
        this.setTitle("Müşteri Yönetim Sistemi");
        this.setSize(400,400);

        int x=(Toolkit.getDefaultToolkit().getScreenSize().width -this.getSize().width)/2;
        int y=(Toolkit.getDefaultToolkit().getScreenSize().height -this.getSize().height)/2;
        this.setLocation(x,y);
        this.setVisible(true);


        this.btn_giris.addActionListener(e ->

        {
            JTextField[] chechList = { this.fld_mail,this.fld_password};

           if (!Helper.isEmailValid(this.fld_mail.getText()))
           {
               // hata mesajı ıcın
         Helper.showMsg("mail"); }
            if (Helper.isFieldListEmpty(chechList))
            {
               Helper.showMsg("fill");

            }

            else
            {
                User user= ((UserController) this.userController).findByLogin(this.fld_mail.getText(),this.fld_password.getText());
          if (user==null) {
              Helper.showMsg("GİRDİĞİNİZ BİLGİLER BULUNAMADU");
          }
          else {
         this.dispose(); // eğer işlemler dogru ılerledıyse DashboardUI Formunu acacak.
              DashboardUI dashboardUI=new DashboardUI(user);
          }
            }


        });

    }

}
