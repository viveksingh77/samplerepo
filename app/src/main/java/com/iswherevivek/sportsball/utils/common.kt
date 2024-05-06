package com.iswherevivek.sportsball.utils

import androidx.compose.material3.SnackbarDuration

sealed class SnackBarEvent{
    data class ShowSnackBar(
        val message:String,
        val duration: SnackbarDuration = SnackbarDuration.Short
    ):SnackBarEvent()
    data object NavigateUp:SnackBarEvent()
}