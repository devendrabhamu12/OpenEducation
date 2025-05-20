package com.customizeitlater.openeducation.data.network

import com.customizeitlater.openeducation.data.datastore.TokenProvider
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton




class NetworkComponent @Inject constructor(){
@Singleton
@Provides
fun provideOkHttpClient(tokenProvider: TokenProvider){

}

}