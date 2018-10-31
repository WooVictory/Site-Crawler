package app.woovictory.sitecrawler.fragments

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.woovictory.sitecrawler.NewsAdapter
import app.woovictory.sitecrawler.NewsItem
import app.woovictory.sitecrawler.NewsSubscribeActivity
import app.woovictory.sitecrawler.R
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import org.jetbrains.anko.support.v4.toast
import org.jsoup.Jsoup
import java.io.IOException

/**
 * Created by VictoryWoo
 */
class NewsListFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            v->{
                var news_index_url = newsListRv.getChildAdapterPosition(v!!)
                //toast(datas[news_index_url].news_link_url)
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(datas[news_index_url].news_link_url)))
            }
        }
    }

    companion object {
        private const val CATEGORY = "category"

        fun newInstance(sectionCategory: String): NewsListFragment {
            val fragment = NewsListFragment()
            val args = Bundle()
            args.putString(CATEGORY, sectionCategory)
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var newsAdapter : NewsAdapter
    lateinit var datas :  ArrayList<NewsItem>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_news_list, container, false)

        view.newsListRv.setHasFixedSize(true)
        view.newsListRv.layoutManager = LinearLayoutManager(context)

        datas = ArrayList()

        val task = NewsAsyncTask(arguments!!.getString(CATEGORY), context!!)
        task.execute()

        return view
    }

    inner class NewsAsyncTask(private var category : String, private var context : Context) : AsyncTask<Any,Any,Any>(){


        override fun onPreExecute() {
            super.onPreExecute()


        }

        override fun onPostExecute(result: Any?) {
            super.onPostExecute(result)

            newsAdapter = NewsAdapter(datas, context!!)
            newsAdapter.setNewsClick(this@NewsListFragment)
            newsListRv.adapter = newsAdapter
            newsAdapter.notifyDataSetChanged()



        }
        override fun doInBackground(vararg p0: Any?): Any {

            try {
                val doc = Jsoup.connect("https://news.naver.com/").get()
                val elements = doc.select("ul.section_list_ranking")
                for ((i, element) in elements.withIndex()) {
                    if (NewsSubscribeActivity.news_category[i] == category) {
                        for ((j, child) in element.children().withIndex()) {
                            val title = child.select("a").attr("title")
                            val url = "https://news.naver.com/" + child.select("a").attr("href")
                            datas.add(NewsItem(NewsSubscribeActivity.news_category[i], j + 1, title, url))
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return datas
        }


    }
}