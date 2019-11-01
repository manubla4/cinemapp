package com.diegomedina.notesapp.mocks

import android.content.Context
import com.diegomedina.notesapp.data.helper.networking.NetworkingManager

class NetworkingManagerMock(context: Context) : NetworkingManager(context) {

    var networkingAvailable = true

    override fun isNetworkOnline() = networkingAvailable

}