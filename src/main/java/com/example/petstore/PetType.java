package com.example.petstore;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(name = "PetType")
public class PetType {
	
	@Schema(required = true, description = "Pet type id")
	@JsonProperty("pet_type_id")
	private Integer petTypeId;

	@Schema(required = true, description = "Pet type")
	@JsonProperty("pet_type")
	private String petType;
	
	public Integer getPetTypeId() {
		return petTypeId;
	}

	public void setPetTypeId(Integer petTypeId) {
		this.petTypeId = petTypeId;
	}
	
	
}
