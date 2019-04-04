package myLibrary.service.specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import myLibrary.reposit.model.Book;
import myLibrary.rest.exception.TypeMismatchSpecificationException;
import myLibrary.service.specification.interfase.LibrarySpecification;

public class BookAvailabilitySpecification implements LibrarySpecification {
	private boolean availabilityStatus;
	
	public BookAvailabilitySpecification(boolean availabilityStatus) {
		this.availabilityStatus =availabilityStatus;
	}

	@Override
	public <T> Collection<T> satisfiedBy(Collection<T> libraryEntities) {
		List<T> booksFilteredAvailability = new ArrayList<T>();

		for (T entity : libraryEntities) {
			if (entity instanceof Book) {
				if (((Book) entity).isAvailability()==availabilityStatus)
					booksFilteredAvailability.add(entity);
			}

			else
				throw new TypeMismatchSpecificationException(getClass().getSimpleName(),Book.class.getSimpleName(), entity.getClass().getSimpleName());
		}
		return booksFilteredAvailability;
	}

	

}
