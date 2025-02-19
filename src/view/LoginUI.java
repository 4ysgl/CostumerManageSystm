package view;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {
    private JPanel contanier;
    private JPanel pnl;
    private JLabel lbl_top;
    private JPanel pnl_bottom;
    private JTextField textField1;
    private JButton btn_gırıs;
    private JLabel lbl_mail;
    private JLabel lbl_password;
    private JPasswordField fld_password;

    public  LoginUI()
    {
        this.add(contanier);
        this.setTitle("Müşteri Yönetim Sistemi");
        this.setSize(400,400);

        int x=(Toolkit.getDefaultToolkit().getScreenSize().width -this.getSize().width)/2;
        int y=(Toolkit.getDefaultToolkit().getScreenSize().height -this.getSize().height)/2;
        this.setLocation(x,y);
        this.setVisible(true);

    }



}
