package com.nachopr.eldenringdatabase.view.talismans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nachopr.eldenringdatabase.R
import com.nachopr.eldenringdatabase.common.ResourceState
import com.nachopr.eldenringdatabase.databinding.FragmentTalismansBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class TalismansFragment : Fragment() {

    private val binding: FragmentTalismansBinding by lazy {
        FragmentTalismansBinding.inflate(layoutInflater)
    }

    private val talismansAdapter = TalismanAdapter()
    private val talismanViewModel: TalismanViewModel by activityViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initUI()

        talismanViewModel.getTalismans()
    }

    private fun initViewModel() {
        talismanViewModel.getTalismanLiveData().observe(viewLifecycleOwner) { state ->
            handleTalismansListState(state)
        }
    }

    private fun handleTalismansListState(state: TalismanListState) {
        when (state) {
            is ResourceState.Loading -> {
                binding.pbTalismanList.visibility = View.VISIBLE
            }

            is ResourceState.Success -> {
                binding.pbTalismanList.visibility = View.GONE
                talismansAdapter.submitTalismanList(state.result)
            }

            is ResourceState.Error -> {
                binding.pbTalismanList.visibility = View.GONE
                showErrorDialog(state.error)
            }
        }
    }

    private fun initUI() {
        binding.rvTalismansList.adapter = talismansAdapter
        binding.rvTalismansList.layoutManager = LinearLayoutManager(requireContext())

        talismansAdapter.onClickListener = { talisman ->
            findNavController().navigate(
                TalismansFragmentDirections.actionTalismansFragmentToTalismanDetailFragment(
                    talisman.id
                )
            )
        }
    }

    private fun showErrorDialog(error: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.error)
            .setMessage(error)
            .setPositiveButton(R.string.action_ok, null)
            .setNegativeButton(R.string.action_retry) { dialog, witch ->
                talismanViewModel.getTalismans()
            }
            .show()
    }
}