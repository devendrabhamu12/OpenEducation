package com.customizeitlater.openeducation.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import javax.inject.Inject
import javax.inject.Singleton
import android.util.Base64
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LifecycleCoroutineScope
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.map


class PrefsDataManager @Inject constructor(
    private val store: DataStore<Preferences>
) {
    private object PrefKeys {
        val JWT = stringPreferencesKey("jwt")
    }

    // ✅ Suspend function to get the latest JWT
    suspend fun getJwt(): ByteArray? {
        val base64String = store.data.first()[PrefKeys.JWT]
        return base64String?.let {
            Base64.decode(it, Base64.DEFAULT)
        }
    }

    // ✅ Suspend function to save JWT
    suspend fun saveJwt(jwt: ByteArray) {
        val base64String = Base64.encodeToString(jwt, Base64.DEFAULT)
        store.edit { prefs ->
            prefs[PrefKeys.JWT] = base64String
        }
    }
}









