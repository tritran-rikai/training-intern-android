package com.app.imagerandom.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefHelper @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    // User
    fun saveUser(username: String, password: String) {
        prefs.edit {
            putString("username", username)
            putString("password", password)
        }
    }

    fun getUser(): Pair<String?, String?> {
        val username = prefs.getString("username", null)
        val password = prefs.getString("password", null)
        return username to password
    }

    // Session
    fun saveSessionId(sessionId: String) {
        prefs.edit {
            putString("session_id", sessionId)
        }
    }

    fun getSessionId(): String? {
        return prefs.getString("session_id", null)
    }

    fun clearSession() {
        prefs.edit {
            remove("session_id")
        }
    }
}
