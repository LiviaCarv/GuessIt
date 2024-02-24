package com.example.guessit.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // inicializados como null
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    // private to the GameViewModel
    private val _score = MutableLiveData<Int>()
    // LiveData is read only, thats why its public
    val score : LiveData<Int>
        get() = _score

    private lateinit var wordList: MutableList<String>

    init {
        _score.value = 0
        _word.value = ""
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
            _word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        _score.value = (_score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(1)
        nextWord()
    }
}