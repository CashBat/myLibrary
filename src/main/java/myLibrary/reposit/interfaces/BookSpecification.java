package myLibrary.reposit.interfaces;

import java.util.Collection;

import myLibrary.model.Book;

public interface BookSpecification {

	Collection<Book> satisfiedBy(Collection<Book> books);
}
