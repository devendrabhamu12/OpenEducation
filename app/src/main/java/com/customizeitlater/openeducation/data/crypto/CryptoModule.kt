package com.customizeitlater.openeducation.data.crypto

import android.content.Context
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.RegistryConfiguration
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.aead.AeadKeyTemplates
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@InstallIn(SingletonComponent::class)
@Module
object Crypto {


    init {
        AeadConfig.register()
    }

    @Provides
    @Singleton
    fun provideKeysHandle(@ApplicationContext context: Context): KeysetHandle{
        return AndroidKeysetManager.Builder()
            .withSharedPref(context,"tinkKeySet","Masterkey")
            .withKeyTemplate(AeadKeyTemplates.AES256_GCM)
            .withMasterKeyUri("android-keystore://MasterKey")
            .build()
            .keysetHandle
    }


    @Provides
    @Singleton
    fun provideAEAD(keySetHandle:KeysetHandle): Aead{
        return keySetHandle.getPrimitive(RegistryConfiguration.get(), Aead::class.java)
    }
}