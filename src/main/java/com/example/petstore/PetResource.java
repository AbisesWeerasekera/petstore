package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

// 	@APIResponses(value = {
// 			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
// 	@GET
// 	public Response getPets() {
// 		List<Pet> pets = new ArrayList<Pet>();
// 		Pet pet1 = new Pet();
// 		pet1.setPetId(1);
// 		pet1.setPetAge(3);
// 		pet1.setPetName("Boola");
// 		pet1.setPetType("Dog");

// 		Pet pet2 = new Pet();
// 		pet2.setPetId(2);
// 		pet2.setPetAge(4);
// 		pet2.setPetName("Sudda");
// 		pet2.setPetType("Cat");

// 		Pet pet3 = new Pet();
// 		pet3.setPetId(3);
// 		pet3.setPetAge(2);
// 		pet3.setPetName("Peththappu");
// 		pet3.setPetType("Bird");

// 		pets.add(pet1);
// 		pets.add(pet2);
// 		pets.add(pet3);
// 		return Response.ok(pets).build();
// 	}

// 	@APIResponses(value = {
// 			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
// 			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
// 	@GET
// 	@Path("{petId}")
// 	public Response getPet(@PathParam("petId") int petId) {
// 		if (petId < 0) {
// 			return Response.status(Status.NOT_FOUND).build();
// 		}
// 		Pet pet = new Pet();
// 		pet.setPetId(petId);
// 		pet.setPetAge(3);
// 		pet.setPetName("Buula");
// 		pet.setPetType("Dog");

// 		return Response.ok(pet).build();
		
// 	}
	
	
	List<Pet> pets = new ArrayList<Pet>();
	List<PetType> petTypes = new ArrayList<PetType>();


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet")))})
	@GET
	@Path("/")
	public Response getPets() {
		return Response.ok(pets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Delete one Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@DELETE
	@Path("/{petId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePets(@PathParam("petId") int petId) {
		if (petId < 0 || pets.get(petId).getPetName() == "undefined") {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = null;
		for(int k = 0; k < pets.size() ; k++) {
			if(pets.get(k).getPetId()==petId) {
				pet = pets.get(k);
				pets.remove(k);
			}
		}

		return Response.ok(pet).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "202", description = "Add a Pet Type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
	@POST
	@Path("/petTypes/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPetType(PetType petType) {
		int index = this.petTypes.size();
		int lastid = 0;
		if(index == 0) {
			lastid = 1;
		}else {
			lastid = petTypes.get(index-1).getPetTypeId()+1;
		}
		PetType pet1 = petType;
		pet1.setPetTypeId(lastid);
		this.petTypes.add(pet1);
		return Response.ok(pet1).build();
	}
	
	
	
	
	
	
}
