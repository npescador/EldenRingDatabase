package com.nachopr.eldenringdatabase.view.weapons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nachopr.eldenringdatabase.R
import com.nachopr.eldenringdatabase.common.ResourceState
import com.nachopr.eldenringdatabase.databinding.FragmentWeaponDetailBinding
import com.nachopr.eldenringdatabase.model.remote.Weapon


@Suppress("CAST_NEVER_SUCCEEDS", "PreviewAnnotationInFunctionWithParameters")
class WeaponDetailFragment : Fragment() {

    private val binding: FragmentWeaponDetailBinding by lazy {
        FragmentWeaponDetailBinding.inflate(layoutInflater)
    }

    private val weaponViewModel: WeaponViewModel by activityViewModels()
    private val args: WeaponDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        weaponViewModel.getWeapon(args.weaponId)
    }

    private fun initViewModel() {
        weaponViewModel.getWeaponDetailLiveData().observe(viewLifecycleOwner) { state ->
            handleWeaponDetailState(state)
        }
    }

    private fun handleWeaponDetailState(state: WeaponDetailState) {
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

    private fun initUI(weapon: Weapon) {
        binding.tvWeaponDetailName.text = weapon.name

        Glide.with(requireContext())
            .load(weapon.image)
            .into(binding.ivWeaponDetail)

        weapon.attack.forEach { attribute ->
            when(attribute.name.uppercase()){
                "PHY"-> binding.tvAttackAttributePhyValue.text = attribute.amount.toString()
                "MAG"-> binding.tvAttackAttributeMagValue.text = attribute.amount.toString()
                "LIGT" -> binding.tvAttackAttributeLigtValue.text = attribute.amount.toString()
                "HOLY" -> binding.tvAttackAttributeHolyValue.text = attribute.amount.toString()
                "FIRE" -> binding.tvAttackAttributeFireValue.text = attribute.amount.toString()
                "CRIT" -> binding.tvAttackAttributeCritValue.text = attribute.amount.toString()
                else -> {}
            }
        }

        weapon.defence.forEach { attribute ->
            when(attribute.name.uppercase()){
                "PHY" ->   binding.tvGuardAttributePhyValue.text = attribute.amount
                "MAG" ->   binding.tvGuardAttributeMagValue.text = attribute.amount
                "LIGT" ->  binding.tvGuardAttributeLigtValue.text = attribute.amount
                "HOLY" ->  binding.tvGuardAttributeHolyValue.text = attribute.amount
                "FIRE" ->  binding.tvGuardAttributeFireValue.text = attribute.amount
                "BOOST" -> binding.tvGuardAttributeCritValue.text = attribute.amount
                else -> {}
            }
        }
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