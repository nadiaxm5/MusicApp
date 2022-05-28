package es.uji.al394716.musicapp.Activity1

import android.view.View

interface Interface1 {
    fun showWords(words: Array<String>)
    fun enableButtons()
    fun searchSong(view: View)
    fun searchArtist(view: View)
    fun startNewActivity(song: String?, byArtist: Boolean)
}