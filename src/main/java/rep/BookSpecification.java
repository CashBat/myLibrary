package rep;

import java.util.Collection;
import myLibrary.Genre;
import myLibrary.LibraryBook;

public interface BookSpecification {

	Collection<LibraryBook> satisfiedBy(Collection<LibraryBook> books);
}
