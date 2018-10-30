package app.woovictory.sitecrawler

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.select.Elements
import org.w3c.dom.Document
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
    var text: String = ""

    companion object {
        var htmlUrl: String = "https://www.naver.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reloadBtn.setOnClickListener(this)


    }

    inner class Start : AsyncTask<Any, Any, Any>() {
        override fun doInBackground(vararg p0: Any?): Any? {
            var document: org.jsoup.nodes.Document
            document = Jsoup.connect(htmlUrl).get()
            contents = document.select("span.ah_k")
            //Log.v("woo 1994 1", text.toString())
            //Log.v("woo 1994 2", text.toString())
            Log.v("victory 22", contents.text())
            Log.v("victory 22", contents.size.toString())

            var cnt: Int = 0
            for (element in contents!!) {
                cnt += 1
                //text = "${cnt}.${contents.text()+"\n"}"
                text = cnt.toString() + "." + element.text() + "\n"
                Log.v("victory 33", cnt.toString())
                if (cnt == 10) break
            }

            Log.v("woo 1994", text.toString())
            return null
        }

        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun onPostExecute(result: Any?) {
            super.onPostExecute(result)
            Log.v("woo 1994", text.toString())
            content.text = text + "\n"
        }

    }


}


