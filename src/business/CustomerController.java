package business;

import entity.Customer;

import java.util.ArrayList;
import dao.CustomerDao;

public class CustomerController
{
    private final CustomerDao customerDao = new CustomerDao();



    public ArrayList<Customer>findAll(){

        return this.customerDao.findAll();
    }
}
