package view;

import business.ProductController;
import core.Helper;
import entity.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductUI extends  JFrame{

    private JPanel container;
    private Product product;
    private ProductController productController;
    private JTextField fldAPName;
    private JTextField fldAddPCode;
    private JTextField fldAddPPrice;
    private JTextField fldAddPStock;
    private JButton btnAddProduct;
    private JLabel lblTitle;

    public  ProductUI(Product product)
    {
        this.product=product;
        this.productController=new ProductController();

        this.add(container);
        this.setTitle("Ürün Ekle /Düzenle");
        this.setSize(500,600);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x, y);
        this.setVisible(true);


        if (this.product.getId() == 0) {
            this.lblTitle.setText("Ürün Ekle");
        } else {
            this.lblTitle.setText("Ürün Düzenle");
            this.fldAPName.setText(this.product.getName());
            this.fldAddPCode.setText(this.product.getCode());
            this.fldAddPPrice.setText(String.valueOf(this.product.getPrice()));
            this.fldAddPStock.setText(String.valueOf(this.product.getStock()));
        }

        // Buton olayını dinle
        this.btnAddProduct.addActionListener(e -> {
            JTextField[] checkList = {
                    fldAPName,
                    fldAddPCode,
                    fldAddPPrice,
                    fldAddPStock
            };

            if (Helper.isFieldListEmpty(checkList)) {
                Helper.showMsg("fill");
            } else {
                this.product.setName(this.fldAPName.getText());
                this.product.setCode(this.fldAddPCode.getText());
                this.product.setPrice(Integer.parseInt(this.fldAddPPrice.getText()));
                this.product.setStock(Integer.parseInt(this.fldAddPStock.getText()));

                    boolean result;
                    if (this.product.getId() == 0) {
                        result = this.productController.save(this.product);
                    } else {
                        result = this.productController.update(this.product);
                    }

                    if (result) {
                        Helper.showMsg("done");
                        dispose();
                    } else {
                        Helper.showMsg("error");
                    }

            }

        });

    }


}
