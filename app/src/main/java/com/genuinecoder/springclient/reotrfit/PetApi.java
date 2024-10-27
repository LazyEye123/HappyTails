package com.genuinecoder.springclient.reotrfit;

import com.genuinecoder.springclient.pets_page_activity.dto.PetDTO;
import com.genuinecoder.springclient.pets_page_activity.model.PetCard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PetApi {
    @POST("/api/v1.0/pet")
    public Call<PetDTO> addPet(@Body PetDTO petDTO);

    @PUT("/api/v1.0/pet")
    public Call<PetCard> updatePet(@Body PetDTO petDTO);

    @DELETE("/api/v1.0/pet")
    public Call<PetCard> deletePet(@Body PetDTO petDTO);

    @GET("/api/v1.0/pet")
    public Call<List<PetDTO>> getAllPets();
}
