package es.uji.al394716.musicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import es.uji.al394716.musicapp.Activity1.Interface1
import es.uji.al394716.musicapp.Activity1.Presenter1
import es.uji.al394716.musicapp.Activity2.Activity2
import es.uji.al394716.musicapp.Activity2.AdapterArtists
import es.uji.al394716.musicapp.Activity2.AdapterSongs
import es.uji.al394716.musicapp.Activity2.Presenter2
import es.uji.al394716.musicapp.Entities.Artist
import es.uji.al394716.musicapp.Entities.Song
import es.uji.al394716.musicapp.model.Model

class MainActivity : AppCompatActivity(), Interface1 {
    lateinit var searchSpinner: Spinner
    lateinit var artistButton: Button
    lateinit var songButton: Button
    lateinit var radioLocal: RadioButton
    lateinit var radioOnline: RadioButton
    lateinit var artistTextView: TextView

    lateinit var presenter: Presenter1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchSpinner = findViewById(R.id.searchSpinner)
        artistButton = findViewById(R.id.artistButton)
        songButton = findViewById(R.id.songButton)
        radioLocal = findViewById(R.id.localRadioB)
        radioOnline = findViewById(R.id.onlineRadioB)
        artistTextView = findViewById(R.id.searchTextView)

        songButton.isEnabled = false        //Buttons are not enabled if you do not select a word
        artistButton.isEnabled = false

        val model = Model(applicationContext)
        presenter = Presenter1(this, model)
    }

    override fun showWords(words: Array<String>) {  //To show the words to use in the spinner
        words.sort()                                //Alphabetically sorted
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, words)
        searchSpinner.adapter = adapter
        object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val word : String = searchSpinner.getItemAtPosition(p2) as String
                presenter.setChosenWord(word)       //Call to pass the word selected
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }.also {searchSpinner.onItemSelectedListener = it}
    }

    override fun enableButtons(){                    //Buttons are visible if you select a word
        artistButton.isEnabled = true
        songButton.isEnabled = true
    }

    override fun searchSong(view: View){      //Song's button is pressed
        presenter.doSearchSong()
    }

    override fun searchArtist(view: View){    //Artist's button is pressed
        presenter.doSearchArtist()
    }

    //Go to activity 2 passing the song part and if it is searched by Artist
    override fun startNewActivity(songPart: String?, byArtist: Boolean) {

        val intent = Intent(this, Activity2::class.java).apply {
            putExtra(Activity2.SONG_PART, songPart)
            putExtra(Activity2.BY_ARTIST, byArtist)
        }
        startActivity(intent)
        if (intent.resolveActivity(packageManager)!=null){

        }
    }
}