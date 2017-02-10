package fi.niko.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.niko.bookstore.domain.BookRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	@RequestMapping(value="/booklist", method=RequestMethod.GET)
	public String books(Model model){
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
}
