package myLibrary.service.specification.interfase;

import java.util.Collection;

import myLibrary.entity.Book;

public interface BookSpecification {

	public Collection<Book> satisfiedBy(Collection<Book> books);
}
