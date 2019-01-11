package myLibrary.service.specific;

import java.util.Collection;
import myLibrary.model.LibraryEntity;

public interface LibrarySpecification {
	<T> Collection<T> satisfiedBy(Collection<T> libraryEntitys);
}
