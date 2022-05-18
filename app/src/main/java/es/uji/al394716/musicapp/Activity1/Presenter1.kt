package es.uji.al394716.musicapp.Activity1

import es.uji.al394716.musicapp.model.Model

class Presenter1(val view: Interface1, val model: Model) {

    private var wordSelected : String? = null

    init{
        val spinnerWords = model.words_array    //Getting the words array from the model
        view.showWords(spinnerWords)            //Call to show the words in the spinner, passing the words array
    }

    fun setChosenWord(word: String){            //To enable buttons and get the word selected
        wordSelected = word
        view.enableButtons()
    }

    fun doSearchSong(){     //When button is pressed, go to the song activity
        view.startNewActivity(wordSelected, false)
    }

    fun doSearchArtist(){     //When button is pressed, go to the artist activity
        view.startNewActivity(wordSelected, true)
    }
}