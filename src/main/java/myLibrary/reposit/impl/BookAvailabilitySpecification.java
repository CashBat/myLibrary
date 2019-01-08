package myLibrary.reposit.impl;

import java.util.Collection;

import myLibrary.model.Book;
import myLibrary.reposit.interfaces.BookSpecification;

public class BookAvailabilitySpecification implements BookSpecification {

	@Override
	public Collection<Book> satisfiedBy(Collection<Book> books) {		

		for (Book book: books) {
			
			
		}
		return null;
		
	}

}
