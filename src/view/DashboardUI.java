package view;

import business.CustomerController;
import core.Helper;
import entity.Customer;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DashboardUI extends JFrame {

    private User user;
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane tab_menu;
    private JPanel pnl_customer;
    private JScrollPane scrl_customer;
    private JTable tblCustomer;
    private JPanel pnl_customerfilter;
    private JTextField f_customerName;
    private JButton btn_customerFilter;
    private JButton btn_customerReset;
    private JButton btn_customerAdd;
    private JComboBox cmb_customerType;
    private JLabel lbl_fcustomerName;
    private JLabel lbl_fcustomerType;
    private CustomerController customerController;
    private DefaultTableModel tmdl_customer = new DefaultTableModel();
    private JPopupMenu popupCustomer = new JPopupMenu();

    public DashboardUI(User user) {

        this.user = user;
        this.customerController = new CustomerController();
        if (user == null) {
            Helper.showMsg("error");
            dispose();
        }
        this.add(container);
        this.setTitle("Müşteri Yönetim Sistemi");
        this.setSize(1000, 500);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x, y);
        this.setVisible(true);
        this.lbl_welcome.setText("Hoşgeldin :" + this.user.getName());


        this.btn_logout.addActionListener(e -> //Logout botton
        {
            dispose();
            LoginUI loginUI = new LoginUI();


        });
        loadCustomerTable(null);
        loadCustomerPopupMenu();
        loadCustomerButtonEvent();
    }



    private void loadCustomerButtonEvent() {
        this.btn_customerAdd.addActionListener(e -> {
            CustomerUI customerUI = new CustomerUI(new Customer());
            customerUI.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    loadCustomerTable(null);
                }
            });

        });
    }

    ;


    private void loadCustomerPopupMenu() {
        this.tblCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tblCustomer.rowAtPoint(e.getPoint());
                tblCustomer.setRowSelectionInterval(selectedRow, selectedRow);

            }
        });
        this.popupCustomer.add("Güncelle").addActionListener(e ->
        {
            int selectedId = Integer.parseInt((tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 0).toString()));
            CustomerUI customerUI = new CustomerUI(this.customerController.getById(selectedId));
            customerUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCustomerTable(null);
                }
            });
        });
        this.popupCustomer.add("Sil").addActionListener(e -> {
            int selectedId = Integer.parseInt((tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 0).toString()));
           if (Helper.confirm("sure"))
           {
               if (this.customerController.delete(selectedId)) {
                   Helper.showMsg("done");
                   loadCustomerTable(null);
               } else {
                   Helper.showMsg("error");

               }
           }
        });
        this.tblCustomer.setComponentPopupMenu(this.popupCustomer);
    }





    private void loadCustomerTable(ArrayList<Customer> customers) {
        Object[] columCustomer = {"ID", "Müsteri Adı", "Tipi", "Telefon", "E-Posta", "Adres"};
        if (customers == null) {
            customers = this.customerController.findAll();
        }
// tabloyu sıfırlama / tablo cagırıldıgında asagıya dogru tekrar etmemesı ıcın
        DefaultTableModel clearModel = (DefaultTableModel) this.tblCustomer.getModel();
        clearModel.setRowCount(0);
        this.tmdl_customer.setColumnIdentifiers(columCustomer);

        for (Customer customer : customers) {
            Object[] rowObject =
                    {
                            customer.getId(),
                            customer.getName(),
                            customer.getType(),
                            customer.getPhone(),
                            customer.getMail(),
                            customer.getAddress()

                    };
            this.tmdl_customer.addRow(rowObject);
        }
        this.tblCustomer.setModel(tmdl_customer);
        this.tblCustomer.getTableHeader().setReorderingAllowed(false);
        this.tblCustomer.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tblCustomer.setEnabled(false);

    }
}
