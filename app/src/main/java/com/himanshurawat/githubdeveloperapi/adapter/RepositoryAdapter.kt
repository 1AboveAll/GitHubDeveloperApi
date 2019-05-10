package com.himanshurawat.githubdeveloperapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.himanshurawat.githubdeveloperapi.R
import com.himanshurawat.githubdeveloperapi.pojo.Repo
import com.himanshurawat.githubdeveloperapi.pojo.Users
import org.w3c.dom.Text

class RepositoryAdapter(val context: Context, val dataList: MutableList<Repo>): RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val pos = holder.adapterPosition
        val item = dataList[pos]
        holder.fullNameTextView.text = item.fullName
        holder.starTextView.text = item.stars.toString()
        if(item.description != null){
            holder.descriptionTextView.text = item.description
            holder.descriptionTextView.visibility = View.VISIBLE
        }else{
            holder.descriptionTextView.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(LayoutInflater.from(context).inflate(R.layout.repository_list_item,parent,false))
    }


    class RepositoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val fullNameTextView: TextView = itemView.findViewById(R.id.repository_list_item_full_name_text_view)
        val starTextView: TextView = itemView.findViewById(R.id.repository_list_item_star_text_view)
        val descriptionTextView: TextView = itemView.findViewById(R.id.repository_list_item_description_text_view)
    }

}