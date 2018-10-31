package app.woovictory.sitecrawler.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.woovictory.sitecrawler.movie.MovieAdapter
import app.woovictory.sitecrawler.movie.MovieDatas
import app.woovictory.sitecrawler.R
import kotlinx.android.synthetic.main.fragment_movie.view.*
import org.jsoup.Jsoup
import java.lang.Exception

/**
 * Created by VictoryWoo
 */
class MovieFragment : Fragment(),View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            v->{
                var item_position = movieRv.getChildAdapterPosition(v!!)
                var data = "https://movie.naver.com/"+movie_list[item_position].movieDetailLink
                Log.v("click 시 link : ",movie_list[item_position].movieDetailLink)
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data!!)))
            }
        }
    }

    lateinit var movieAdapter : MovieAdapter
    lateinit var movie_list : ArrayList<MovieDatas>
    lateinit var movieRv : RecyclerView
    var htmlMovieUrl : String = "https://movie.naver.com/movie/running/current.nhn"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_movie, container, false)
        movieRv = view.movieRv
        movie_list = ArrayList()
        var movieAsyncTask = MovieAsyncTask()
        movieAsyncTask.execute()
        return view
    }

    inner class MovieAsyncTask : AsyncTask<Any,Any, Any>(){

        private lateinit var progressDialog : ProgressDialog


        override fun onPreExecute() {
            super.onPreExecute()

            progressDialog = ProgressDialog(context!!)
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog.setMessage("잠시만 기다려주세요:)")
            progressDialog.setCanceledOnTouchOutside(false)
            progressDialog.show()
        }

        override fun onPostExecute(result: Any?) {

            movieAdapter = MovieAdapter(movie_list, context!!)
            movieAdapter.setOnItemClick(this@MovieFragment)
            movieRv.adapter = movieAdapter
            movieRv.layoutManager = LinearLayoutManager(context!!)
            movieAdapter.notifyDataSetChanged()
            progressDialog.dismiss()

        }

        override fun doInBackground(vararg p0: Any?): Any? {
            try {
                var doc = Jsoup.connect(htmlMovieUrl).get()
                var element = doc.select("ul[class=lst_detail_t1]").select("li")
                var elementSize = element.size
                var cnt : Int = 0
                for(elements in element!!){
                    cnt++
                    var movieTitle : String = cnt.toString()+". "+ elements.select("li dt[class=tit] a").text()
                    var movieLink : String = elements.select("li div[class=thumb] a").attr("href")
                    var movieImg : String = elements.select("li div[class=thumb] a img").attr("src")

                    var rElem = elements.select("dl[class=info_txt1] dt").next().first()
                    var movieRelease = rElem.select("dd").text();
                    var dElem = elements.select("dt[class=tit_t2]").next().first()
                    var movieDirector = "감독: " + dElem.select("a").text();


                    movie_list.add(
                        MovieDatas(
                            movieTitle,
                            movieImg,
                            movieLink,
                            movieRelease,
                            movieDirector
                        )
                    )

                    Log.v("woo 807","size : ${element.size}")
                    Log.v("woo 807 movie link : "," ${movieLink}")



                }
            }catch (e : Exception){

            }
            return null
        }

    }
}