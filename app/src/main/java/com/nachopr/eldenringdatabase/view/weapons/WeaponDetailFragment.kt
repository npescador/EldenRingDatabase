package com.nachopr.eldenringdatabase.view.weapons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nachopr.eldenringdatabase.R
import com.nachopr.eldenringdatabase.common.ResourceState
import com.nachopr.eldenringdatabase.databinding.FragmentWeaponDetailBinding
import com.nachopr.eldenringdatabase.model.remote.Weapon
import com.nachopr.eldenringdatabase.view.talismans.TalismanDetailFragmentArgs


@Suppress("CAST_NEVER_SUCCEEDS")
class WeaponDetailFragment : Fragment() {

    private val binding: FragmentWeaponDetailBinding by lazy {
        FragmentWeaponDetailBinding.inflate(layoutInflater)
    }

    private val weaponViewModel: WeaponViewModel by activityViewModels()
    private val args: WeaponDetailFragmentArgs by navArgs()

    enum class WeaponAttribute {
        PHY,
        MAG,
        LIGT,
        HOLY,
        FIRE,
        CRIT,
        BOOST
    }

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

            val linearLayout = LinearLayout(binding.root.context)
            linearLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            linearLayout.orientation = LinearLayout.HORIZONTAL


            val attributeKeyTextView = TextView(binding.root.context)
            attributeKeyTextView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            attributeKeyTextView.setTextAppearance(R.style.WeaponAttributes)
            attributeKeyTextView.text = attribute.name
            attributeKeyTextView.setTextColor(ContextCompat.getColor(requireContext(),
                when(attribute.name as WeaponAttribute){
                    WeaponAttribute.PHY -> R.color.phy
                    WeaponAttribute.MAG -> R.color.mag
                    WeaponAttribute.LIGT -> R.color.ligt
                    WeaponAttribute.HOLY -> R.color.holy
                    WeaponAttribute.FIRE -> R.color.fire
                    WeaponAttribute.CRIT -> R.color.crit
                    WeaponAttribute.BOOST -> R.color.boost
                })
            )

            linearLayout.addView(attributeKeyTextView)

            val attributeValueTextView = TextView(binding.root.context)
            attributeValueTextView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            attributeValueTextView.setTextAppearance(R.style.WeaponAttributes)
            attributeValueTextView.text = attribute.amount.toString()
            attributeValueTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            binding.llAttackAttributes.addView(attributeKeyTextView)
        }

        weapon.defence.forEach { attribute ->

            val linearLayout = LinearLayout(binding.root.context)
            linearLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            linearLayout.orientation = LinearLayout.HORIZONTAL


            val attributeKeyTextView = TextView(binding.root.context)
            attributeKeyTextView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            attributeKeyTextView.setTextAppearance(R.style.WeaponAttributes)
            attributeKeyTextView.text = attribute.name
            attributeKeyTextView.setTextColor(ContextCompat.getColor(requireContext(),
                when(attribute.name as WeaponAttribute){
                    WeaponAttribute.PHY -> R.color.phy
                    WeaponAttribute.MAG -> R.color.mag
                    WeaponAttribute.LIGT -> R.color.ligt
                    WeaponAttribute.HOLY -> R.color.holy
                    WeaponAttribute.FIRE -> R.color.fire
                    WeaponAttribute.CRIT -> R.color.crit
                    WeaponAttribute.BOOST -> R.color.boost
                })
            )

            linearLayout.addView(attributeKeyTextView)

            val attributeValueTextView = TextView(binding.root.context)
            attributeValueTextView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            attributeValueTextView.setTextAppearance(R.style.WeaponAttributes)
            attributeValueTextView.text = attribute.amount.toString()
            attributeValueTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            binding.llAttackAttributes.addView(attributeKeyTextView)
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