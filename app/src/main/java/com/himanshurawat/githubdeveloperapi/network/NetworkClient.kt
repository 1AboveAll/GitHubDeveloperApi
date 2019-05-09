package com.himanshurawat.githubdeveloperapi.network

import android.content.Context
import com.himanshurawat.githubdeveloperapi.utils.cacheSizeConverter
import com.himanshurawat.githubdeveloperapi.utils.hasNetwork
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
                            request = if (hasNetwork(context))
                                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                            else
                                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                            chain.proceed(request)
                        }
                        .build()

                INSTANCE = Retrofit.
                        Builder().
                        baseUrl("url").
                        addConverterFactory(GsonConverterFactory.create()).
                        client(okHttpClient).build()
            }

            return INSTANCE as Retrofit
        }
    }
}