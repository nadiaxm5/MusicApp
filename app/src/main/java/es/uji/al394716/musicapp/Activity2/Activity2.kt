package es.uji.al394716.musicapp.Activity2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.uji.al394716.musicapp.Entities.Artist
import es.uji.al394716.musicapp.Entities.Song
import es.uji.al394716.musicapp.R
import es.uji.al394716.musicapp.model.Model

class Activity2 : AppCompatActivity(), Interface2 {

    private var songPart : String? = null
    private var searchedByArtist : Boolean = false
    lateinit var recyclerView: RecyclerView
    lateinit var presenter : Presenter2

    companion object{
        const val SONG_PART = "songPart"
        const val BY_ARTIST = "byArtist"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.busqueda_layout)

        Log.d("Update", "I'm in activity2")

        songPart = intent.getStringExtra(SONG_PART)
        searchedByArtist = intent.getBooleanExtra(BY_ARTIST, false)

        recyclerView = findViewById(R.id.recyclerViewID)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val model = Model(applicationContext)
        presenter = Presenter2(this, model)
    }

    override fun GetChosenWord() : String? {
        return songPart
    }

    override fun GetIfSearchedByArtist(): Boolean {
        return searchedByArtist
    }

    override fun showSongs(songs: List<Song>) { //The list goes to the adapter and then to the view
        recyclerView.adapter = AdapterSongs(songs)
    }

    override fun showArtists(artists: List<Artist>) { //The list goes to the adapter and then to the view
        recyclerView.adapter = AdapterArtists(artists)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()
    }
}