package unipd.esp2526.Simon.viewModel

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel

/**
 * ViewModel used to manage the application's language.
 *
 * This class is responsible to handling the current language preference
 * and toggle between supported languages (English and Italian).
 */
class LanguageSwitcher : ViewModel()
{
    /**
     * Class member used to store the current language code for the application.
     *
     * It attempts to retrieve the current application locale,
     * extracting the language code from the first locale in the list (index 0).
     * Defaulting in English if no locale is set or extraction fails.
     */
    var currentLanguage by mutableStateOf( AppCompatDelegate.getApplicationLocales().get(0)?.language ?: "en" )
        private set

    /**
     * Toggles the application language between English and Italian.
     * It may cause the activity to be recreated with the new strings.
     *
     * @param context The application or activity context. Required for locale operations.
     */
    fun toggleLanguage(context: Context)
    {
        val nextLanguage = if (currentLanguage == "it") "en" else "it"

        val appLocale = LocaleListCompat.forLanguageTags(nextLanguage)
        AppCompatDelegate.setApplicationLocales(appLocale)

        currentLanguage = nextLanguage
    }
}
