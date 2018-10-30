package app.woovictory.sitecrawler

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.select.Elements
import org.jsoup.Jsoup


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            reloadBtn -> {
                val start = Start()
                start.execute()
            }
        }

    }


    lateinit var contents: Elements
    lateinit var adapter: Adapter
    lateinit var items : ArrayList<Datas>

    companion object {
        var htmlUrl: String = "https://www.naver.com/"
        var text : String = " "
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reloadBtn.setOnClickListener(this)
        items = ArrayList()


    }

    inner class Start : AsyncTask<Any, Any, Any>() {
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
                items.add(Datas(rank_count, rank_content))
                adapter = Adapter(items)


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

        }

        override fun onPostExecute(result: Any?) {
            super.onPostExecute(result)
            //Log.v("woo 1994", text.toString())
            //content.text = text + "\n"
            mainRv.adapter = adapter
            mainRv.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter.notifyDataSetChanged()
        }

    }


}


