package es.uji.al394716.musicapp.model

import android.content.Context
import com.android.volley.Response

class Model(context: Context) {
    private val network = Network.getInstance(context)
    val words_array = arrayOf("Love", "Live", "Day", "Night", "World", "Time", "Star", "Death", "Moon", "Sun", "Luck")

}