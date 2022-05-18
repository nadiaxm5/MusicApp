package es.uji.al394716.musicapp.Activity1

interface Interface1 {
    fun showWords(words: Array<String>)
    fun enableButtons()
    fun searchSong()
    fun searchArtist()
    fun startNewActivity(song: String?, byArtist: Boolean)
}