package es.uji.al394716.musicapp.Activity2

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import es.uji.al394716.musicapp.Activity3.Activity3

class DialogArtist(val view: Interface2) : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (activity == null)
            throw IllegalStateException("Activity cannot be null")
        return AlertDialog.Builder(activity).run {
            setTitle("Artist's Web")
            setMessage("Go to artist's web?")
            setPositiveButton("Let's go!") { dialog, which -> goToArtistPage()}
            setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
            create()
        }
    }

    fun goToArtistPage(){
        var url = view.GetArtistPage()

        if(!url.startsWith("https://")){ //Adding de security s if necessary
            url = "https://" + url
        }

        val page = Uri.parse(url)

        val intent = Intent(Intent.ACTION_VIEW, page)
        startActivity(intent)

    }
}