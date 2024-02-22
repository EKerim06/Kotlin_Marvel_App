package com.example.marvel_app.charlist

import ComicResult
import com.example.marvel_app.models.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("characters")
    suspend fun fetchCharacters(
        @Query("apikey") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<CharacterResponse>


    @GET("characters/{characterId}/comics")
    suspend fun fetchComics(
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String
    ): Response<ComicResult>
}

