package rep;

import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;

import myLibrary.Genre;
@RequestScoped
public class BookJsonBuilder implements JsonBuilder {
	private Genre genre;

	public GenreJsonBuilder() {	System.out.println("создался GenreJsonBuilder ");  }

	@Override
	public JsonObject getJson() {
		return null;
	}

}
