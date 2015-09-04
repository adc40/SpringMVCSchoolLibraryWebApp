package com.springapp.mvc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abram on 8/11/2015.
 */
public class BookDao {

        static {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
            }
        }

        private Connection getConnection() throws SQLException {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore",
                    "root", "tonka1");
        }

        private void closeConnection(Connection connection) {
            if (connection == null)
                return;
            try {
                connection.close();
            } catch (SQLException ex) {
            }
        }

        public boolean tableExists (String username) {
            boolean exists = false;
            Connection conn = null;
            try {
                conn = getConnection();
                PreparedStatement statement = conn.prepareStatement("show tables like ?");
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    exists = true;
                }
            }catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(conn);
            }
            return exists;
        }

        public boolean passwordCheck(String username, String password) {
            boolean correct = false;
            Connection connection = null;
            try {
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "select * from users where name = ?");
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    if (password.equals(resultSet.getString("password"))) {
                        correct = true;
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(connection);
            }
            return correct;
        }

        public void createUser(String username, String password){
            Connection connection = null;
            try {
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "insert into users (name, password) values (?, ?)");
                statement.setString(1, username);
                statement.setString(2, password);
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(connection);
            }
        }

        public void createBooksTable(String username) {
            Connection connection = null;
            String sql = "create table " + username + " (id int not null auto_increment, color VARCHAR(20), level int, " +
                    "title VARCHAR(60) not null, author VARCHAR(60) not null, category VARCHAR(30), year int," +
                    "availability char(5), primary key (id))";
            try {
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(connection);
            }
        }

        public List<Book> allBooks (String username) {
            ArrayList<Book> bookList = new ArrayList();
            String sql = "select * from " + username;
            Connection conn = null;
            try {
                conn = getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setId(resultSet.getInt("Id"));
                    book.setColor(resultSet.getString("Color"));
                    book.setLevel(resultSet.getInt("Level"));
                    book.setTitle(resultSet.getString("Title"));
                    book.setCategory(resultSet.getString("Category"));
                    book.setAuthor(resultSet.getString("Author"));
                    book.setYear(resultSet.getInt("Year"));
                    book.setAvailability(resultSet.getString("Availability"));
                    bookList.add(book);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(conn);
            }
            return bookList;
        }

        public List<Book> allBooks (String username, String keyword) {
            ArrayList<Book> bookList = new ArrayList();
            String sql = "select * from " + username + " order by " + keyword;
            Connection conn = null;
            try {
                conn = getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setId(resultSet.getInt("Id"));
                    book.setColor(resultSet.getString("Color"));
                    book.setLevel(resultSet.getInt("Level"));
                    book.setTitle(resultSet.getString("Title"));
                    book.setCategory(resultSet.getString("Category"));
                    book.setAuthor(resultSet.getString("Author"));
                    book.setYear(resultSet.getInt("Year"));
                    book.setAvailability(resultSet.getString("Availability"));
                    bookList.add(book);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(conn);
            }
            return bookList;
        }

        public void addBook(String username, Book book) {
            Connection connection = null;
            try {
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "insert into " + username + " (title, author, category, year, color, level, availability)" +
                                " values (?, ?, ?, ?, ?, ?, ?)");
                statement.setString(1, book.getTitle());
                statement.setString(2, book.getAuthor());
                statement.setString(3, book.getCategory());
                statement.setInt(4, book.getYear());
                statement.setString(5, book.getColor());
                statement.setInt(6, book.getLevel());
                statement.setString(7, book.getAvailability());
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(connection);
            }
        }

        public ArrayList<Book> searchBooks(String username,String keyword) {
            ArrayList<Book> bookList = new ArrayList();
            Connection connection = null;
            try {
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "select *  from " + username + " where (title like '%" + keyword.trim() + "%'"
                                +
                                "OR author like '%" + keyword.trim() + "%')");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setId(resultSet.getInt("Id"));
                    book.setColor(resultSet.getString("Color"));
                    book.setLevel(resultSet.getInt("Level"));
                    book.setTitle(resultSet.getString("Title"));
                    book.setCategory(resultSet.getString("Category"));
                    book.setAuthor(resultSet.getString("Author"));
                    book.setYear(resultSet.getInt("Year"));
                    book.setAvailability(resultSet.getString("Availability"));
                    bookList.add(book);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(connection);
            }
            return bookList;
        }

        public ArrayList<Book> advancedSearchBooks(String username, String title, String author, String color, String level) {
            ArrayList<Book> bookList = new ArrayList();
            Connection connection = null;
            String sql = "select * from " + username + " where ";
            String sqlTitle = "(title like '%";
            String sqlAuthor = "(author like '%";
            String sqlColor = "(color like '%";
            String sqlLevel = "(level like '%";
            boolean addOn = false;
            try {
                connection = getConnection();
                if(title.length()>0){
                    sql = sql + sqlTitle + title + "%')";
                    addOn = true;
                }
                if(author.length()>0 && addOn){
                    sql = sql + "AND" + sqlAuthor + author + "%')";
                } else if (author.length()>0){
                    sql = sql + sqlAuthor + author + "%')";
                    addOn = true;
                }
                if(color.length()>0 && addOn){
                    sql = sql + "AND" + sqlColor + color + "%')";
                } else if (color.length()>0){
                    sql = sql + sqlColor + color + "%')";
                    addOn = true;
                }
                if(level.length()>0 && addOn){
                    sql = sql + "AND" + sqlLevel + level + "%')";
                } else if (level.length()>0){
                    sql = sql + sqlLevel + level + "%')";
                }
                sql = sql + ";";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setId(resultSet.getInt("Id"));
                    book.setColor(resultSet.getString("Color"));
                    book.setLevel(resultSet.getInt("Level"));
                    book.setTitle(resultSet.getString("Title"));
                    book.setCategory(resultSet.getString("Category"));
                    book.setAuthor(resultSet.getString("Author"));
                    book.setYear(resultSet.getInt("Year"));
                    book.setAvailability(resultSet.getString("Availability"));
                    bookList.add(book);
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(connection);
            }
            return bookList;
        }

        public Book getBook(String username, int id) {
            Connection connection = null;
            Book book = new Book();
            try{
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "select * from " + username + " where id = ?");
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    book.setId(id);
                    book.setColor(resultSet.getString("Color"));
                    book.setLevel(resultSet.getInt("Level"));
                    book.setTitle(resultSet.getString("Title"));
                    book.setCategory(resultSet.getString("Category"));
                    book.setAuthor(resultSet.getString("Author"));
                    book.setYear(resultSet.getInt("Year"));
                    book.setAvailability(resultSet.getString("Availability"));

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(connection);
            }
            return book;
        }

        public void changeAvail(String username, int bookId) {
            Connection connection = null;
            Book book = getBook(username, bookId);
            try {
                connection = getConnection();
                if (book.getAvailability().equals("in")) {
                    PreparedStatement statement = connection.prepareStatement("UPDATE " + username +
                            " set availability = 'out' where id = " + bookId);
                    statement.executeUpdate();
                } else {
                    PreparedStatement statement = connection.prepareStatement("UPDATE " + username +
                            " set availability = 'in' where id = " + bookId);
                    statement.executeUpdate();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(connection);
            }
        }

        public void delete(String username, int id) {
            Connection connection = null;
            try {
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("delete from " + username + " where id=?");
                statement.setInt(1, id);
                statement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(connection);
            }
        }
}
