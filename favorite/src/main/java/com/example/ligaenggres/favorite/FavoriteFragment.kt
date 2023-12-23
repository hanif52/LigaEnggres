package com.example.ligaenggres.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ligaenggres.core.ui.ClubAdapter
import com.example.ligaenggres.databinding.FragmentFavoriteBinding
import com.example.ligaenggres.detail.DetailClubActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        loadKoinModules(favoriteModule)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val clubAdapter = ClubAdapter()
            clubAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailClubActivity::class.java)
                intent.putExtra(DetailClubActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteClub.observe(viewLifecycleOwner, { dataClub ->
                clubAdapter.setData(dataClub)
                binding.viewEmpty.root.visibility = if (dataClub.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvTourism) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = clubAdapter
            }
        }
    }
}