package com.genpact.librarystore.controller;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.genpact.librarystore.entity.Book;
import com.genpact.librarystore.service.LibraryService;

@RestController
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	
	@PostMapping("/book")
	public ResponseEntity<Void> createBookEntry(@RequestBody Book book) {
		Book bookCreated = libraryService.createBookEntry(book);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bookCreated.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/library/{id}/books")
	public Set<Book> getAllBooks(@PathVariable Long id){
		return libraryService.getAllBooksFromLibrary(id);
	}
	
	@PutMapping("/library/{id}/book")
	public Book updateBook(@RequestBody Book book,@PathVariable Long id) {
		return libraryService.updateBook(book, id);
	}
}
