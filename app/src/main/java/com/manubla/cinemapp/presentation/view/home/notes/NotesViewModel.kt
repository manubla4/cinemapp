package com.manubla.cinemapp.presentation.view.home.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NotesViewModel(private val repository: ReviewsSourceRepository) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val notes: LiveData<List<Note>>
        get() = localNotes
    val isLoading: LiveData<Boolean>
        get() = localIsLoading

    private val localNotes = MutableLiveData<List<Note>>()
    private val localIsLoading = MutableLiveData<Boolean>()

    fun loadNotes() {
        localIsLoading.postValue(true)
        launch(Dispatchers.IO) {
            try {
                val notes = repository.getNotes()
                localNotes.postValue(notes)
            } catch (error: Exception) {

            } finally {
                localIsLoading.postValue(false)
            }
        }
    }
}
