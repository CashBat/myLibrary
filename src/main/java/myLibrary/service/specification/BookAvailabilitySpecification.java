package myLibrary.service.specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import myLibrary.entity.Book;
import myLibrary.rest.exception.TypeMismatchSpecificationException;
import myLibrary.service.specification.interfase.LibrarySpecification;

public class BookAvailabilitySpecification implements LibrarySpecification {

	@Override
	public <T> Collection<T> satisfiedBy(Collection<T> libraryEntities) {
		List<T> booksAvailability = new ArrayList<T>();

		for (T entity : libraryEntities) {
			if (entity instanceof Book) {
				if (((Book) entity).isAvailability())
					booksAvailability.add(entity);
			}

			else
				throw new TypeMismatchSpecificationException(getClass().getSimpleName(),Book.class.getSimpleName(), entity.getClass().getSimpleName());
		}
		return booksAvailability;
	}

}
