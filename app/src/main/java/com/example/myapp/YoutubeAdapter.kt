package com.example.myapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class YoutubeAdapter(private var videoIds: List<String>, private var descriptions: List<String>) : RecyclerView.Adapter<YoutubeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_youtube, parent, false)
        return YoutubeViewHolder(view)
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        val videoId = videoIds[position % videoIds.size]
        val description = descriptions[position % descriptions.size]

        holder.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })

        val descriptionTextView = holder.itemView.findViewById<TextView>(R.id.description)
        descriptionTextView.text = description
    }

    override fun getItemCount(): Int {
        return videoIds.size
    }

    fun updateData(videoIds: List<String>, descriptions: List<String>) {
        this.videoIds = videoIds
        this.descriptions = descriptions
        notifyDataSetChanged()
    }
}

