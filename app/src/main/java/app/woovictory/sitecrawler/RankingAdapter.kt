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
class RankingAdapter(var list : ArrayList<RankingDatas>, private val context : Context) : RecyclerView.Adapter<RankingAdapter.RankViewHolder>() {

    private lateinit var onItemClickListener : View.OnClickListener

    fun setOnItemClickListener(l : View.OnClickListener){
        onItemClickListener = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RankViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        view.setOnClickListener(onItemClickListener)
        return RankViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RankViewHolder, position: Int) {
        holder.rank.text = list[position].rank_title
        holder.content.text = list[position].contents

    }


    inner class RankViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var rank : TextView = itemView.findViewById(R.id.ranking)
        var content : TextView = itemView.findViewById(R.id.content)
    }
}