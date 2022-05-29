package es.uji.al394716.musicapp.Activity2

import android.util.Log
import es.uji.al394716.musicapp.Entities.Artist
import es.uji.al394716.musicapp.Entities.Song
import es.uji.al394716.musicapp.model.Model

class Presenter2(val view: Interface2, val model: Model) {
    private var word: String? = null
    private var searchedByArtist: Boolean = false
    private var listSongs = ArrayList<Song>()
    private var listArtists = ArrayList<Artist>()

    init{
        word = view.GetChosenWord() //Getting the chosen word from the interface
        searchedByArtist = view.GetIfSearchedByArtist() //Knowing if it is searched by artist

        if(!searchedByArtist){ //Searched by Song's button
            model.getSongs(word!!,
                { songs ->
                    if(songs.isEmpty()){
                        view.showError("Might be a bad JSON")
                    } else{
                        Log.d("From Presenter2 ", "I'm in the onResponse")
                        for(i in 0 until songs.size){
                            if(!listSongs.contains(songs[i])){ //No repetitions (Same artist with same song)
                                listSongs.add(songs[i])
                            }
                        }
                        view.showSongs(listSongs)
                    }
                }){ error -> view.showError(error.toString())}
        }

        else{
            model.getArtists(word!!,
                {artists ->
                    if(artists.isEmpty()){
                        view.showError("Might be a bad JSON")
                    } else{
                        Log.d("From Presenter2 ", "I'm in the onResponse")
                        for(i in 0 until artists.size){
                            if(!listArtists.contains(artists[i])){ //No repetitions (Same artist with same song)
                                listArtists.add(artists[i])
                            }
                        }
                        view.showArtists(listArtists)
                    }
                }){ error -> view.showError(error.toString())}
        }
    }


    fun onArtistSelected(position: Int){
        for(i in 0 until listArtists.size){
            if(listArtists[i].idArtist == listArtists[position].idArtist){
                view.setArtistPage(listArtists[i].urlPage!!)
                view.dialogManager()
            }
        }
    }

    fun onSongSelected(position: Int){
        for(i in 0 until listSongs.size){
            if(listSongs[i].idSong == listSongs[position].idSong){
                view.toSongActivity(listSongs[i].image!!, listSongs[i].fullTitle!!)
            }
        }
    }
}