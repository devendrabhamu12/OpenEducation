package com.customizeitlater.openeducation.di

import com.google.crypto.tink.Aead
import javax.inject.Inject

class TokenCryptoHelper @Inject constructor(
   private val aead: Aead
){

    fun encrypt(data: String): ByteArray{
       return aead.encrypt(data.toByteArray(),null)
    }

    fun decrypt(cipher: ByteArray?): String{
        return aead.decrypt(cipher,null).decodeToString()
    }
}