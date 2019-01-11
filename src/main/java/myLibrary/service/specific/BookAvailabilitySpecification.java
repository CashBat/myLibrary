package myLibrary.service.specific;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import myLibrary.model.Book;
import myLibrary.model.LibraryEntity;

public class BookAvailabilitySpecification implements LibrarySpecification {

	@Override
	public <T> Collection<T> satisfiedBy(Collection<T> libraryEntitys) {
		List<T> booksAvailability = new ArrayList<T>();

		for (T entity : libraryEntitys) {

			if (entity instanceof Book) {
				Book t = new Book();
				Book f = (Book) t;
				System.out.println(f);

				if (((Book) entity).isAvailability())
					booksAvailability.add(entity);
			}

			else
				throw new RuntimeException("Тип репозитория и тип спецификации не совпадает");// если без этого то получит пустой список и даже не поймет в чем дело, будет копать весь код
				System.out.println("Неверный тип"); // унаследовать исключение от рунтаймегзепшен(создать свое типо неверный тип спецификации) и выкидывать его тут, чтобы исправляли разрабы, возможно производится откат транзакции(нет не нужно, обычное извленение данных, операции особо не связаны)
		}
		return booksAvailability;
	}



}
