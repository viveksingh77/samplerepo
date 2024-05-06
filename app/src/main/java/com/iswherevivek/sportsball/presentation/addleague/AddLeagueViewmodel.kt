package com.iswherevivek.sportsball.presentation.addleague

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswherevivek.sportsball.domain.model.ClubList
import com.iswherevivek.sportsball.domain.repository.SportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddLeagueViewmodel @Inject constructor(
    private val sportRepository: SportRepository
):ViewModel() {

    private val _state = mutableStateOf(AddState())
    val state: State<AddState> =_state

    init {
        showData()
    }

    private fun onEvent(event: AddEvent){
        when(event){
            AddEvent.ShowRoomData -> showData()
        }
    }

    private fun showData() {

       viewModelScope.launch {
           sportRepository.getClubListFromRoom()
               .collectLatest {
                   _state.value = state.value.copy(teams = it)
               }
       }
    }
}