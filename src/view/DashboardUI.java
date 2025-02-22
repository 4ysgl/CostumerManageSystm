package view;

import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;

public class DashboardUI extends JFrame
{

    private  User user;
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane tab_menu;
    private JPanel pnl_customer;
    private JScrollPane scrl_customer;
    private JTable table1;
    private JPanel pnl_customerfilter;
    private JTextField f_customerName;
    private JButton btn_customerFilter;
    private JButton btn_customerReset;
    private JButton btn_customerAdd;
    private JComboBox cmb_customerType;
    private JLabel lbl_fcustomerName;
    private JLabel lbl_fcustomerType;


    public DashboardUI(User user)
    {

        this.user = user;
        if (user==null)
        {
            Helper.showMsg("error");
            dispose();
        }
        this.add(container);
        this.setTitle("Müşteri Yönetim Sistemi");
        this.setSize(1000,500);
        int x=(Toolkit.getDefaultToolkit().getScreenSize().width -this.getSize().width)/2;
        int y=(Toolkit.getDefaultToolkit().getScreenSize().height -this.getSize().height)/2;
        this.setLocation(x,y);
        this.setVisible(true);
        this.lbl_welcome.setText("Hoşgeldin :" +this.user.getName());


        this.btn_logout.addActionListener(e -> //Logout botton
        {
             dispose();
             LoginUI loginUI=new LoginUI();


        });
    }
}
