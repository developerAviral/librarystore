package com.genpact.librarystore.service;

import java.util.Set;

import com.genpact.librarystore.entity.Book;

public interface LibraryService {

	public Book createBookEntry(Book book);
	
	public Set<Book> getAllBooksFromLibrary(Long libraryId);
	
	public Book updateBook(Book book, long libraryID);
}
