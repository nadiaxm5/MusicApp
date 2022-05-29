package es.uji.al394716.musicapp.Activity2

import com.android.volley.VolleyError
import es.uji.al394716.musicapp.Entities.Artist
import es.uji.al394716.musicapp.Entities.Song

interface Interface2 {
    fun GetChosenWord() : String?
    fun GetIfSearchedByArtist() : Boolean
    fun showSongs(songs: List<Song>)
    fun showArtists(artists: List<Artist>)
    fun showError(message: String)
    fun toSongActivity()
}