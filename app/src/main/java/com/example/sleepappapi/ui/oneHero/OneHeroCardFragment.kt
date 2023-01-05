package com.example.sleepappapi.ui.hero

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.sleepappapi.Hero
import com.example.sleepappapi.InfoHero
import com.example.sleepappapi.R
import com.example.sleepappapi.databinding.FragmentCardHeroBinding
import com.example.sleepappapi.repository.SaveSharedPref
import com.example.sleepappapi.ui.oneHero.adapter.InfoHeroAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OneHeroCardFragment : Fragment() {

    private lateinit var binding: FragmentCardHeroBinding
    private val viewModel: OneHeroViewModel by viewModels()
    private val args: OneHeroCardFragmentArgs by navArgs()
    private var isFavouriteHeroIcon = false

    lateinit var currentHero: Hero

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

        binding.btnLike.setOnClickListener {
            val saveShared = SaveSharedPref(requireContext())
            if (!currentHero.isFavourite) {
                binding.btnLike.setImageResource(R.drawable.heart_like)
                saveShared.setFavourite(currentHero.id.toString(), true)
                viewModel.chooseHeroFavourite(currentHero)
                binding.messageView.visibility = View.VISIBLE
                binding.messageView.setTitle("Герой успешно добавлен в избранное")
                binding.messageView.setClickClose {
                    binding.messageView.visibility = View.GONE
                }
            } else {
                binding.btnLike.setImageResource(R.drawable.heart_disslike)
                saveShared.setFavourite(currentHero.id.toString(), false)
                viewModel.chooseHeroFavourite(currentHero)
                binding.messageView.visibility = View.VISIBLE
                binding.messageView.setTitle("Герой успешно удален из избранного")
                binding.messageView.setClickClose {
                    binding.messageView.visibility = View.GONE
                }
            }
            ObjectAnimator.ofFloat(binding.btnLike, View.ALPHA, 0.3F, 1F).start()
        }

        viewModel.run {
            val saveShared = SaveSharedPref(requireContext())
            oneHero.observe(viewLifecycleOwner) {
                currentHero = it
                Glide.with(requireContext())
                    .load(it.imageUrl)
                    .centerCrop()
                    .into(binding.imageHeroCard)
                setList(it.listInfo)
                val booleanFavourite = saveShared.getFavourite(currentHero.id.toString())
                if (isFavouriteHeroIcon != booleanFavourite) {
                    binding.btnLike.setImageResource(R.drawable.heart_like)
                    isFavouriteHeroIcon = true
                } else {
                    binding.btnLike.setImageResource(R.drawable.heart_disslike)
                    isFavouriteHeroIcon = false
                }
            }
            binding.tvNameHero.text = args.nameHero
        }
        viewModel.getInfoOneHero(args.idHero)
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