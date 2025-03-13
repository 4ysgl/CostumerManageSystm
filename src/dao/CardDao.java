package dao;

import core.Database;
import entity.Card;
import entity.Customer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CardDao {
    private Connection connection;
    private CustomerDao customerDao;
    private  ProductDao productDao;

    public CardDao() {
        this.connection = Database.getInstance();
    }

    public ArrayList<Card> findAll() {
        ArrayList<Card> cards = new ArrayList<>();
        try {
            ResultSet rs=this.connection.createStatement().executeQuery("SELECT * FROM card");

            while (rs.next()) {
                cards.add(this.match(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public  Card match(ResultSet rs) throws SQLException
    {
        Card  card=new Card();
        card.setId(rs.getInt("id"));
        card.setCustomerId(rs.getInt("customer_id"));
        card.setProductId((rs.getInt("product_id")));

        card.setPrice(rs.getInt("price"));
        card.setNote(rs.getString("note"));
        card.setDate(LocalDate.parse(rs.getString("date")));
        card.setProduct(this.productDao.getById(card.getProductId()));
        card.setCustomer(this.customerDao.getById(card.getCustomerId()));

        return card;
    }

    public  boolean save(Card card)
    {
        String query="INSERT INTO card "+
                "(" +
                "customer_id,"+
                "product_id,"+
                "price,"+
                "date,"+
                "note"+
                ")" +
                "VALUES (?,?,?,?,?)" ;
        try {
            PreparedStatement pr=this.connection.prepareStatement(query);
            pr.setInt(1,card.getId());
            pr.setInt(2,card.getCustomerId());
            pr.setInt(3,card.getProductId());
            pr.setInt(4,card.getPrice());
            pr.setDate(6, Date.valueOf(card.getDate()));
            pr.setString(5,card.getNote());

            return pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
