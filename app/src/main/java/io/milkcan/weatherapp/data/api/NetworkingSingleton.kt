package io.milkcan.weatherapp.data.api

import io.milkcan.weatherapp.BuildConfig
import io.milkcan.weatherapp.ui.activity.MainActivity
import io.milkcan.weatherapp.util.Settings.BASE_URL
import okhttp3.Cache
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

        val cacheSize: Long = 10 * 1024 * 1024
        val cache = Cache(MainActivity.appCacheDir, cacheSize)
        httpClient.cache(cache)

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(MainActivity.gson))
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