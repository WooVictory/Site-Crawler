package app.woovictory.sitecrawler.fragments

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.woovictory.sitecrawler.RankingAdapter
import app.woovictory.sitecrawler.RankingDatas
import app.woovictory.sitecrawler.R
import kotlinx.android.synthetic.main.fragment_ranking.*
import kotlinx.android.synthetic.main.fragment_ranking.view.*
import org.jetbrains.anko.support.v4.toast
import org.jsoup.Jsoup
import org.jsoup.select.Elements

/**
 * Created by VictoryWoo
 */
class RankingFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            v!!->{
                var position = rankingRv.getChildAdapterPosition(v!!)
                toast(position.toString())

            }
        }
    }

    lateinit var contents: Elements
    lateinit var adapter: RankingAdapter
    lateinit var items : ArrayList<RankingDatas>
    lateinit var rv : RecyclerView

    companion object {
        var htmlUrl: String = "https://www.naver.com/"
        var text : String = " "
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_ranking, container, false)
        items = ArrayList()

        rv = view.rankingRv

        return view
    }

    override fun onResume() {
        super.onResume()
        var start = Start(view!!)
        start.execute()

    }

    inner class Start(views : View) : AsyncTask<Any, Any, Any>() {
        private lateinit var progressDialog : ProgressDialog


        override fun doInBackground(vararg p0: Any?): Any? {
            var document: org.jsoup.nodes.Document
            document = Jsoup.connect(htmlUrl).get()
            contents = document.select("span.ah_k")

            Log.v("victory 22", contents.text())
            Log.v("victory 22", contents.size.toString())

            var cnt: Int = 0
            for (element in contents!!) {
                cnt += 1
                //text = "${cnt}.${contents.text()+"\n"}"
                var rank_count : String
                var rank_content : String
                rank_count = cnt.toString()
                rank_content = element.text()
                items.add(RankingDatas(rank_count, rank_content))
                adapter = RankingAdapter(items, context!!)


                //text += cnt.toString() + "." + element.text() + "\n"
                Log.v("victory cnt 33", cnt.toString())
                //Log.v("victory text 33", text)
                if (cnt == 20) break
            }

            //Log.v("woo 1994", text.toString())
            return text
        }

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(context!!)
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog.setMessage("잠시만 기다려주세요:)")
            progressDialog.setCanceledOnTouchOutside(false)
            progressDialog.show()

        }

        override fun onPostExecute(result: Any?) {
            super.onPostExecute(result)
            //Log.v("woo 1994", text.toString())
            //content.text = text + "\n"
            adapter.setOnItemClickListener(this@RankingFragment)
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
            progressDialog.dismiss()
        }

    }
}