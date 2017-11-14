package com.asanarebel.yanbraslavski.asanarebeltask.details

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.asanarebel.yanbraslavski.asanarebeltask.R
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.SubscribersResponseModel
import com.squareup.picasso.Picasso

/**
 * Created by yan.braslavski on 11/14/17.
 */
class SubscribersAdapter(private val mDataItems: List<SubscribersResponseModel>,
                         private val mListener: ((SubscribersResponseModel) -> Unit)?)
    : RecyclerView.Adapter<SubscribersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.subscriber_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mDataItems[position]
        holder.mItem?.let { item ->

            //update question title
            holder.mSubscriberName.text = item.login
            Picasso.with(holder.mAvatarImage.context).load(item.avatar_url).placeholder(R.drawable.ic_launcher_background).into(holder.mAvatarImage)
            holder.itemView.setOnClickListener({
                mListener?.invoke(item)
            })
        }
    }

    override fun getItemCount(): Int {
        return mDataItems.size
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mSubscriberName: TextView = mView.findViewById(R.id.subscriber_name_text_view)
        val mAvatarImage: ImageView = mView.findViewById(R.id.avatar_image_view)
        var mItem: SubscribersResponseModel? = null
    }
}