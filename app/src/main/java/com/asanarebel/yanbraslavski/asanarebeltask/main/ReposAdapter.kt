package com.asanarebel.yanbraslavski.asanarebeltask.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.asanarebel.yanbraslavski.asanarebeltask.R
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.GithubRepoResponseModel
import com.squareup.picasso.Picasso

/**
 * Created by yan.braslavski on 11/14/17.
 */
class ReposAdapter(private val mDataItems: List<GithubRepoResponseModel>,
                   private val mListener: ((GithubRepoResponseModel) -> Unit)?) : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.repo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mDataItems[position]
        holder.mItem?.let { item ->

            //update question title
            holder.mRepoNameTextView.text = item.name
            holder.mRepoDescriptionTextView.text = item.description
            holder.mRepoForksCountTextView.text = "${item.forks_count}"
            Picasso.with(holder.mAvatarImage.context).load(item.owner.avatar_url).placeholder(R.drawable.ic_launcher_background).into(holder.mAvatarImage)
            holder.itemView.setOnClickListener({
                mListener?.invoke(item)
            })
        }
    }

    override fun getItemCount(): Int {
        return mDataItems.size
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mRepoNameTextView: TextView = mView.findViewById(R.id.repo_name_text_view)
        val mRepoDescriptionTextView: TextView = mView.findViewById(R.id.repo_description_text_view)
        val mRepoForksCountTextView: TextView = mView.findViewById(R.id.forks_count_text_view)
        val mAvatarImage: ImageView = mView.findViewById(R.id.avatar_image_view)
        var mItem: GithubRepoResponseModel? = null
    }
}