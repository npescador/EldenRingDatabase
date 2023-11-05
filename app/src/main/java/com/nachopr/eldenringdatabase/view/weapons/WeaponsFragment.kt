package com.nachopr.eldenringdatabase.view.weapons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nachopr.eldenringdatabase.R
import com.nachopr.eldenringdatabase.common.ResourceState
import com.nachopr.eldenringdatabase.databinding.FragmentWeaponsBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class WeaponsFragment : Fragment() {

    private val binding: FragmentWeaponsBinding by lazy {
        FragmentWeaponsBinding.inflate(layoutInflater)
    }

    private val weaponAdapter = WeaponAdapter()
    private val weaponViewModel: WeaponViewModel by activityViewModel()

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

        weaponViewModel.getWeapons()
    }

    private fun initViewModel() {
        weaponViewModel.getWeaponLiveData().observe(viewLifecycleOwner) { state ->
            handleWeaponsListState(state)
        }
    }

    private fun handleWeaponsListState(state: WeaponListState) {
        when (state) {
            is ResourceState.Loading -> {
                binding.pbWeaponList.visibility = View.VISIBLE
            }

            is ResourceState.Success -> {
                binding.pbWeaponList.visibility = View.GONE
                weaponAdapter.submitWeaponList(state.result)
            }

            is ResourceState.Error -> {
                binding.pbWeaponList.visibility = View.GONE
                showErrorDialog(state.error)
            }
        }
    }

    private fun initUI() {
        binding.rvWeaponsList.adapter = weaponAdapter
        binding.rvWeaponsList.layoutManager = LinearLayoutManager(requireContext())

        weaponAdapter.onClickListener = { weapon ->
            findNavController().navigate(
                WeaponsFragmentDirections.actionNavigationWeaponsToWeaponDetailFragment(
                    weapon.id
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
                weaponViewModel.getWeapons()
            }
            .show()
    }
}