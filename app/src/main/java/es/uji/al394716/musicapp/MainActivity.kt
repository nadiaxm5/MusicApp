package es.uji.al394716.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import es.uji.al394716.musicapp.Activity1.Interface1
import es.uji.al394716.musicapp.Activity1.Presenter1
import es.uji.al394716.musicapp.model.Model

class MainActivity : AppCompatActivity(), Interface1 {
    lateinit var songAutoComplete: AutoCompleteTextView
    lateinit var artistSpinner: Spinner
    lateinit var artistButton: Button
    lateinit var songButton: Button
    lateinit var radioLocal: RadioButton
    lateinit var radioOnline: RadioButton
    lateinit var artistTextView: TextView
    lateinit var songTextView: TextView

    lateinit var presenter: Presenter1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songAutoComplete = findViewById(R.id.autoComplete)
        artistSpinner = findViewById(R.id.artistSpinner)
        artistButton = findViewById(R.id.artistButton)
        songButton = findViewById(R.id.songButton)
        radioLocal = findViewById(R.id.localRadioB)
        radioOnline = findViewById(R.id.onlineRadioB)
        artistTextView = findViewById(R.id.searchTextView)
        songTextView = findViewById(R.id.songTextView)

        val model = Model(applicationContext)
        presenter = Presenter1(this, model)
    }
}