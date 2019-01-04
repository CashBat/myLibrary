package myLibrary;

import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;


public class ClothesTypeJsonBuilder {
  public static JsonObject getTypesJson(List<Genre> types) {
    JsonArrayBuilder builder = Json.createArrayBuilder();
    for (Genre type : types) {
      builder.add(getTypeJsonObjectBuilder(type));
    }

    return Json.createObjectBuilder().add("genres", builder).build();
  }

  public static JsonObject getTypeJsonObject(Genre type) {
    return getTypeJsonObjectBuilder(type).build();
  }

  private static JsonObjectBuilder getTypeJsonObjectBuilder(Genre type) {
    JsonObjectBuilder builder =
        Json.createObjectBuilder().add("id", type.getId()).add("title", type.getTitle());

    return builder;
  }
}
