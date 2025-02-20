package view;

import core.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame
{
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
               JOptionPane.showMessageDialog(null,"EPOSTA GECERSIZ","HATAA",JOptionPane.INFORMATION_MESSAGE);
           }
            if (Helper.isFieldListEmpty(chechList))
            {
                System.out.println("lütfen alanı doldur");

            }
            else
            {
                System.out.println("giriş yapın");
            }



        });

    }

}
