package es.uji.al394716.musicapp.model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import es.uji.al394716.musicapp.Entities.Song
import javax.xml.transform.ErrorListener

private const val HOST_HEADER_NAME = "X-RapidAPI-Host"
private const val KEY_HEADER_NAME = "X-RapidAPI-Key"
private const val KEY_HEADER = "be915f27a0mshd8a5e73640133f3p12645djsn77b9048a7d55"
private const val HOST_HEADER = "genius.p.rapidapi.com"

private const val SONGS_URL = "https://genius.p.rapidapi.com/songs/"

class Network private constructor(context: Context){

    companion object: SingletonHolder<Network, Context>(::Network)
    val queue = Volley.newRequestQueue(context)

    /*
    fun getSongs(listener: Response.Listener<List<Song>>, errorListener: Response.ErrorListener){
        val urlSongs = "$SONGS_URL"
        Log.d("Alcoholico", "EL URL ES $urlCategories")
        val jsonObjectRequestCategories = JsonObjectRequest(
            Request.Method.GET, urlCategories, null,
            { response -> processCategories(response, listener, errorListener) },
            { error -> errorListener.onErrorResponse(error) })
        queue.add(jsonObjectRequestCategories)
    }

    val stringRequest = object: StringRequest(Request.Method.GET, linkTrang,
        Response.Listener<String> { response ->
            Log.d("A", "Response is: " + response.substring(0,500))
        },
        Response.ErrorListener {  })
    {
        override fun getHeaders(): MutableMap<String, String> {
            val headers = HashMap<String, String>()
            headers["Authorization"] = "Basic <<YOUR BASE64 USER:PASS>>"
            return headers
        }
    }

    queue.add(stringRequest)
    */

}