package com.example.sleepappapi.ui.hero

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.sleepappapi.InfoHero
import com.example.sleepappapi.R
import com.example.sleepappapi.databinding.FragmentCardHeroBinding
import com.example.sleepappapi.ui.oneHero.adapter.InfoHeroAdapter
import com.example.sleepappapi.ui.oneHero.oneHeroModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


class OneHeroCardFragment : Fragment() {

    private lateinit var binding: FragmentCardHeroBinding

    private val viewModel: OneHeroViewModel by viewModel()

    val args: OneHeroCardFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(oneHeroModule)
    }

    override fun onDetach() {
        super.onDetach()
        unloadKoinModules(oneHeroModule)
    }

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
        viewModel.run {
            imageHeroUrl.observe(viewLifecycleOwner) {
                Glide.with(requireContext())
                    .load(it.imageUrl)
                    .centerCrop()
                    .into(binding.imageHeroCard)
                setList(it.listInfo)
            }
            binding.tvNameHero.text = args.nameHero
        }
        viewModel.getImageOneHeroInfo(args.idHero)
    }

    private fun setList(list: ArrayList<InfoHero>) {
        binding.heroInfoRV.run {
            if (adapter == null) {
                adapter = InfoHeroAdapter()
                layoutManager = LinearLayoutManager(requireContext())
            }
            (adapter as? InfoHeroAdapter)?.submitList(list)
        }

    }
}