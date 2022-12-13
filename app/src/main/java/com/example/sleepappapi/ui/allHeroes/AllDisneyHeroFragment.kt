package com.example.sleepappapi.ui.allBase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sleepappapi.databinding.FragmentAllDisneyHeroBinding
import com.example.sleepappapi.model.CharactersHero
import com.example.sleepappapi.ui.allHeroes.allHeroModule
import com.example.sleepappapi.ui.allHeroes.adapter.AllHeroesAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class AllDisneyHeroFragment : Fragment() {

    private lateinit var binding: FragmentAllDisneyHeroBinding

    private val viewModel: AllCharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(allHeroModule)
    }

    override fun onDetach() {
        super.onDetach()
        unloadKoinModules(allHeroModule)
    }

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

        viewModel.onError = {
            binding.bannerView.post {
                binding.bannerView.visibility = View.VISIBLE
                binding.bannerView.setTitle("Проверьте подключение к Интернету")
                binding.recyclerAllHero.visibility = View.INVISIBLE
                binding.progress.visibility = View.VISIBLE
                binding.bannerView.setClickOk {
                    binding.progress.visibility = View.GONE
                    binding.recyclerAllHero.visibility = View.VISIBLE
                    viewModel.getDisneyHeroCharacters()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.flowHero.collectLatest { pagingData ->
                initHero(pagingData)
            }
        }
        viewModel.getDisneyHeroCharacters()
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