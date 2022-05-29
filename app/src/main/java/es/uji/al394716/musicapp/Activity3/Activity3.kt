package es.uji.al394716.musicapp.Activity3

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.toolbox.ImageRequest
import es.uji.al394716.musicapp.R
import es.uji.al394716.musicapp.model.VolleySingleton

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

        // request a image response from the provided url
        val imageRequest = ImageRequest(
            image,
            {bitmap -> // response listener
                imageView.setImageBitmap(bitmap)
            },
            0, // max width
            0, // max height
            ImageView.ScaleType.CENTER_CROP, // image scale type
            Bitmap.Config.ARGB_8888, // decode config
            {error-> // error listener
                Log.d("Error", "En imagen")
            }
        )

        VolleySingleton.getInstance(applicationContext)
            .addToRequestQueue(imageRequest)

    }
}