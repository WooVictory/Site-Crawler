package app.woovictory.sitecrawler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by VictoryWoo
 */
class Adapter(var list : ArrayList<Datas>) : RecyclerView.Adapter<Adapter.RankViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RankViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
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