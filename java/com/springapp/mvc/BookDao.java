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

        public List<Book> allBooks () {
            ArrayList<Book> bookList = new ArrayList();
            String sql = "select * from book";
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

        public void addBook(Book book) {
            Connection connection = null;
            try {
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "insert into Book (title, author, category, year, color, level, availability)" +
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

        public ArrayList<Book> searchBooks(String keyword) {
            ArrayList<Book> bookList = new ArrayList();
            Connection connection = null;
            try {
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "select *  from Book where (title like '%" + keyword.trim() + "%'"
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

        public ArrayList<Book> advancedSearchBooks(String title, String author, String color, int level) {
            ArrayList<Book> bookList = new ArrayList();
            Connection connection = null;
            try {
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "select *  from Book where (title like '%" + title.trim() + "%'"
                                +
                                "OR author like '%" + author.trim() + "%'"
                                +
                                "OR color like '%" + color.trim() + "%'"
                                +
                                "OR level like '%" + level + "%')");
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

        public Book getBook(int id) {
            Connection connection = null;
            Book book = new Book();
            try{
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "select * from Book where id = ?");
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

        public void changeAvail(int bookId) {
            Connection connection = null;
            Book book = getBook(bookId);
            try {
                connection = getConnection();
                if (book.getAvailability().equals("in")) {
                    PreparedStatement statement = connection.prepareStatement("UPDATE book set availability='out' " +
                            "where id = '%" + bookId + "%'");
                    statement.executeUpdate();
                } else {
                    PreparedStatement statement = connection.prepareStatement("UPDATE book set availability='in' " +
                            "where id = '%" + bookId + "%'");
                    statement.executeUpdate();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(connection);
            }
        }

        public void delete(int id) {
            Connection connection = null;
            try {
                connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("delete from book where id=?");
                statement.setInt(1, id);
                statement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                closeConnection(connection);
            }
        }
}
