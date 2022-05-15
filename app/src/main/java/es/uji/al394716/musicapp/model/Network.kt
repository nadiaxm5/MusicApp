package es.uji.al394716.musicapp.model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import es.uji.al394716.musicapp.Entities.Song
import org.json.JSONException
import org.json.JSONObject
import javax.xml.transform.ErrorListener

private const val HOST_HEADER_NAME = "X-RapidAPI-Host"
private const val KEY_HEADER_NAME = "X-RapidAPI-Key"
private const val KEY_HEADER = "be915f27a0mshd8a5e73640133f3p12645djsn77b9048a7d55"
private const val HOST_HEADER = "genius.p.rapidapi.com"

private const val SONG_URL = "https://genius.p.rapidapi.com/songs/"

private const val NAME = "title"
private const val ID = "id"
private const val SONG_ARRAY = "song"
//song id that actually works 3039923

class Network private constructor(context: Context){

    companion object: SingletonHolder<Network, Context>(::Network)
    val queue = Volley.newRequestQueue(context)

    fun getSongWithId(idSong: Int, listener: Response.Listener<Song>, errorListener: Response.ErrorListener){
        val urlSong = "$SONG_URL$idSong"
        Log.d("Genius", "EL URL ES $urlSong")
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, urlSong, null,
            { response -> processSongWithId(response, listener, errorListener) },
            { error -> errorListener.onErrorResponse(error) })
             fun getHeaders(): MutableMap<String, String> {                                         //override!!
                val headers = HashMap<String, String>()
                headers[HOST_HEADER_NAME] = HOST_HEADER
                headers[KEY_HEADER_NAME] = KEY_HEADER
                return headers
            }
        queue.add(jsonObjectRequest)
    }

    private fun processSongWithId(response: JSONObject, listener: Response.Listener<Song>, errorListener: Response.ErrorListener) {
        Log.d("Genius", "PROCESANDO JSON...")

        try {
            val songArray = response.getJSONArray(SONG_ARRAY)
            val songObject : JSONObject = songArray[0] as JSONObject
            val songId = songObject.getInt(ID)
            val songName = songObject.getString(NAME)
            val song = Song(songId, songName)
            listener.onResponse(song)
            Log.d("Genius", "Funciona")
        } catch (e: JSONException){
            errorListener.onErrorResponse(VolleyError("BAD JSON processing"))
        }
    }
}