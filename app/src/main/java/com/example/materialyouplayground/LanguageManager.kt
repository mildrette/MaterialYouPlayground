import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.materialyouplayground.ui.theme.AppTheme

object SharedPrefsHelper {

    private const val PREF_NAME = "app_prefs"
    private const val KEY_THEME = "theme_mode"
    private const val KEY_LANGUAGE = "user_language"

    fun saveTheme(context: Context, theme: AppTheme) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_THEME, theme.name).apply()
    }

    fun getTheme(context: Context): AppTheme {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val saved = prefs.getString(KEY_THEME, AppTheme.SYSTEM.name)
        return AppTheme.valueOf(saved!!)
    }

    fun saveLanguage(context: Context, langCode: String?) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LANGUAGE, langCode).apply()

        if (langCode == null) {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.getEmptyLocaleList()
            )
        } else {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(langCode)
            )
        }
    }

    fun getLanguage(context: Context): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LANGUAGE, null)
    }

    fun applySavedLanguage(context: Context) {
        val lang = getLanguage(context)

        if (lang == null) {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.getEmptyLocaleList()
            )
        } else {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(lang)
            )
        }
    }
}
