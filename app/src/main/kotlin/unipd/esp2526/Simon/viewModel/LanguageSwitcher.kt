package unipd.esp2526.Simon.viewModel

import android.util.Log
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
 * This class is responsible to handl the current language preference
 * and toggles between supported languages (English and Italian).
 */
class LanguageSwitcher : ViewModel()
{
    companion object
    {
        /**
         * Tag identifier used for Android logging messages.
         */
        private val TAG = LanguageSwitcher::class.java.simpleName
    }

    /**
     * Class member used to store the current language code for the application.
     *
     * It attempts to retrieve the current application locale,
     * extracting the language code from the first locale in the list (index 0).
     * If unable to get the locale, it falls back to the system default locale.
     * Defaulting in English if no locale is set or extraction fails.
     */
    var currentLanguage by mutableStateOf(getCurrentActiveLanguage())
        private set

    /**
     * Helper function used to retrieve the currently active language code.
     * Applying specifics fallbacks:
     * 1. Application locale
     * 2. System locale
     * 3. Default "En"
     *
     * @return The language code (e.g. "en", "it") of the current active locale
     */
    private fun getCurrentActiveLanguage(): String
    {
        return AppCompatDelegate.getApplicationLocales().get(0)?.language ?: java.util.Locale.getDefault().language ?: "en"
    }

    /**
     * Toggles the application language between English and Italian.
     * Additionally, causing the activity to be recreated with the new strings.
     */
    fun toggleLanguage()
    {
        Log.d(TAG, "Toggling language")

        val nextLanguage = if (currentLanguage.startsWith("it")) "en" else "it"

        Log.i(TAG, "Switching from $currentLanguage to $nextLanguage")

        val appLocale = LocaleListCompat.forLanguageTags(nextLanguage)
        AppCompatDelegate.setApplicationLocales(appLocale)

        currentLanguage = nextLanguage
    }
}
