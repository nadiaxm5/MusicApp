package es.uji.al394716.musicapp.Activity2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.al394716.musicapp.Entities.Artist
import es.uji.al394716.musicapp.R

class AdapterArtists(val artistsList: List<Artist>, val onClickListener: (Int) -> Unit): RecyclerView.Adapter<AdapterArtists.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val artistNameTV: TextView = view.findViewById(R.id.artistName)
        val artistSongTV: TextView = view.findViewById(R.id.artistSongTV)
        val urlArtistPageTV: TextView = view.findViewById(R.id.url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.artist_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(artistsList[position]) {
            holder.artistNameTV.text = artistName
            holder.urlArtistPageTV.text = urlPage
            holder.artistSongTV.text = titleArtistSong
            holder.itemView.setOnClickListener{
                onClickListener(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return artistsList.size
    }
}