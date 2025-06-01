package com.customizeitlater.openeducation.di

import com.google.crypto.tink.Aead
import javax.inject.Inject

class TokenCryptoHelper @Inject constructor(
   private val aead: Aead
){

    fun encrypt(data: String="Master"): ByteArray{
       return aead.encrypt(data.toByteArray(),null)
    }
    fun decrypt(cipher: ByteArray?): String {
        val actualCipher = cipher ?: "Master".toByteArray()
        return aead.decrypt(actualCipher, null).decodeToString()
    }

}