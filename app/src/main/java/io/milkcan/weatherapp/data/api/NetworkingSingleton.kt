package io.milkcan.weatherapp.data.api

import io.milkcan.weatherapp.App
import io.milkcan.weatherapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkingSingleton private constructor() {
    companion object {
        private var networkingSingleton: NetworkingSingleton? = null

        val instance: NetworkingSingleton
            @Synchronized get() {
                if (networkingSingleton == null) {
                    networkingSingleton = NetworkingSingleton()
                }

                return networkingSingleton!!
            }
    }

    private val retrofit: Retrofit

    init {
        val httpClient = OkHttpClient.Builder()

        // Display network requests in log (debug only)
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            val loggingInterceptor =
                httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                }
            httpClient.addInterceptor(loggingInterceptor)
        }

        retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(App.gson))
            .client(httpClient.build())
            .build()
    }

    /**
     * Creates a Retrofit service with the provided [serviceClass] API interface.
     *
     * @param serviceClass The API service to create.
     * @return An API service created with [NetworkingSingleton]'s [retrofit] instance.
     */
    fun <S> createService(serviceClass: Class<S>): S = retrofit.create(serviceClass)
}