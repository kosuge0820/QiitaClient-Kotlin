package com.example.k_satoshi.qiitaclient.ui.activities

/**
 * Created by k-satoshi on 2017/06/02.
 */

class ArticleActivity: android.support.v7.app.AppCompatActivity() {
    companion object {
        private const val ARTICLE_EXTRA: String = "article"

        fun intent(context: android.content.Context, article: com.example.k_satoshi.qiitaclient.models.Article): android.content.Intent =
                android.content.Intent(context, ArticleActivity::class.java).putExtra(com.example.k_satoshi.qiitaclient.ui.activities.ArticleActivity.Companion.ARTICLE_EXTRA, article)
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.k_satoshi.qiitaclient.R.layout.activity_article)

        val articleView = findViewById(com.example.k_satoshi.qiitaclient.R.id.article_view) as com.example.k_satoshi.qiitaclient.ui.views.ArticleView
        val webView = findViewById(com.example.k_satoshi.qiitaclient.R.id.web_view) as android.webkit.WebView

        val article: com.example.k_satoshi.qiitaclient.models.Article = intent.getParcelableExtra(com.example.k_satoshi.qiitaclient.ui.activities.ArticleActivity.Companion.ARTICLE_EXTRA)
        articleView.setArticle(article)
        webView.loadUrl(article.url)
    }
}