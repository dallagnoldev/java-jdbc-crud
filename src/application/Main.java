package application;

import db.DB;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        PreparedStatement stInsert = null;
        Statement stSelect = null;
        ResultSet rs = null;
        PreparedStatement stUpdate = null;
        PreparedStatement stDelete = null;

        try {
            conn = DB.getConnection();

            // Insert

            System.out.print("Enter product name: ");
            String name = sc.nextLine();
            System.out.print("Enter product price: ");
            double price = sc.nextDouble();

            System.out.print("Enter category id: ");
            int categoryId = sc.nextInt();

            stInsert = conn.prepareStatement("INSERT INTO product (Name, Price, CategoryId) VALUES " +
                    "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            stInsert.setString(1, name);
            stInsert.setDouble(2, price);
            stInsert.setInt(3, categoryId);

            stInsert.executeUpdate();

            // Read
            stSelect = conn.createStatement();
            rs = stSelect.executeQuery("SELECT * FROM product");

            while (rs.next()) {
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name") + ", " + rs.getDouble("Price"));
            }

            // Update

            System.out.print("Enter the id of the product you want to UPDATE: ");
            int updateId = sc.nextInt();
            sc.nextLine();

            System.out.print("Update name: ");
            name = sc.nextLine();
            System.out.print("Update price: ");
            price = sc.nextDouble();

            stUpdate = conn.prepareStatement("UPDATE product " +
                    "SET Name = ?, Price = ?" +
                    "WHERE Id = ?");

            stUpdate.setString(1, name);
            stUpdate.setDouble(2, price);
            stUpdate.setInt(3, updateId);

            stUpdate.executeUpdate();

            // Delete
            System.out.print("Enter the id of the product you want to DELETE: ");
            int deleteId = sc.nextInt();

            stDelete = conn.prepareStatement("DELETE FROM product WHERE Id = ?");
            stDelete.setInt(1, deleteId);

            int rows = stDelete.executeUpdate();

            if (rows > 0) {
                System.out.println("Product deleted!");
            }
            else {
                System.out.println("ID not found.");
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(stInsert);
            DB.closeStatement(stUpdate);
            DB.closeStatement(stSelect);
            DB.closeStatement(stDelete);

            DB.closeResultSet(rs);

            DB.closeConnection();
        }


        sc.close();
    }
}
