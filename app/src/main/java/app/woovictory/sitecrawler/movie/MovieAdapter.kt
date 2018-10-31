package app.woovictory.sitecrawler.movie

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.woovictory.sitecrawler.R
import com.bumptech.glide.Glide

/**
 * Created by VictoryWoo
 */
class MovieAdapter(var item_list : ArrayList<MovieDatas>, private var context : Context)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private lateinit var onItemClick : View.OnClickListener

    fun setOnItemClick(l : View.OnClickListener){
        onItemClick = l
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.movie_item, parent, false)
        view.setOnClickListener(onItemClick)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int  = item_list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movie_title.text = item_list[position].movieTitle
        holder.movie_director.text = item_list[position].movieDirector
        holder.movie_release.text = item_list[position].movieRelease
        Glide.with(context).load(item_list[position].movieImage).into(holder.movie_image)
    }

    inner class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var movie_title : TextView = itemView.findViewById(R.id.mTitle)
        var movie_image : ImageView = itemView.findViewById(R.id.mImage)
        var movie_release : TextView = itemView.findViewById(R.id.mRelease)
        var movie_director : TextView = itemView.findViewById(R.id.mDirector)

    }

}