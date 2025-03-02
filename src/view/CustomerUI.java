package view;

import business.CustomerController;
import core.Helper;
import entity.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerUI extends JFrame
{

    private JPanel container;
    private JTextField fldCName;
    private JComboBox<Customer.TYPE> cmbCType;
    private JTextField fldCPhone;
    private JTextField fldCMail;
    private JTextArea areaCAddress;
    private JButton btnSave;
    private JLabel lblTitle;
    private  Customer customer;
    private  CustomerController customerController;


public CustomerUI(Customer customer)
{
this.customer=customer ;
this.customerController = new CustomerController();
    this.add(container);
    this.setTitle("Müşteri  EKLE /DÜZENLE");
    this.setSize(300, 500);
    int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
    int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
    this.setLocation(x, y);
    this.setVisible(true);
    this.cmbCType.setModel(new DefaultComboBoxModel<>(Customer.TYPE.values()));
    if (this.customer.getId()==0)
    {
        this.lblTitle.setText("Müşteri Ekle");
    }else
    {
        this.lblTitle.setText("Müşteri Düzenle");
        this.fldCName.setText(this.customer.getName());
        this.fldCPhone.setText(this.customer.getPhone());
        this.fldCMail.setText(this.customer.getMail());
        this.areaCAddress.setText(this.customer.getAddress());
        this.cmbCType.getModel().setSelectedItem(this.customer.getType());
    }

   this.btnSave.addActionListener(e ->{

JTextField[]checkList={this.fldCName,this.fldCPhone};
if (Helper.isFieldListEmpty(checkList))
{
    Helper.showMsg("fill");
}
else if(!this.fldCMail.getText().trim().isEmpty() && !Helper.isEmailValid(this.fldCMail.getText()))
{
Helper.showMsg("lÜTFEN GEÇERLİ EPOSTA GİRİN");
}
else
{
 boolean result = false;
 this.customer.setName((this.fldCName.getText()));
 this.customer.setPhone(this.fldCPhone.getText());
 this.customer.setMail(this.fldCMail.getText());
 this.customer.setAddress(this.areaCAddress.getText());
 this.customer.setType((Customer.TYPE)this.cmbCType.getSelectedItem());

 if (this.customer.getId()==1)
 {
     result=this.customerController.save(this.customer);

 }
 if (result)
 {
     Helper.showMsg("done");
     dispose();
 }else {
     Helper.showMsg("error");
 }
}
    });
}
}
