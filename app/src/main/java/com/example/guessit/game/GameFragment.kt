package com.example.guessit.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guessit.R
import com.example.guessit.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        // reference to GameViewModel
        Log.i("GameFragment", "called ViewModelProvider")

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        binding.btnGotIt.setOnClickListener {
            viewModel.onCorrect()
        }
        binding.btnSkip.setOnClickListener {
            viewModel.onSkip()
        }

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.txtCurrentScore.text = newScore.toString()
        })

       viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
           binding.txtWord.text = newWord
       })


        return binding.root
    }


    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(
            viewModel.score.value ?: 0)
        findNavController().navigate(action)
    }


}