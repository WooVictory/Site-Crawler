package app.woovictory.sitecrawler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import app.woovictory.sitecrawler.fragments.NewsListFragment
import kotlinx.android.synthetic.main.activity_news_subscribe.*

class NewsSubscribeActivity : AppCompatActivity() {

    companion object {
        var news_category  = listOf<String>("정치","경제","사회","생활/문화","세계","IT/과학")

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_subscribe)

        addFragment(NewsListFragment.newInstance(news_category[0]))

        for(item in news_category){
            newsTab.addTab(newsTab.newTab().setText(item))
        }

        newsTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0-> {
                        Log.v("key 930",newsTab.getTabAt(tab!!.position)!!.text.toString())
                        replaceFragment(NewsListFragment.newInstance(newsTab.getTabAt(tab!!.position)!!.text.toString()))
                    }
                    1-> {
                        Log.v("key 930",newsTab.getTabAt(tab!!.position)!!.text.toString())
                        replaceFragment(NewsListFragment.newInstance(newsTab.getTabAt(tab!!.position)!!.text.toString()))
                    }
                    2-> {
                        Log.v("key 930",newsTab.getTabAt(tab!!.position)!!.text.toString())
                        replaceFragment(NewsListFragment.newInstance(newsTab.getTabAt(tab!!.position)!!.text.toString()))
                    }
                    3-> {
                        Log.v("key 930",newsTab.getTabAt(tab!!.position)!!.text.toString())
                        replaceFragment(NewsListFragment.newInstance(newsTab.getTabAt(tab!!.position)!!.text.toString()))
                    }
                    4-> {
                        Log.v("key 930",newsTab.getTabAt(tab!!.position)!!.text.toString())
                        replaceFragment(NewsListFragment.newInstance(newsTab.getTabAt(tab!!.position)!!.text.toString()))
                    }
                    5-> {
                        Log.v("key 930",newsTab.getTabAt(tab!!.position)!!.text.toString())
                        replaceFragment(NewsListFragment.newInstance(newsTab.getTabAt(tab!!.position)!!.text.toString()))
                    }
                }
            }

        })


    }

    fun addFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.news_container, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.news_container, fragment)
        transaction.commit()
    }
}
