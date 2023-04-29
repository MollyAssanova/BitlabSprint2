package kz.bitlab.techorda.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnection {
    private static Connection connection;
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/db_techorda","root","");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static User getUser(String email){
        User users = null;
        try {
            PreparedStatement statement = connection.prepareStatement(" "+ "SELECT * FROM users WHERE email = ?");
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                users = new User();
                users.setId(resultSet.getLong("id"));
                users.setEmail(resultSet.getString("email"));
                users.setPassword(resultSet.getString("password"));
                users.setFullName(resultSet.getString("full_Name"));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }



    public static ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM items");

            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Item item=new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setPrice(resultSet.getDouble("price"));

                items.add(item);
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public static Item getItem(int id){

        Item item = null;

        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM items WHERE id = ? LIMIT 1");

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                item = new Item();
                item.setName(resultSet.getString("name"));
                item.setId(resultSet.getInt("id"));
                item.setDescription(resultSet.getString("description"));
                item.setPrice(resultSet.getDouble("price"));
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return item;
    }

    public static void updateItem(Item item){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE items " +
                    "SET " +
                    "name = ?, " +
                    "price = ?, " +
                    "description = ?, " +
                    "WHERE id = ?");

            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setString(4, item.getDescription());
            statement.setInt(6, item.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteItem(int id){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM items WHERE id = ?");

            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
