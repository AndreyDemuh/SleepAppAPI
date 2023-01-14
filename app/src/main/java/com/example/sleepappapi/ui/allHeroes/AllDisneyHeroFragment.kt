package com.example.sleepappapi.ui.allBase

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sleepappapi.R
import com.example.sleepappapi.databinding.FragmentAllDisneyHeroBinding
import com.example.sleepappapi.model.CharactersHero
import com.example.sleepappapi.ui.allHeroes.adapter.AllHeroesAdapter
import com.example.sleepappapi.ui.allHeroes.adapter.FavouriteHeroesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllDisneyHeroFragment : Fragment() {

    private lateinit var binding: FragmentAllDisneyHeroBinding
    private val viewModel: AllCharactersViewModel by viewModels()

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerFavouriteHero: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllDisneyHeroBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMyLike.setOnClickListener {
            initFavouriteHero()
            binding.btnMyLike.setImageResource(R.drawable.heart_like)
            binding.titleText.text = "Список любимых героев"
            ObjectAnimator.ofFloat(binding.btnMyLike, View.ALPHA, 0.2F, 1F).start()
        }

        binding.btnAll.setOnClickListener {
            viewModel.getAllDisneyHero()
            lifecycleScope.launch {
                viewModel.flowHero.collectLatest { pagingData ->
                    initHero(pagingData)
                }
            }
            binding.btnMyLike.setImageResource(R.drawable.heart_disslike)
            binding.titleText.text = "Список всех героев"
            ObjectAnimator.ofFloat(binding.btnAll, View.ALPHA, 0.2F, 1F).start()
        }

        viewModel.onError = {
            binding.bannerView.post {
                binding.bannerView.visibility = View.VISIBLE
                binding.bannerView.setTitle("Проверьте подключение к Интернету")
                binding.recyclerAllHero.visibility = View.INVISIBLE
                binding.progress.visibility = View.VISIBLE
                binding.bannerView.setClickOk {
                    binding.progress.visibility = View.GONE
                    binding.recyclerAllHero.visibility = View.VISIBLE
                    viewModel.getAllDisneyHero()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.flowHero.collectLatest { pagingData ->
                initHero(pagingData)
            }
        }
    }

    private fun initFavouriteHero() {
        viewModel.getListFavouriteHero()
        recyclerFavouriteHero = binding.recyclerAllHero
        recyclerFavouriteHero.run {
            adapter = FavouriteHeroesAdapter {
                findNavController().navigate(
                    AllDisneyHeroFragmentDirections
                        .actionAllDisneyHeroFragmentToOneHeroCardFragment(
                            it.id.toString(),
                            it.name
                        )
                )
            }
        }
        viewModel.listFavouriteHero.observe(viewLifecycleOwner) { list ->
            (recyclerFavouriteHero.adapter as FavouriteHeroesAdapter).setListFavouriteHero(list)
        }
    }

    private suspend fun initHero(list: PagingData<CharactersHero>) {
        binding.recyclerAllHero.run {
            if (adapter == null) {
                adapter = AllHeroesAdapter {
                    findNavController().navigate(
                        AllDisneyHeroFragmentDirections
                            .actionAllDisneyHeroFragmentToOneHeroCardFragment(
                                it._id.toString(),
                                it.name
                            )
                    )
                }
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
            (adapter as? AllHeroesAdapter)?.submitData(list)
        }
    }
}