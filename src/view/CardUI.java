package view;

import business.BasketController;
import business.CardController;
import business.ProductController;
import core.Helper;
import entity.Basket;
import entity.Card;
import entity.Customer;
import entity.Product;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CardUI  extends JFrame{
     private BasketController basketController;
     private  CardController cardController;
     private ProductController productController;
    private JPanel container;
    private JLabel lblCustomerName;
    private JTextField fldCardDate;
    private JTextArea txtAreaNote;
    private JButton btnCard;
    private Customer customer;
    public CardUI(Customer customer)
    {
        this.customer = customer;
        this.basketController = new BasketController();
        this.cardController = new CardController();
        this.productController =new ProductController();
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



        this.btnCard.addActionListener(e -> {
            if (Helper.isFieldEmpty(this.fldCardDate)) {
                Helper.showMsg("fill");
            }  else
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                for (Basket basket : baskets) {
                    if (basket.getProduct().getStock() <= 0) continue;
                    Card card = new Card();
                    card.setCustomerId(this.customer.getId());
                    card.setProductId(basket.getProductId());
                    card.setPrice(basket.getProduct().getPrice());
                    card.setDate(LocalDate.parse(this.fldCardDate.getText(), formatter));
                    card.setNote(this.txtAreaNote.getText());
                    this.cardController.save(card);


                    Product unStockProduct = basket.getProduct();
                    unStockProduct.setStock(unStockProduct.getStock() - 1);
                    this.productController.update(unStockProduct);

                }

            }
            this.basketController.clear();

            Helper.showMsg("done");
            dispose();
        });

    }

    private void createUIComponents() throws ParseException {
       this.fldCardDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fldCardDate.setText(formatter.format(LocalDate.now()));

    }
}
