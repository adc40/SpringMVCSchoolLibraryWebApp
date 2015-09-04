package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    BookDao bookDao = new BookDao();
    User user = new User();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToLogIn(){
        user.setUsername("");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @RequestParam("username") String username,
                        @RequestParam("password") String password){
        if(bookDao.tableExists(username.trim()) && bookDao.passwordCheck(username.trim(), password.trim())) {
            user.setUsername(username.trim());
            return "hello";
        }
        model.addAttribute("message", "Username/password incorrect");
        return "login";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String getCreateUser() {
        return "createUser";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(Model model, @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("confirmPassword") String confirmPassword){
        if (bookDao.tableExists(username)) {
            model.addAttribute("message", "Username already exists");
            return "createUser";
        }
        if (!password.equals(confirmPassword)) {
            model.addAttribute("message", "Password entries did not match");
            return "createUser";
        }
        bookDao.createUser(username, password);
        bookDao.createBooksTable(username);
        user.setUsername(username);
        user.setPassword(password);
        model.addAttribute("message", "Account created!");
        return "hello";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchBooks(Model model, @RequestParam("keyword") String keyword) {
        model.addAttribute("books", bookDao.searchBooks(user.getUsername(), keyword));
        return "bookList";
    }

    @RequestMapping(value = "/advancedSearch", method = RequestMethod.GET)
    public String getForm(){
        return "advancedSearch";
    }

    @RequestMapping(value = "/advancedSearch", method = RequestMethod.POST)
    public String searchForm(Model model, @RequestParam("title") String title,
                             @RequestParam("author") String author,
                             @RequestParam("color") String color,
                             @RequestParam("level") String level) {
        model.addAttribute("books", bookDao.advancedSearchBooks(user.getUsername(),title,author,color,level));
        return "bookList";
    }

    @RequestMapping(value = "/allBooks", method = RequestMethod.GET)
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDao.allBooks(user.getUsername()));
        return "bookList";
    }

    @RequestMapping(value = "/allBooksOrdered", method = RequestMethod.GET)
    public String getOrderedBooks(Model model, @RequestParam("keyword") String keyword) {
        model.addAttribute("books", bookDao.allBooks(user.getUsername(), keyword));
        return "bookList";
    }

    @RequestMapping("/book")
    public String getBook(Model model, @RequestParam("id") int id){
        model.addAttribute("book", bookDao.getBook(user.getUsername(), id));
        return "book";
    }

    @RequestMapping(value = "/availability", method = RequestMethod.POST)
    public String changeAvail(Model model, @RequestParam("id") int id){
        bookDao.changeAvail(user.getUsername(), id);
        Book book = bookDao.getBook(user.getUsername(), id);
        model.addAttribute("book", book);
        return "book";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addForm(){
        return "addBook";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBook(@RequestParam("title") String title,
                        @RequestParam("author") String author,
                        @RequestParam("category") String category,
                        @RequestParam("year") int year,
                        @RequestParam("color") String color,
                        @RequestParam("level") int level,
                        @RequestParam("availability") String availability){
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setYear(year);
        book.setColor(color);
        book.setLevel(level);
        book.setAvailability(availability);
        bookDao.addBook(user.getUsername(),book);
        return "addAgain";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(){
        return "remove";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id){
        bookDao.delete(user.getUsername(), id);
        return "/bookDeleted";
    }

}