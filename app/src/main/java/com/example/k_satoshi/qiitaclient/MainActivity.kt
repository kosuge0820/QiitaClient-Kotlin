package com.example.k_satoshi.qiitaclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.k_satoshi.qiitaclient.model.Article
import com.example.k_satoshi.qiitaclient.model.User
import com.example.k_satoshi.qiitaclient.view.ArticleView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ArticleViewオブジェクトの生成
        val articleView = ArticleView(applicationContext)

        //Articleオブジェクトを生成して、ArticleViewオブジェクトにセット
        articleView.setArticle(
                Article(
                        id = "123",
                        title = "Kotlin入門",
                        url = "http://www.example.com/articles/123",
                        user = User(id = "456", name = "太郎", profileImageUrl = "")
                )
        )

        //このアクティビティにArticleViewオブジェクトをセット
        setContentView(articleView)
    }
}
