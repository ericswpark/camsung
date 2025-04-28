package android.com.ericswpark.camsung

import android.content.ContentResolver
import android.provider.Settings

class CameraHelper {
    companion object {
        @Suppress("SpellCheckingInspection")
        private const val PROP_STRING = "csc_pref_camera_forced_shuttersound_key"

        fun isCameraMuted(contentResolver: ContentResolver): Boolean {
            return try {
                val propValue = Settings.System.getInt(contentResolver, PROP_STRING)
                propValue == 0
            } catch (e: Settings.SettingNotFoundException) {
                false
            }
        }

        fun setCameraMute(contentResolver: ContentResolver) {
            Settings.System.putInt(contentResolver, PROP_STRING, 0)
        }

        fun setCameraUnmute(contentResolver: ContentResolver) {
            Settings.System.putInt(contentResolver, PROP_STRING, 1)
        }
    }
}