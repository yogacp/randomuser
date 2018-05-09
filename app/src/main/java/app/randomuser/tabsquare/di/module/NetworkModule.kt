package app.randomuser.tabsquare.di.module

import app.randomuser.tabsquare.RandomUserApp
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(val randomUserApp: RandomUserApp) {
    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val httpCacheDirectory = File(randomUserApp.cacheDir, "httpCache")
        val cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    try {
                        chain.proceed(chain.request())
                    } catch (e: Exception) {
                        val offlineRequest = chain.request().newBuilder()
                                .header("Cache-Control", "public, only-if-cached," +
                                        "max-stale=" + 60 * 60 * 24)
                                .build()
                        chain.proceed(offlineRequest)
                    }
                }
                .cache(cache)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()
    }

}