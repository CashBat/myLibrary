package myLibrary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/main")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClothesResource {

  @Inject
  private LibraryService clothesService;

  
  @Inject
  private Aftor aftor;

  @GET
  @Path(value = "/books")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getStores() {
	 
    Collection<MemoryItem> stores = clothesService.getBooks();
    return Response.ok(stores).build();
  }
  
 /* @GET
  @Path(value = "/genre")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getGenre() {
    Collection<Genre> genres = clothesService.getGenres();
    JsonObject storeJson = jsonBuilder.getJson(genres);
    System.out.println(stores.size());
    System.out.println(aftor.getMessage());  
    return Response.ok(storeJson).build();
  }*/

  
}
