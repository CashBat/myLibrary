package myLibrary.service.specification.interfase;

import java.util.Collection;

public interface LibrarySpecification {
	<T> Collection<T> satisfiedBy(Collection<T> libraryEntitys);
}
