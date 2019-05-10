package com.himanshurawat.githubdeveloperapi.utils

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.Socket

object InternetConnectivity: LiveData<Boolean>() {
    private lateinit var application: Application
    private var isReallyConnected: CheckInternetConnectivity? = null

    private var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val isNetworkAvailable = isNetworkAvailable(context)
            postValue(isNetworkAvailable)

            if (!isNetworkAvailable) { // No need to check internet connectivity
                return
            }

            if (isReallyConnected != null
                && (isReallyConnected?.status == AsyncTask.Status.RUNNING
                        || isReallyConnected?.status == AsyncTask.Status.PENDING)) {
                //Connection check is already running
                return
            }
            isReallyConnected = CheckInternetConnectivity()
            isReallyConnected?.execute()
        }
    }

    fun init(application: Application){
        this.application = application
    }

    private fun registerReceiver(){
        val filter = IntentFilter()
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        application.registerReceiver(receiver,filter)
    }

    private fun unRegisterReceiver(){
        application.unregisterReceiver(receiver)
    }


    override fun onActive() {
        super.onActive()
        registerReceiver()
    }

    override fun onInactive() {
        super.onInactive()
        unRegisterReceiver()
    }


    class CheckInternetConnectivity: AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean {
            if(isNetworkAvailable(application)){
                return try{
                    val sock = Socket()
                    sock.connect(
                        InetSocketAddress("8.8.8.8", 53),
                        5000)
                    sock.close()
                    true
                }catch (e: Exception){
                    return false
                }
            }
            return false
        }

        override fun onPostExecute(result: Boolean?) {
            postValue(result)
        }

    }
}