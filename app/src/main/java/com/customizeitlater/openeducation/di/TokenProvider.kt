package com.customizeitlater.openeducation.di

import com.customizeitlater.openeducation.di.TokenCryptoHelper
import com.customizeitlater.openeducation.data.datastore.PrefsDataManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TokenProvider{


    @Provides
  suspend  fun getToken(tokenCryptoHelper: TokenCryptoHelper, prefsDataManager: PrefsDataManager): String {
        val tokenCipher=prefsDataManager.getJwt()
        val token=tokenCryptoHelper.decrypt(tokenCipher)
        return token
    }



}