package base.ext

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val DEFAULT_SP_NAME = "DEFAULT_SP"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DEFAULT_SP_NAME)

suspend fun <T> DataStore<Preferences>.get(key: Preferences.Key<T>, defValue: T): T =
    data.map { it[key] }.first() ?: defValue