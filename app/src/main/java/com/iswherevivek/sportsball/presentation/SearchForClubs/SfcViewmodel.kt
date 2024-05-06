package com.iswherevivek.sportsball.presentation.SearchForClubs

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswherevivek.sportsball.domain.repository.SportRepository
import com.iswherevivek.sportsball.utils.SnackBarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SfcViewmodel @Inject constructor(
    private val sportRepository: SportRepository
):ViewModel() {

   private val _state = mutableStateOf(SfcState())
    val state: State<SfcState> = _state

    private val _snackBarEventFlow = MutableSharedFlow<SnackBarEvent>()
    val snackBarEventFlow = _snackBarEventFlow.asSharedFlow()

    fun onEvent(event: SfcEvent){
        when(event){
            SfcEvent.SearchClub -> searchClub()
            is SfcEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchquery = event.searchQuery)
            }
        }
    }


    private fun searchClub() {
        viewModelScope.launch {
            try {
               val team = sportRepository.getClub(state.value.searchquery)
                _state.value = state.value.copy(club = team.teams[0])
                _snackBarEventFlow.emit(
                    SnackBarEvent.ShowSnackBar(
                        message = "Fetch Successfully.",
                        duration = SnackbarDuration.Long
                    )
                )
            } catch (e: Exception) {
                _snackBarEventFlow.emit(
                    SnackBarEvent.ShowSnackBar(
                        message = "Couldn't Load clubs ${e.message}",
                        duration = SnackbarDuration.Long
                    )
                )
            }

        }

    }

}