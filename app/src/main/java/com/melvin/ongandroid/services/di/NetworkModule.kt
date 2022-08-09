package com.melvin.ongandroid.services.di

import android.content.Context
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.GsonBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.melvin.ongandroid.services.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//objet uses the library retrofit for call the APIService in module for dependency injection
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    //get retrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("https://ongapi.alkemy.org/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    //get ApiClient
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideFirebaseAnalytics(@ApplicationContext context: Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }

    @Singleton
    @Provides
    fun providerFacebookAuth(): LoginManager {
        return LoginManager.getInstance();
    }

    @Singleton
    @Provides
    fun providerFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}