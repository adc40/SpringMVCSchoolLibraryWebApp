package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    BookDao bookDao = new BookDao();

    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "hello";
	}

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchBooks(Model model, @RequestParam("keyword") String keyword) {
        model.addAttribute("books", bookDao.searchBooks(keyword));
        return "searchResults";
    }

    @RequestMapping(value = "/advancedSearch", method = RequestMethod.GET)
    public String getForm(){
        return "advancedSearch";
    }

    @RequestMapping(value = "/advancedSearch", method = RequestMethod.POST)
    public String searchForm(Model model, @RequestParam("title") String title,
                             @RequestParam("author") String author,
                             @RequestParam("color") String color,
                             @RequestParam("level") int level) {
        model.addAttribute("books", bookDao.advancedSearchBooks(title,author,color,level));
        return "searchResults";
    }

    @RequestMapping(value = "/allBooks", method = RequestMethod.GET)
    public String getAllBooks(Model model) {
        model.addAttribute("allBooks", bookDao.allBooks());
        return "allBooks";
    }

    @RequestMapping("/book")
    public String getBook(Model model, @RequestParam("id") int id){
        model.addAttribute("book", bookDao.getBook(id));
        return "book";
    }

    @RequestMapping(value = "/availability", method = RequestMethod.POST)
    public String changeAvail(Model model, @RequestParam("id") int id){
        bookDao.changeAvail(id);
        Book book = bookDao.getBook(id);
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
        bookDao.addBook(book);
        return "addAgain";
    }

    @RequestMapping("/addAgain")
    public String bookAdded(){
        return "addAgain";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id){
        bookDao.delete(id);
        return "/bookDeleted";
    }

}