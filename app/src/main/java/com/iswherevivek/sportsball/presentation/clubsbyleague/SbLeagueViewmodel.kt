package com.iswherevivek.sportsball.presentation.clubsbyleague

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswherevivek.sportsball.domain.model.ClubList
import com.iswherevivek.sportsball.domain.repository.SportRepository
import com.iswherevivek.sportsball.utils.SnackBarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SbLeagueViewmodel @Inject constructor(
    private val sportRepository: SportRepository
) : ViewModel() {

    private val _state = mutableStateOf(SbState())
    val state: State<SbState> = _state

    private val _snackBarEventFlow = MutableSharedFlow<SnackBarEvent>()
    val snackBarEventFlow = _snackBarEventFlow.asSharedFlow()

    fun onEvent(event: SbEvent) {
        when (event) {
            SbEvent.SearchClubs -> searchClubs()
            is SbEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchquery = event.searchquery)
            }

            SbEvent.StoreToDb -> storeToDb()
        }
    }

    private fun searchClubs() {
        viewModelScope.launch {
            try {
                sportRepository.searchClubs(
                    searchquery = state.value.searchquery
                ).collect{
                    _state.value = state.value.copy(clubList = it.teams)
                    Log.d("CLUBLIST", "search: ${_state.value.clubList.toString()}")
                }

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

    private fun storeToDb(){
        viewModelScope.launch {
            try {
                Log.d("ROOMDB", "storeToDb:${state.value.clubList} ")
                val clubs = state.value.clubList
                sportRepository.upsertClubList(ClubList(teams = clubs!!))

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