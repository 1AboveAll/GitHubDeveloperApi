package com.himanshurawat.githubdeveloperapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.himanshurawat.githubdeveloperapi.R
import com.himanshurawat.githubdeveloperapi.pojo.User


class UserAdapter(private val context: Context,private val dataList: List<User>,private val listener: OnUserItemClickListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.user_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val pos = holder.adapterPosition
        val item = dataList[pos]




    }


    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameTextView: TextView = itemView.findViewById(R.id.user_list_item_name_text_view)
        val placeTextView: TextView = itemView.findViewById(R.id.user_list_item_place_text_view)
        val profileImageView: ImageView = itemView.findViewById(R.id.user_list_item_profile_image_view)
        val textLinearLayout: LinearLayout = itemView.findViewById(R.id.user_list_item_linear_layout)
    }

    interface OnUserItemClickListener{
        fun onUserItemClick()
    }
}
