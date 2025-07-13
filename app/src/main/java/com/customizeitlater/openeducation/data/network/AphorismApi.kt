package com.customizeitlater.openeducation.data.network

import com.customizeitlater.openeducation.data.network.requestmodel.SaveAphorismRequest
import com.customizeitlater.openeducation.data.network.responsemodel.AphorismApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AphorismApi {

    // 1️⃣ Get paginated aphorisms (feed)
    @GET("/api/aphorisms")
    suspend fun getAllAphorisms(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 33
    ): Response<AphorismApiResponse>

    // 2️⃣ Search aphorisms by title
    @GET("/api/aphorisms/search")
    suspend fun searchAphorismsByTitle(
        @Query("keyword") keyword: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 33
    ): Response<AphorismApiResponse>

    // 3️⃣ Filter by a single tag
    @GET("/api/aphorisms/tag")
    suspend fun getAphorismsByTag(
        @Query("tagName") tagName: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 33
    ): Response<AphorismApiResponse>

    // 4️⃣ Filter by multiple tags
    @GET("/api/aphorisms/tags")
    suspend fun getAphorismsByTags(
        @Query("tags") tags: Set<String>,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 33
    ): Response<AphorismApiResponse>

    // 5️⃣ Save new aphorism
    @POST("/api/aphorisms")
    suspend fun createAphorism(
        @Body request: SaveAphorismRequest
    ): Response<AphorismApiResponse>
}