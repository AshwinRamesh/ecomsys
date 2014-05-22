package models.orderProducts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import models.orders.*;

public class OrderProductDAO {
    private DataSource ds;
    private String createOrderProduct = "INSERT INTO order_products (product_id, product_name, description, quantity, order_id, cost) VALUES (?, ?, ?, ?, ?, ?)";
    private String getOrderProducts = "SELECT * FROM order_products WHERE order_id = ?";

     public OrderProductDAO() throws Exception{
        try{
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            // Look up our data source
            ds = (DataSource) envCtx.lookup("jdbc/ShoppingCart");
        }catch (NamingException e){
            throw new Exception("cannot find database");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Boolean insertOrderProudct(OrderProduct op) {
        try {
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(createOrderProduct, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(op.getProductId()));
            ps.setString(2, op.getProductName());
            ps.setString(3, op.getDescription());
            ps.setString(4, String.valueOf(op.getQuantity()));
            ps.setString(5, String.valueOf(op.getOrderId()));
            ps.setString(6, String.valueOf(op.getCost()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Creating Order Failed");
            }
            ps.close();
            c.close();
            return true;
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            System.out.println("Error in creating order product;");
            return false;
        }
    }

    public List<OrderProduct> getOrderProductsForOrder(int orderId) {
        try{
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(getOrderProducts);
            ps.setString(1, String.valueOf(orderId));
            ResultSet rs = ps.executeQuery();
            List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
            while(rs.next()) {
                orderProducts.add(new OrderProduct(
                    rs.getString("product_id"),
                    rs.getInt("order_id"),
                    rs.getString("product_name"),
                    rs.getString("description"),
                    rs.getInt("quantity"),
                    rs.getDouble("cost")
                ));
            }
            rs.close();
            ps.close();
            c.close();
            return orderProducts;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error in getting user order products");
            return null;
        }
    }

}
