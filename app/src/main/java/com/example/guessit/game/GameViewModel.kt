package com.example.guessit.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // inicializados como null
    var word = MutableLiveData<String>()
    var score = MutableLiveData<Int>()

    private lateinit var wordList: MutableList<String>

    init {
        score.value = 0
        word.value = ""
        resetList()
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }


    private fun resetList() {
        wordList = mutableListOf(
            "telephone",
            "computer",
            "key",
            "piano",
            "mirror",
            "bicycle"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            // gameFinished()
        } else {
            word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        score.value = (score.value)?.plus(1)
        nextWord()
    }
}