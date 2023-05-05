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


    public static boolean addComment(Comment comment){

        int rows = 0;

        try{

            PreparedStatement statement = connection.prepareStatement("" +

                    "INSERT INTO comments (user_id, news_id, comment, post_date) " +

                    "VALUES (?, ?, ?, NOW())");



            statement.setLong(1, comment.getUser().getId());

            statement.setLong(2, comment.getNews().getId());

            statement.setString(3, comment.getComment());



            rows = statement.executeUpdate();

            statement.close();



        }catch (Exception e){

            e.printStackTrace();

        }

        return rows>0;

    }



    public static ArrayList<Comment> getAllComments(Long newsId){

        ArrayList<Comment> comments = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +

                    "SELECT c.id, c.user_id, c.comment, c.news_id, u.fullname, u.email, c.post_date " +

                    "FROM comments c " +

                    "INNER JOIN users u ON c.user_id = u.id " +

                    "WHERE c.news_id = ? " +

                    "ORDER BY c.post_date DESC");

            statement.setLong(1, newsId);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){



                Comment comment = new Comment();

                comment.setId(resultSet.getLong("id"));

                comment.setComment(resultSet.getString("comment"));

                comment.setPostDate(resultSet.getTimestamp("post_date"));



                User user = new User();

                user.setId(resultSet.getLong("user_id"));

                user.setFullName(resultSet.getString("fullname"));

                user.setEmail(resultSet.getString("email"));

                comment.setUser(user);



                News news = new News();

                news.setId(resultSet.getInt("news_id"));

                comment.setNews(news);



                comments.add(comment);

            }

            statement.close();



        }catch (Exception e){

            e.printStackTrace();

        }

        return comments;

    }





    public static User getUser(String email){
        User user = null;
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ? LIMIT 1");

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

                user = new User();

                user.setEmail(resultSet.getString("email"));

                user.setId(resultSet.getLong("id"));

                user.setPassword(resultSet.getString("password"));

                user.setFullName(resultSet.getString("fullname"));

            }
            statement.close();
        }catch (Exception e){

            e.printStackTrace();
        }
        return user;

    }


    public static boolean addUser(User user){
        int rows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +

                    "INSERT INTO users (email, password, fullname) " +

                    "VALUES (?, ?, ?)");

            statement.setString(1, user.getEmail());

            statement.setString(2, user.getPassword());

            statement.setString(3, user.getFullName());
            rows = statement.executeUpdate();

            statement.close();
        }catch (Exception e){

            e.printStackTrace();

        }

        return rows>0;

    }




    public static News getNews1(Long id) {
        News news = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.title, n.content, n.post_date " + // удалена лишняя запятая после n.post_date
                            "FROM news n " +
                            "WHERE n.id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                news = new News();
                news.setId(resultSet.getInt("id")); // изменен getInt на getLong
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }











    public static ArrayList<News> getNews() {
        ArrayList<News> news = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM news");

            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                News news1=new News();
                news1.setId(resultSet.getInt("id"));
                news1.setCategory_id(resultSet.getInt("category_id"));
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));

                news.add(news1);
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }


    public static boolean addNews(News news) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO news (title, content, category_id, post_date ) " +
                            "VALUES (?, ?, ?, NOW())");
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setInt(3, news.getCategory_id());
            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }


        public static void deleteNews(int id){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM news WHERE id = ?");

            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }









//
//    public static void updateItem(Item item){
//        try{
//            PreparedStatement statement = connection.prepareStatement("" +
//                    "UPDATE items " +
//                    "SET " +
//                    "name = ?, " +
//                    "price = ?, " +
//                    "description = ?, " +
//                    "WHERE id = ?");
//
//            statement.setString(1, item.getName());
//            statement.setDouble(2, item.getPrice());
//            statement.setString(4, item.getDescription());
//            statement.setInt(6, item.getId());
//
//            statement.executeUpdate();
//            statement.close();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//


}
