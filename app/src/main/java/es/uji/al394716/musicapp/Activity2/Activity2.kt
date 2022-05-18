package es.uji.al394716.musicapp.Activity2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.uji.al394716.musicapp.R
import es.uji.al394716.musicapp.model.Model

class Activity2 : AppCompatActivity(), Interface2{

    private var songPart : String? = null
    private var searchedByArtist : Boolean = false
    lateinit var recyclerView: RecyclerView
    lateinit var presenter : Presenter2

    companion object{
        const val SONG_PART = "s"
        const val BY_ARTIST = "a"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.busqueda_layout)
        songPart = intent.getStringExtra(SONG_PART)
        searchedByArtist = intent.getBooleanExtra(BY_ARTIST, false)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val model = Model(applicationContext)
        presenter = Presenter2(this, model)
    }
}