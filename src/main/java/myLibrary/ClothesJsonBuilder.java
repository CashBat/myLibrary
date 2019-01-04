package myLibrary;

import java.util.Collection;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;


public class ClothesJsonBuilder {
  public static JsonObject getClothesListJson(Collection<MemoryItem> stores) {
    return Json.createObjectBuilder().add("libraryBooks", getClothesListJsonArrayBuilder(stores))
        .build();
  }

  public static JsonArrayBuilder getClothesListJsonArrayBuilder(Collection<MemoryItem> stores) {
    JsonArrayBuilder builder = Json.createArrayBuilder();
    for (MemoryItem cl : stores) {
      builder.add(getClothesJsonObjectBuilder((LibraryBook)cl));
    }

    return builder;
  }

  public static JsonObject getClothesJsonObject(LibraryBook clothes) {
    return getClothesJsonObjectBuilder(clothes).build();
  }

  private static JsonObjectBuilder getClothesJsonObjectBuilder(LibraryBook clothes) {
    JsonObjectBuilder builder = Json.createObjectBuilder().add("id", clothes.getId())
    		  .add("title", clothes.getTitle())
    		  .add("description", clothes.getDescription())
    		  .add("availability", clothes.isAvailability())
        .add("genre", clothes.getGenre() != null ?
            ClothesTypeJsonBuilder.getTypeJsonObject(clothes.getGenre()) : JsonValue.NULL

);

    return builder;
  }
}
