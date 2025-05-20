package com.customizeitlater.openeducation.data.datastore

import com.customizeitlater.openeducation.data.crypto.TokenCryptoHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
object TokenProvider{


    @Provides
  suspend  fun getToken( tokenCryptoHelper: TokenCryptoHelper,prefsDataManager: PrefsDataManager): String {
        val tokenCipher=prefsDataManager.getJwt()
        val token=tokenCryptoHelper.decrypt(tokenCipher)
        return token
    }



}