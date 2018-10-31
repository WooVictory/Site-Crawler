package app.woovictory.sitecrawler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by VictoryWoo
 */
class NewsAdapter(var news_item : ArrayList<NewsItem>, private var context : Context)
    : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){


    private lateinit var onNewsClick : View.OnClickListener

    fun setNewsClick(l : View.OnClickListener){
        onNewsClick = l
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent, false)
        view.setOnClickListener(onNewsClick)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = news_item.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.news_title.text = news_item[position].news_title
        holder.news_rank.text = news_item[position].news_rank.toString()+"."
    }

    inner class NewsViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView){
        var news_rank :TextView = itemView.findViewById(R.id.ranking)
        var news_title : TextView = itemView.findViewById(R.id.content)
    }
}