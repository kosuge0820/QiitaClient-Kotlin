package com.example.k_satoshi.qiitaclient.services

import com.example.k_satoshi.qiitaclient.models.Article
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

//import java.util.*

/**
 * Created by k-satoshi on 2017/06/02.
 */

interface ArticleClient {
    @GET("/api/v2/items")
    fun search(@Query("query") query: String): Observable<List<Article>>
}