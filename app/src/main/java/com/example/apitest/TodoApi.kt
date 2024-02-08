package com.example.apitest

import com.example.apitest.models.TodoData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TodoApi {
    @GET("/todos")
    suspend fun getTodo() : Response<List<TodoData>>

    // suspend to active coroutine and don't run in main thread
    //@Query("key") key : String

//    @POST("/createTodo")
//    fun createTodo(@Body todo :TodoData) :Response<CreateTodoResponse>
}