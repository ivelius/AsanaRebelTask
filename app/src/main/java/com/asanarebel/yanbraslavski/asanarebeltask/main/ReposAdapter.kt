package com.asanarebel.yanbraslavski.asanarebeltask.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.asanarebel.yanbraslavski.asanarebeltask.R
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.GithubRepoResponseModel

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
        var mItem: GithubRepoResponseModel? = null
    }
}