package com.customizeitlater.openeducation.data.datastore

import android.util.Base64
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefsDataManager @Inject constructor(
    private val store: DataStore<Preferences>
) {
    private object PrefKeys {
        val JWT_LOGIN = stringPreferencesKey("jwt")
        val JWT_REFRESH = stringPreferencesKey("refreshJwt")
        val FIRST_LOGIN = booleanPreferencesKey("firstLogin")

    }

    // ✅ Suspend function to get the latest JWT
    suspend fun getJwt(): ByteArray? {
        val base64String = store.data.first()[PrefKeys.JWT_LOGIN]
        return base64String?.let {
            Base64.decode(it, Base64.DEFAULT)
        }
    }

    // ✅ Suspend function to save JWT
    suspend fun saveJwt(jwt: ByteArray) {
        val base64String = Base64.encodeToString(jwt, Base64.DEFAULT)
        store.edit { prefs ->
            prefs[PrefKeys.JWT_LOGIN] = base64String
        }
    }


    suspend fun getRefreshJwt(): ByteArray? {
        val base64String = store.data.first()[PrefKeys.JWT_REFRESH]
        return base64String?.let {
            Base64.decode(it, Base64.DEFAULT)
        }
    }

    suspend fun saveRefreshJwt(jwt: ByteArray) {
        val base64String = Base64.encodeToString(jwt, Base64.DEFAULT)
        store.edit { prefs ->
            prefs[PrefKeys.JWT_REFRESH] = base64String
        }
    }

    suspend fun saveFirstLogin(firstLogin: Boolean) {
        store.edit { prefs ->
            prefs[PrefKeys.FIRST_LOGIN] = firstLogin
        }
    }

    suspend fun getFirstLogin(): Boolean {
        val prefs = store.data.first()
        return prefs[PrefKeys.FIRST_LOGIN] != false
    }

}









