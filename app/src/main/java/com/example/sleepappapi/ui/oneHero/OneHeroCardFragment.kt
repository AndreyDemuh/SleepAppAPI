package com.example.sleepappapi.ui.hero

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.sleepappapi.R
import com.example.sleepappapi.databinding.FragmentCardHeroBinding
import dagger.hilt.android.AndroidEntryPoint

private const val ID_HERO = "idHero"

@AndroidEntryPoint
class OneHeroCardFragment : Fragment() {

    private lateinit var binding: FragmentCardHeroBinding
    private val viewModel: OneHeroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardHeroBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.isHeroFavourite.observe(viewLifecycleOwner) {
            binding.btnLike.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    if (it) {
                        R.drawable.heart_like
                    } else {
                        R.drawable.heart_disslike
                    }
                )
            )
            ObjectAnimator.ofFloat(binding.btnLike, View.ALPHA, 0.3F, 1F).start()
        }
        binding.btnLike.setOnClickListener {
            viewModel.chooseHeroFavourite()
        }
        viewModel.imageHeroUrl.observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load(it)
                .into(binding.imageHeroCard)
        }

        arguments?.getString(ID_HERO)?.let {
            Log.d("MyLog", "getString arguments: $it")
            viewModel.getImageOneHeroInfo(it)
        }
    }

    companion object {

        fun getHeroFragmentInstance(id: String): OneHeroCardFragment {
            return OneHeroCardFragment().apply {
                Log.d("MyLog", "getHeroFragmentInstance $id")
                arguments = bundleOf(ID_HERO to id)
            }
        }
    }
}