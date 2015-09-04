package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Abram on 9/1/2015.
 */
/*
Login necessary
Username required for bookDao methods
 */
@Controller
public class KidsBookController {
    BookDao bookDao = new BookDao();
    User user = new User();

    @RequestMapping(value = "/kidsHello", method = RequestMethod.GET)
    public String kidsHello(){
        return "kidsHello";
    }

    @RequestMapping(value = "/kidsAllBooks", method = RequestMethod.GET)
    public String getAllBooks(Model model, @RequestParam("username") String username) {
        model.addAttribute("books", bookDao.allBooks(username));
        return "kidsBookList";
    }

    @RequestMapping(value = "/kidsAdvancedSearch", method = RequestMethod.POST)
    public String searchForm(Model model, @RequestParam("title") String title,
                             @RequestParam("author") String author,
                             @RequestParam("color") String color,
                             @RequestParam("level") String level) {
        model.addAttribute("books", bookDao.advancedSearchBooks(user.getUsername(), title, author, color, level));
        return "kidsBookList";
    }

    @RequestMapping(value = "/kidsSearch", method = RequestMethod.GET)
    public String searchBooks(Model model, @RequestParam("keyword") String keyword) {
        model.addAttribute("books", bookDao.searchBooks(user.getUsername(), keyword));
        return "kidsBookList";
    }

    @RequestMapping(value = "/kidsAdd", method = RequestMethod.GET)
    public String addForm(){
        return "kidsAddBook";
    }

    @RequestMapping(value = "/kidsAdd", method = RequestMethod.POST)
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
        bookDao.addBook(user.getUsername(), book);
        return "kidsAddAgain";
    }

    @RequestMapping(value = "/kidsDelete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id){
        bookDao.delete(user.getUsername(), id);
        return "/kidsBookDeleted";
    }
}
