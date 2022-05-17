package es.uji.al394716.musicapp.Activity1

import es.uji.al394716.musicapp.model.Model

class Presenter1(val view: Interface1, val model: Model) {
    init{
        val spinnerWords = model.words_array
        view.showWords(spinnerWords)
    }

    fun setChosenWord(word: String){

    }
}