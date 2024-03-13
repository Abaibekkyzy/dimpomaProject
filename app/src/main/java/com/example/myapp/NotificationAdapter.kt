package com.example.myapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.ItemNotificationBinding


class NotificationAdapter(private var originalList: List<Notifications>) :
    RecyclerView.Adapter<NotificationAdapter.MyViewHolder>() {

    private var notificationsList: List<Notifications> = originalList
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = notificationsList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return notificationsList.size
    }


    inner class MyViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(notificationsList[position])
                }
            }
        }

        fun bind(notificationsItem: Notifications) {
            binding.titleImage.setImageResource(notificationsItem.titleImage)
            binding.tvHeading.text = notificationsItem.heading
            binding.tvDescription.text = notificationsItem.description
            binding.tvTime.text = notificationsItem.time
        }
    }

    interface OnItemClickListener {
        fun onItemClick(news: Notifications)
    }
}
