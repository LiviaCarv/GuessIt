package com.example.guessit.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.guessit.R
import com.example.guessit.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private var word = ""
    private var score = 0

    private lateinit var wordList: MutableList<String>
    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        resetList()
        nextWord()

        binding.btnGotIt.setOnClickListener { onCorrect() }
        binding.btnSkip.setOnClickListener { onSkip() }
        updateWordText()

        return binding.root
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
            gameFinished()
        } else {
            word = wordList.removeAt(0)
        }
        updateWordText()
    }


    private fun updateWordText() {
        binding.txtWord.text = word
    }


    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(score)
        findNavController().navigate(action)
    }

    private fun onSkip() {
        score--
        nextWord()
    }

    private fun onCorrect() {
        score++
        nextWord()
    }


}