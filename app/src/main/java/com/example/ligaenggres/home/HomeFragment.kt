package com.example.ligaenggres.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ligaenggres.R
import com.example.ligaenggres.core.data.Resource
import com.example.ligaenggres.core.ui.ClubAdapter
import com.example.ligaenggres.databinding.FragmentHomeBinding
import com.example.ligaenggres.detail.DetailClubActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Fragment view not initialized yet.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val tourismAdapter = ClubAdapter()
            tourismAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailClubActivity::class.java)
                intent.putExtra(DetailClubActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.club.observe(viewLifecycleOwner) { tourism ->
                if (tourism != null) {
                    when (tourism) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            tourismAdapter.setData(tourism.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                tourism.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvTourism) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tourismAdapter
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}