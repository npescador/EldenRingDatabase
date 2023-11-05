package com.nachopr.eldenringdatabase.view.weapons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nachopr.eldenringdatabase.common.ResourceState
import com.nachopr.eldenringdatabase.domain.weapons.usecase.GetWeaponDetailUseCase
import com.nachopr.eldenringdatabase.domain.weapons.usecase.GetWeaponsUseCase
import com.nachopr.eldenringdatabase.model.remote.Weapon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias WeaponListState = ResourceState<List<Weapon>>
typealias WeaponDetailState = ResourceState<Weapon>

class WeaponViewModel(
    private val weaponsUseCase: GetWeaponsUseCase,
    private val weaponDetailUseCase: GetWeaponDetailUseCase
) : ViewModel() {

    private val weaponMutableLiveData = MutableLiveData<WeaponListState>()
    private val weaponDetailMutableLiveData = MutableLiveData<WeaponDetailState>()

    fun getWeaponLiveData(): LiveData<WeaponListState> {
        return weaponMutableLiveData
    }

    fun getWeaponDetailLiveData(): LiveData<WeaponDetailState> {
        return weaponDetailMutableLiveData
    }

    fun getWeapons() {
        weaponMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val weaponList = weaponsUseCase.getWeapons()

                withContext(Dispatchers.Main) {
                    weaponMutableLiveData.value = ResourceState.Success(weaponList)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    weaponMutableLiveData.value =
                        ResourceState.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }

    fun getWeapon(id: String) {
        weaponDetailMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val weapon = weaponDetailUseCase.getWeaponDetail(id)

                withContext(Dispatchers.Main) {
                    weaponDetailMutableLiveData.value = ResourceState.Success(weapon)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    weaponDetailMutableLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }
}