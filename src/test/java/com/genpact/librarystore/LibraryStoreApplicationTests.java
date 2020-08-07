package com.genpact.librarystore;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.genpact.librarystore.entity.Book;
import com.genpact.librarystore.entity.Library;
import com.genpact.librarystore.service.LibraryService;

@SpringBootTest
class LibraryStoreApplicationTests {
	
	private static final String ISBN_1 = "ISBN_123";
	private static final String ISBN_2 ="ISBN_345";
	private static final String PUBLISHER_1 = "Dhananjay";
	private static final String PUBLISHER_2 = "test_publisher";
	private static final String TITLE_1 = "Mahabharat";
	private static final String TITLE_2 = "test_title";
	private static final String LIBRARY_NAME= "Delhi Library";
	private Book book1;
	private Book book2;
	private Library lib1;

	@Autowired
	private LibraryService libraryService;
	
	@BeforeTestClass
	public void init() {
		book1 = new Book();
		book2 = new Book();
		lib1 = new Library();
		
		book1.setIsbn(ISBN_1);
		book1.setPublisher(PUBLISHER_1);
		book1.setTitle(TITLE_1);
		
		book2.setIsbn(ISBN_2);
		book2.setPublisher(PUBLISHER_2);
		book2.setTitle(TITLE_2);
		
		Set<Book> books = new HashSet<>();
		books.add(book1);
		books.add(book2);
				
		lib1.setLibraryName(LIBRARY_NAME);
		lib1.setBooks(books);
		
		Set<Library> library = new HashSet<>();
		library.add(lib1);
		
		book1.setLibrary(library);
		book2.setLibrary(library);
		
		libraryService.createBookEntry(book1);
		libraryService.createBookEntry(book2);
	}
	
	@Test
	public void testBookDetails(){
		Set<Book> books = libraryService.getAllBooksFromLibrary(1L);		
		assertNotNull(books);
	}

	@Test
	public void testUpdateBook(){
		Book newBook = new Book();
		Book book = libraryService.updateBook(newBook, 1L);
		assertNotNull(book);		
	}
}
