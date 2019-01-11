package myLibrary.service.specific;

import java.util.Collection;

import myLibrary.model.Book;

public interface BookSpecification  {
	
 public	Collection<Book> satisfiedBy(Collection<Book> books);
}
