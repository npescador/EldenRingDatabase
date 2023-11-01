package com.nachopr.eldenringdatabase.view.talismans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nachopr.eldenringdatabase.common.ResourceState
import com.nachopr.eldenringdatabase.domain.talismans.usecase.GetTalismansDetailUseCase
import com.nachopr.eldenringdatabase.domain.talismans.usecase.GetTalismansUseCase
import com.nachopr.eldenringdatabase.model.remote.Talismans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias TalismanListState = ResourceState<List<Talismans>>
typealias TalismanDetailState = ResourceState<Talismans>

class TalismanViewModel(
    private val talismansUseCase: GetTalismansUseCase,
    private val talismanDetailUseCase: GetTalismansDetailUseCase
) : ViewModel() {

    private val talismanMutableLiveData = MutableLiveData<TalismanListState>()
    private val talismanDetailMutableLiveData = MutableLiveData<TalismanDetailState>()

    fun getTalismanLiveData(): LiveData<TalismanListState> {
        return talismanMutableLiveData
    }

    fun getTalismanDetailLiveData(): LiveData<TalismanDetailState> {
        return talismanDetailMutableLiveData
    }

    fun getTalismans() {
        talismanMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val talismanList = talismansUseCase.getTalismans()

                withContext(Dispatchers.Main) {
                    talismanMutableLiveData.value = ResourceState.Success(talismanList)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    talismanMutableLiveData.value =
                        ResourceState.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }

    fun getTalisman(id: String) {
        talismanDetailMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val talisman = talismanDetailUseCase.getTalismanDetail(id)

                withContext(Dispatchers.Main) {
                    talismanDetailMutableLiveData.value = ResourceState.Success(talisman)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    talismanDetailMutableLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }
}