package view;

import business.CustomerController;
import business.ProductController;
import core.Helper;
import core.Item;
import entity.Customer;
import entity.Product;
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
    private JTextField fld_customerName;
    private JButton btn_customerFilter;
    private JButton btn_customerReset;
    private JButton btn_customerAdd;
    private JComboBox cmb_customerType;
    private JLabel lbl_fcustomerName;
    private JLabel lbl_fcustomerType;
    private JPanel pnl_Product;
    private JTable tbl_product;
    private JPanel pnl_productFilter;
    private JTextField fldUrunName;
    private JTextField fdlPCode;
    private JComboBox cmbPStock;
    private JButton btnPSearch;
    private JButton btnUDelete;
    private JButton btnUAdd;
    private JLabel lblUCode;
    private JLabel lblPStok;
    private CustomerController customerController;
    private ProductController productController;
    private DefaultTableModel tmdl_customer = new DefaultTableModel();
    private DefaultTableModel tmdl_product = new DefaultTableModel();
    private JPopupMenu popupProduct = new JPopupMenu();

    private JPopupMenu popupCustomer = new JPopupMenu();

    public DashboardUI(User user) {

        this.user = user;
        this.customerController = new CustomerController();
        this.productController = new ProductController();
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

        //CUSTOMER TAB
        loadCustomerTable(null);
        loadCustomerPopupMenu();
        loadCustomerButtonEvent();
        this.cmb_customerType.setModel(new DefaultComboBoxModel<>(Customer.TYPE.values()));
        this.cmb_customerType.setSelectedItem(null);

//PRODUCT TABLE
        loadProductTable(null);
        loadProductPopupMenu();
        loadProductButtonEvent();
        this.cmbPStock.addItem(new Item(1,"Stokta Var"));
        this.cmbPStock.addItem(new Item(2,"Stokta Yok "));
        this.cmbPStock.setSelectedItem(null);



    }

    private void loadProductButtonEvent() {
        this.btnUAdd.addActionListener(e -> {
            ProductUI productUI = new ProductUI(new Product());
            productUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadProductTable(null);
                }
            });

        });
        this.btnPSearch.addActionListener(e -> {
            ArrayList<Product> filteredProduct = this.productController.filter(
                    this.fldUrunName.getText(),
                    this.fdlPCode.getText(),
                    (Item) this.cmbPStock.getSelectedItem()
            );
            loadProductTable(filteredProduct); // Tablonun güncellenmesi için eklenmeli
        });

        this.btnUDelete.addActionListener(e -> {
            this.fldUrunName.setText(null);
            this.fdlPCode.setText(null);
            this.cmbPStock.setSelectedItem(null);
            loadProductTable(null);

        });


    }

    // product table
    private void loadProductPopupMenu() {

        this.tbl_product.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_product.rowAtPoint(e.getPoint());
                tbl_product.setRowSelectionInterval(selectedRow, selectedRow);

            }
        });
        this.popupProduct.add("Güncelle").addActionListener(e ->
        {
            int selectedId = Integer.parseInt((this.tbl_product.getValueAt(this.tbl_product.getSelectedRow(), 0).toString()));
            ProductUI productUI = new ProductUI(this.productController.getById(selectedId));
            productUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadProductTable(null);
                }
            });
        });

        this.popupProduct.add("Sil").addActionListener(e ->
        {
            int selectedId = Integer.parseInt((tbl_product.getValueAt(tbl_product.getSelectedRow(), 0).toString()));
            if (Helper.confirm("sure")) {
                if (this.productController.delete(selectedId)) {
                    Helper.showMsg("done");
                    loadProductTable(null);
                } else {
                    Helper.showMsg("error");

                }
            }
        });
        this.tbl_product.setComponentPopupMenu(this.popupProduct);


    }

    private void loadProductTable(ArrayList<Product> products) {
        Object[] columnProduct = {"ID", "Ürün Adı", "Ürün Kodu", "Ürün Fiyatı", "Ürün Stok"};
        if (products == null) {
            products = this.productController.findAll();
        }
// tabloyu sıfırlama / tablo cagırıldıgında asagıya dogru tekrar etmemesı ıcın
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_product.getModel();
        clearModel.setRowCount(0);

        this.tmdl_product.setColumnIdentifiers(columnProduct);

        for (Product product : products) {
            Object[] rowObject =
                    {
                            product.getId(),
                            product.getName(),
                            product.getCode(),
                            product.getPrice(),
                            product.getStock()

                    };
            this.tmdl_product.addRow(rowObject);
        }
        this.tbl_product.setModel(tmdl_product);
        this.tbl_product.getTableHeader().setReorderingAllowed(false);
        this.tbl_product.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tbl_product.setEnabled(false);

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
        this.btn_customerFilter.addActionListener(e -> {

            ArrayList<Customer> filteredCustomers = this.customerController.filter(
                    this.fld_customerName.getText(),
                    (Customer.TYPE) this.cmb_customerType.getSelectedItem()
            );
            loadCustomerTable(filteredCustomers);
        });
        this.btn_customerReset.addActionListener(e -> {

            loadCustomerTable(null);
            this.fld_customerName.setText(null);
            this.cmb_customerType.setSelectedItem(null);
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
            if (Helper.confirm("sure")) {
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
