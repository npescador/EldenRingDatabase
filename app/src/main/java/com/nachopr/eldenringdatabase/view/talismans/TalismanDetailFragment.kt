package com.nachopr.eldenringdatabase.view.talismans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nachopr.eldenringdatabase.R
import com.nachopr.eldenringdatabase.common.ResourceState
import com.nachopr.eldenringdatabase.databinding.FragmentTalismanDetailBinding
import com.nachopr.eldenringdatabase.model.remote.Talismans

class TalismanDetailFragment : Fragment() {
    private val binding: FragmentTalismanDetailBinding by lazy {
        FragmentTalismanDetailBinding.inflate(layoutInflater)
    }

    private val talismanViewModel: TalismanViewModel by activityViewModels()
    private val args: TalismanDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        talismanViewModel.getTalisman(args.talismanId)
    }

    private fun initViewModel() {
        talismanViewModel.getTalismanDetailLiveData().observe(viewLifecycleOwner) { state ->
            handleTalismanDetailState(state)
        }
    }

    private fun handleTalismanDetailState(state: TalismanDetailState) {
        when (state) {
            is ResourceState.Loading -> {
                binding.pbTalismanDetail.visibility = View.VISIBLE
            }

            is ResourceState.Success -> {
                binding.pbTalismanDetail.visibility = View.GONE
                initUI(state.result)
            }

            is ResourceState.Error -> {
                binding.pbTalismanDetail.visibility = View.GONE
                showErrorDialog(state.error)
            }
        }
    }

    private fun initUI(talisman: Talismans) {
        binding.tvTalismanDetailName.text = talisman.name

        Glide.with(requireContext())
            .load(talisman.image)
            .into(binding.ivTalismanDetail)

        binding.tvTalismanDetailEffect.text = talisman.effects
        binding.tvTalismanDetailDescription.text = talisman.description
    }

    private fun showErrorDialog(error: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.error)
            .setMessage(error)
            .setPositiveButton(R.string.action_ok, null)
            .setNegativeButton(R.string.action_retry) { dialog, witch ->
                //talismanViewModel.fetchCharacters()
            }
            .show()
    }
}