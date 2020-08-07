package com.genpact.librarystore.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.librarystore.entity.Book;
import com.genpact.librarystore.entity.Library;
import com.genpact.librarystore.repository.BookRepository;
import com.genpact.librarystore.repository.LibraryRepository;

@Service
public class LibraryServiceImpl implements LibraryService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	public Book createBookEntry(Book book) {
		return bookRepository.save(book);
	}

	public Set<Book> getAllBooksFromLibrary(Long libraryId) {
		Optional<Library> libraryDetails = libraryRepository.findById(libraryId);
		return libraryDetails.get().getBooks();
	}

	public Book updateBook(Book book, long libraryId) {
		Optional<Library> library = libraryRepository.findById(libraryId);
		if(library.isPresent())
			book.getLibrary().add(library.get());
		
		return bookRepository.save(book);
		
	}

}
