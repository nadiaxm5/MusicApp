package es.uji.al394716.musicapp.Activity3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import es.uji.al394716.musicapp.R

class Activity3 : AppCompatActivity() {
    private var image : String? = null
    private var fullTitle : String? = null
    lateinit var imageView: ImageView
    lateinit var fullTitleTextView: TextView

    companion object{
        const val IMAGE = "image"
        const val FULLTITLE = "fullTitle"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        fullTitleTextView = findViewById(R.id.fullTitle)
        imageView = findViewById(R.id.imageV)

        image = intent.getStringExtra(IMAGE)
        fullTitle = intent.getStringExtra(FULLTITLE)

        fullTitleTextView.text = fullTitle

        Log.d("Image", image.toString())
        Log.d("Title", fullTitle.toString())
    }
}