package es.uji.al394716.musicapp.Activity2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.al394716.musicapp.Entities.Song
import es.uji.al394716.musicapp.R

class AdapterSongs(val songsList: List<Song>): RecyclerView.Adapter<AdapterSongs.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val songNameTV: TextView = view.findViewById(R.id.songName)
        val artistTV: TextView = view.findViewById(R.id.artist_song_name)
        val dateTV: TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(songsList[position]) {
            holder.songNameTV.text = title
            holder.artistTV.text = artistSongName
            holder.dateTV.text = date
        }
    }

    override fun getItemCount(): Int {
        return songsList.size
    }
}