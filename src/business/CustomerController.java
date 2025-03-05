package business;

import core.Helper;
import entity.Customer;

import java.util.ArrayList;

import dao.CustomerDao;

public class CustomerController {
    private final CustomerDao customerDao = new CustomerDao();


    public ArrayList<Customer> findAll() {

        return this.customerDao.findAll();
    }

    public boolean save(Customer customer)
    {
        return this.customerDao.save(customer);
    }

    public Customer getById(int id) {
        return this.customerDao.getById(id);

    }

    public boolean update(Customer customer) {
        if (this.getById(customer.getId()) == null) {
            Helper.showMsg(customer.getId() + "ID KAYITLI MÜŞTERİ BULUNAMADI");
            return false;
        }
        return this.customerDao.update(customer);
    }
}
