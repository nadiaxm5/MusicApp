package es.uji.al394716.musicapp.model

import android.content.Context
import com.android.volley.Response
import es.uji.al394716.musicapp.Entities.Artist
import es.uji.al394716.musicapp.Entities.Song

class Model(context: Context) {

    private val words_array = arrayOf("Love", "Live", "Feel", "Night", "Like", "Time", "Hand", "Body", "Thing", "Good")
    private val network = Network.getInstance(context)

    fun getWordsArray(): Array<String> {
        return words_array
    }

    fun getSongs(nameSong: String, listener: Response.Listener<List<Song>>, errorListener: Response.ErrorListener){
        network.getSongs(nameSong, listener, errorListener)
    }

    fun getArtists(nameSongFromArtist: String, listener: Response.Listener<List<Artist>>, errorListener: Response.ErrorListener){
        network.getArtists(nameSongFromArtist, listener, errorListener)
    }
}