package com.customizeitlater.openeducation.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import javax.inject.Inject
import javax.inject.Singleton
import android.util.Base64
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


@Singleton
class PrefsDataManager @Inject constructor(
   val store : DataStore<Preferences>
){
    private object PrefKeys{
        val JWT= stringPreferencesKey("jwt")
    }

    val jwt : Flow<ByteArray?> = store.data.map { it->
        val jwtString=it[PrefKeys.JWT]
        jwtString?.let{
            Base64.decode(it, Base64.DEFAULT)
        }
    }
    suspend fun saveJwt(jwt: ByteArray){
        val base64String = Base64.encodeToString(jwt, Base64.DEFAULT)
        store.edit {  prefs ->
            prefs[PrefKeys.JWT] = base64String }

    }









}