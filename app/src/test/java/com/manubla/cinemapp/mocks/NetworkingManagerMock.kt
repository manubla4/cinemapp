package com.manubla.cinemapp.mocks

import android.content.Context
import com.manubla.cinemapp.data.helper.networking.NetworkingManager

class NetworkingManagerMock(context: Context) : NetworkingManager(context) {

    var networkingAvailable = true

    override fun isNetworkOnline() = networkingAvailable

}