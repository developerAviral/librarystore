package com.genpact.librarystore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.genpact.librarystore.entity.Book;
import com.genpact.librarystore.entity.Library;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class LibraryStoreIntegrationTest {

	private static final String ISBN_1 = "ISBN_123";
	private static final String PUBLISHER_1 = "Dhananjay";
	private static final String TITLE_1 = "Mahabharat";
	private static final String LIBRARY_NAME = "Delhi Library";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	public void testCreateBookEntry() {
		Book book = new Book();
		Library lib = new Library();

		book.setIsbn(ISBN_1);
		book.setPublisher(PUBLISHER_1);
		book.setTitle(TITLE_1);

		Set<Book> books = new HashSet<>();
		books.add(book);

		lib.setLibraryName(LIBRARY_NAME);
		lib.setBooks(books);

		Set<Library> library = new HashSet<>();
		library.add(lib);
		book.setLibrary(library);

		HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Book> request = new HttpEntity<>(book, httpHeaders);
		ResponseEntity<String> result = this.restTemplate.postForEntity("/book", request, String.class);
		assertEquals(201, result.getStatusCodeValue());
	}

	@Test
	@Order(2)
	public void testGetAllBooks() {
		Book[] books = this.restTemplate.getForObject("/library/1/books", Book[].class);

		assertEquals(1, books.length);
	}
}
