package com.example.k_satoshi.qiitaclient.ui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.k_satoshi.qiitaclient.models.Article
import com.example.k_satoshi.qiitaclient.ui.views.ArticleView

/**
 * Created by k-satoshi on 2017/06/01.
 */

class ArticleListAdapter(private val context: Context): BaseAdapter() {
    var articles: List<Article> = emptyList()

    override fun getCount(): Int = articles.size

    override fun getItem(position: Int): Any? = articles[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int,
                         convertView: View?,
                         parent: ViewGroup?): View = convertView as? ArticleView ?: ArticleView(context).apply {
        setArticle(articles[position])
    }
}