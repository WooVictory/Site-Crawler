package app.woovictory.sitecrawler.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.woovictory.sitecrawler.NewsSubscribeActivity
import app.woovictory.sitecrawler.R
import kotlinx.android.synthetic.main.fragment_news.view.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by VictoryWoo
 */
class NewsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_news, container, false)
        view.naverImage.setOnClickListener {
            startActivity<NewsSubscribeActivity>()
        }
        return view
    }



}