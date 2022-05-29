package es.uji.al394716.musicapp.model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import es.uji.al394716.musicapp.Entities.Artist
import es.uji.al394716.musicapp.Entities.Song
import org.json.JSONException
import org.json.JSONObject
import javax.xml.transform.ErrorListener

private const val HOST_HEADER_NAME = "X-RapidAPI-Host"
private const val KEY_HEADER_NAME = "X-RapidAPI-Key"
private const val KEY_HEADER = "be915f27a0mshd8a5e73640133f3p12645djsn77b9048a7d55"
private const val HOST_HEADER = "genius.p.rapidapi.com"

private const val FIRST_URL = "https://genius.p.rapidapi.com/search?q="

private const val LIST = "hits"             //And then "result"
private const val TITLE = "title"
private const val FULLTITLE = "full_title"
private const val SONG_ID = "id"
private const val DATE = "release_date_for_display"
private const val IMG = "header_image_url"
private const val ARTIST = "artist_names"
private const val PRIMARY_ARTIST = "primary_artist"
private const val ARTIST_URL = "url"        //Inside "primary artist", the one outside is for lyrics
private const val ARTIST_ID = "id"          //Inside "primary artist", the one outside is for the song
private const val RESULT = "result"

class Network private constructor(context: Context){

    companion object: SingletonHolder<Network, Context>(::Network)
    val queue = Volley.newRequestQueue(context)

    //To get the songs from the network
    fun getSongs(nameSong: String, listener: Response.Listener<List<Song>>, errorListener: Response.ErrorListener){
        val url = "$FIRST_URL$nameSong"
        Log.d("Genius Song", " EL URL ES $url")
        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null,
            { response -> processSong(response, listener, errorListener)},
            { error -> errorListener.onErrorResponse(error)}) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers[HOST_HEADER_NAME] = HOST_HEADER
                headers[KEY_HEADER_NAME] = KEY_HEADER
                return headers
            } }
        queue.add(jsonObjectRequest)
    }

    //To process the songs obtained, called by getSongs
    private fun processSong(response: JSONObject, listener: Response.Listener<List<Song>>, errorListener: Response.ErrorListener) {
        val songs = ArrayList<Song>()
        try{
            val answer = response.getJSONObject("response")
            val songsArray = answer.getJSONArray(LIST)
            for (i in 0 until songsArray.length()){
                val songObject : JSONObject = songsArray[i] as JSONObject
                val songResult = songObject.getJSONObject("result")
                val id = songResult.getInt(SONG_ID)
                val title = songResult.getString(TITLE)
                val artist = songResult.getString(ARTIST)
                val date = songResult.getString(DATE)
                val img = songResult.getString(IMG)
                val fullT = songResult.getString(FULLTITLE)
                val song = Song(id,title,artist,date,img,fullT)
                songs.add(song)
            }

        }catch (e : JSONException){
            errorListener.onErrorResponse(VolleyError("Bad JSON format: Processing songs"))
        }
        songs.sortBy {it.title}
        listener.onResponse(songs)
    }

    fun getArtists(nameSongFromArtist: String, listener: Response.Listener<List<Artist>>, errorListener: Response.ErrorListener) {
        val url = "$FIRST_URL$nameSongFromArtist"
        Log.d("Genius Song", " EL URL ES $url")
        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null,
            { response -> processArtist(response, listener, errorListener)},
            { error -> errorListener.onErrorResponse(error)}) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers[HOST_HEADER_NAME] = HOST_HEADER
                headers[KEY_HEADER_NAME] = KEY_HEADER
                return headers
            } }
        queue.add(jsonObjectRequest)
    }

    //To process the artists obtained, called by getArtists
    private fun processArtist(response: JSONObject, listener: Response.Listener<List<Artist>>, errorListener: Response.ErrorListener) {
        val artists = ArrayList<Artist>()
        try{
            val answer = response.getJSONObject("response")
            val artistsArray = answer.getJSONArray(LIST)
            for (i in 0 until artistsArray.length()){
                val artistObject : JSONObject = artistsArray[i] as JSONObject
                val artistResult = artistObject.getJSONObject(RESULT)
                val primaryArtistResult = artistResult.getJSONObject(PRIMARY_ARTIST)
                val id = primaryArtistResult.getInt(ARTIST_ID)
                val idSong = artistResult.getInt(SONG_ID)
                val title = artistResult.getString(TITLE)
                val name = artistResult.getString(ARTIST)
                val url = primaryArtistResult.getString(ARTIST_URL)
                val artist = Artist(id,idSong,title,name, url)
                artists.add(artist)
            }

        }catch (e : JSONException){
            errorListener.onErrorResponse(VolleyError("Bad JSON format: Processing songs"))
        }
        artists.sortBy {it.artistName}
        listener.onResponse(artists)
    }
}