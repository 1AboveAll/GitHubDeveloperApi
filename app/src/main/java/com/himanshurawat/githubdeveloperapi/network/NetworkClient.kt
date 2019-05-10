package com.himanshurawat.githubdeveloperapi.network

import android.content.Context
import com.himanshurawat.githubdeveloperapi.utils.Constants
import com.himanshurawat.githubdeveloperapi.utils.cacheSizeConverter
import com.himanshurawat.githubdeveloperapi.utils.isNetworkAvailable
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {


    companion object {

        private var INSTANCE: Retrofit? = null

        fun getRetrofit(context: Context): Retrofit {
            if(INSTANCE == null){

                //5MB Cache
                val cacheSize = cacheSizeConverter(5)
                val cache = Cache(context.cacheDir,cacheSize)

                val okHttpClient = OkHttpClient.Builder()
                        .cache(cache)
                        .addInterceptor { chain ->
                            var request = chain.request()
                            request = if (isNetworkAvailable(context))
                                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                            else
                                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                            chain.proceed(request)
                        }
                        .build()

                INSTANCE = Retrofit.
                        Builder().
                        baseUrl(Constants.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create()).
                        addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                        client(okHttpClient).build()
            }

            return INSTANCE as Retrofit
        }
    }
}