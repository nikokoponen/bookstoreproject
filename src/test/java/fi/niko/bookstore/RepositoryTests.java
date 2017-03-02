package fi.niko.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.niko.bookstore.domain.Book;
import fi.niko.bookstore.domain.BookRepository;
import fi.niko.bookstore.domain.Category;
import fi.niko.bookstore.domain.CategoryRepository;
import fi.niko.bookstore.domain.User;
import fi.niko.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {
	
	@Autowired
	private BookRepository brepository;
	@Autowired
	private CategoryRepository crepository;
	@Autowired
	private UserRepository urepository;
	
	//BookRepository tests. Search
	@Test
	public void findByTitleShouldReturnBook() {
        List<Book> books = brepository.findByTitle("Animal Farm");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getIsbn()).isEqualTo("2212343-5");
    }
	//BookRepository tests. Create
	@Test
	public void createNewBook(){
		
		Book book = new Book("My Biography","Dave Mustaine",1929,"12355323-21",9.99,crepository.findByName("Biography").get(0));
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	//BookRepository tests. Delete
	@Test
	public void deleteBook(){
		
		List<Book> books = brepository.findByTitle("A Farewell to Arms");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getIsbn()).isEqualTo("1232323-21");
		brepository.delete(books);
	}
	
	//CategoryRepository tests. Search
	@Test
	public void findByNameShouldReturnCategory() {
		List<Category> categories = crepository.findByName("Horror");
		assertThat(categories).hasSize(1);
	    }
	
	//CategoryRepository tests. Create
	@Test
	public void createNewCategory(){
		Category category = new Category("Journal");
		crepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
		}
	
	//CategoryRepository tests. Delete
	@Test
	public void deleteCategory(){
		List<Category> categories = crepository.findByName("Fiction");
		assertThat(categories).hasSize(1);
		crepository.delete(categories);
		}
	
	//UserRepository tests. Search
	@Test
	public void findByUsernameShouldReturnUsername() {
		User thisuser = urepository.findByUsername("user");
		assertThat(thisuser.getId()).isNotNull();
		}
		
	//UserRepository tests. Create
	@Test
	public void createNewUser(){
		User user = new User("nikok","nikokkikoo","user");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
		assertThat(user.getPasswordHash()).isNotNull();
		assertThat(user.getRole()).isNotNull();
		}
		
	//UserRepository tests. Delete
	@Test
	public void deleteUser(){
		User user = urepository.findByUsername("admin");
		assertThat(user.getId()).isNotNull();
		urepository.delete(user);
		}
	}

