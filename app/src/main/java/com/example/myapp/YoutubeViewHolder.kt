package com.example.myapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val youtubePlayerView: YouTubePlayerView = itemView.findViewById(R.id.youtube_player_view)
}
