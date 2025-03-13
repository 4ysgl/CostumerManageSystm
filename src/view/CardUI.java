package view;

import business.BasketController;
import business.CardController;
import core.Helper;
import entity.Basket;
import entity.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardUI  extends JFrame{
     private BasketController basketController;
     private  CardController cardController;
    private JPanel container;
    private JLabel lblCustomerName;
    private JTextField textField1;
    private JTextArea txtAreaNote;
    private JButton btnCard;
    private Customer customer;
    public CardUI(Customer customer)
    {
        this.customer = customer;
        this.basketController = new BasketController();
        this.cardController = new CardController();
        this.add(container);
        this.setTitle("Sipariş Olustur");
        this.setSize(500, 600);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x, y);
        this.setVisible(true);
        if (customer.getId() == 0) {
            Helper.showMsg("Lütfen Gecerlı Musterı Secın");
            dispose();
        }

        ArrayList<Basket> baskets = this.basketController.findAll();
        if (baskets.size() == 0) {
            Helper.showMsg("Lütfen sepete ürün ekleyınz");
            dispose();
        }


        this.lblCustomerName.setText("Müşteri :"+ this.customer.getName());
    }
}
