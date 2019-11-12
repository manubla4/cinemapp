package com.manubla.cinemapp

import android.content.Context
import com.manubla.cinemapp.data.repository.notes.CloudNotesDataStore
import com.manubla.cinemapp.data.repository.notes.DatabaseNotesDataStore
import com.manubla.cinemapp.mocks.NetworkingManagerMock
import com.manubla.cinemapp.mocks.NoteDaoMock
import com.manubla.cinemapp.mocks.NotesDataStoreFactoryMock
import com.manubla.cinemapp.mocks.NotesServiceMock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NotesDataTest {

    @Mock
    private lateinit var mockContext: Context

    lateinit var noteServiceMock: NotesServiceMock
    lateinit var noteDaoMock: NoteDaoMock
    lateinit var networkingManagerMock: NetworkingManagerMock
    lateinit var notesDataStoreFactoryMock: NotesDataStoreFactoryMock

    @Before
    fun beforeTest() {
        noteServiceMock = NotesServiceMock()
        noteDaoMock = NoteDaoMock()
        networkingManagerMock = NetworkingManagerMock(mockContext)
        notesDataStoreFactoryMock =
            NotesDataStoreFactoryMock(noteServiceMock, noteDaoMock, networkingManagerMock)
    }

    @After
    fun afterTest() {

    }


    @Test
    fun testCloudSourceDataRetrieving() {
        networkingManagerMock.networkingAvailable = true

        assert(notesDataStoreFactoryMock.notesDataStoreFactory is CloudNotesDataStore)
    }

    @Test
    fun testDatabaseSourceDataRetrieving() {
        networkingManagerMock.networkingAvailable = false

        assert(notesDataStoreFactoryMock.notesDataStoreFactory is DatabaseNotesDataStore)
    }

    @Test
    fun testNetworking() {
        assert(networkingManagerMock.isNetworkOnline())
    }

    @Test
    fun testSourceData() {

    }

}
