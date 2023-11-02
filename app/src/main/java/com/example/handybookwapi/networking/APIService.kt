package com.example.handybookwapi.networking

import com.example.handybookwapi.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface APIService {
    @GET("/book-api")
    fun getBooks():Call<List<Book>>

    @GET("/book-api/all-category")
    fun getCategories():Call<List<Category>>

    @GET("/book-api/view")
    fun getBook(@Query("id") id:Int):Call<Book>

    @GET("/book-api/main-book")
    fun getMainBook():Call<Book>
    @GET("/book-api/category")
    fun getBookByCategory(@Query("name") name:String):Call<List<Book>>

    @GET("/book-api/comment")
    fun getComments(@Query("id") id: Int):Call<List<CommentResponse>>

    @POST("/comment-api/create")
    fun addComment(@Body addComment: AddComment): Call<AddCommentResponse>

    @GET("/book-api/category")
    fun getBooksByCategory(@Query("name") categoryName: String):Call<List<Book>>
    @GET("/book-api/search-name")
    fun search(@Query("name") name: String):Call<List<Book>>

    @POST("/book-api/register")
    fun signup(@Body signUp: SignUp): Call<User>

    @POST("/book-api/login")
    fun login(@Body signIn: SignIn): Call<User>


}