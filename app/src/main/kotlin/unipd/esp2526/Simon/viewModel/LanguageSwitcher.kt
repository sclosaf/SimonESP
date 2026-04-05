package unipd.esp2526.Simon.viewModel

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel

class LanguageSwitcher : ViewModel()
{
    var currentLanguage by mutableStateOf( AppCompatDelegate.getApplicationLocales().get(0)?.language ?: "en" )
        private set

    fun toggleLanguage(context: Context)
    {
        val nextLanguage = if (currentLanguage == "it") "en" else "it"

        val appLocale = LocaleListCompat.forLanguageTags(nextLanguage)
        AppCompatDelegate.setApplicationLocales(appLocale)

        currentLanguage = nextLanguage
    }
}
