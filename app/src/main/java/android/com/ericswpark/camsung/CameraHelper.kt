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
            } catch (_: Settings.SettingNotFoundException) {
                false
            }
        }

        fun setCameraMute(contentResolver: ContentResolver) {
            cameraSetting(contentResolver, 0)
        }

        fun setCameraUnmute(contentResolver: ContentResolver) {
            cameraSetting(contentResolver, 1)
        }

        private fun cameraSetting(contentResolver: ContentResolver, value: Int) {
            Settings.System.putInt(contentResolver, PROP_STRING, value)
        }
    }
}