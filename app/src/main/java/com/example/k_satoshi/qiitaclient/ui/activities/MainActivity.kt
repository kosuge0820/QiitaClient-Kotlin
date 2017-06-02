package com.example.k_satoshi.qiitaclient.ui.activities
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import android.widget.ProgressBar
import com.example.k_satoshi.qiitaclient.R
import com.example.k_satoshi.qiitaclient.extensions.toast
import com.example.k_satoshi.qiitaclient.ui.adapters.ArticleListAdapter
import com.example.k_satoshi.qiitaclient.models.Article
import com.example.k_satoshi.qiitaclient.models.User
import com.example.k_satoshi.qiitaclient.services.ArticleClient
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import rx.android.schedulers.AndroidSchedulers


class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val queryEditText = findViewById(R.id.query_edit_text) as EditText
        val progressBar = findViewById(R.id.progress_bar) as ProgressBar
        val searchButton = findViewById(R.id.search_button) as Button
        val listView: ListView = findViewById(R.id.list_view) as ListView
        val listAdapter = ArticleListAdapter(applicationContext)

        listView.adapter = listAdapter
        listView.setOnItemClickListener { _, _, position, _ ->
            startActivity(ArticleActivity.Companion.intent(this, listAdapter.articles[position]))
        }

        val gsonBuilder = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        val articleClient = retrofit.create(ArticleClient::class.java)

        searchButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(listView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            listView.clearFocus()

            articleClient.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate { progressBar.visibility = View.GONE }
                    .bindToLifecycle(this)
                    .subscribe({
                        queryEditText.text.clear()
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                    }, {
                        toast("エラー: $it")
                    })
        }
    }
}