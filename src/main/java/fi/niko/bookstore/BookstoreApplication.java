package fi.niko.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.niko.bookstore.domain.Book;
import fi.niko.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookstore(BookRepository repository){
		return (args) -> {
			log.info("Insert few books");
			repository.save(new Book("A Farewell to Arms","Ernest Hemingway",1929,"1232323-21",9.99));
			repository.save(new Book("Animal Farm","George Orwell",1945,"2212343-5",9.99));
		};
	}
	
}
