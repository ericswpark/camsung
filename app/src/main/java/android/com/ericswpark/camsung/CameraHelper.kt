package android.com.ericswpark.camsung

import android.content.ContentResolver
import android.provider.Settings

class CameraHelper {
    companion object {
        private const val propString = "csc_pref_camera_forced_shuttersound_key"

        fun isCameraMuted(contentResolver: ContentResolver): Boolean {
            return try {
                val propValue = Settings.System.getInt(contentResolver, propString)
                propValue == 0
            } catch (e: Settings.SettingNotFoundException) {
                false
            }
        }

        fun setCameraMute(contentResolver: ContentResolver) {
            Settings.System.putInt(contentResolver, propString, 0)
        }

        fun setCameraUnmute(contentResolver: ContentResolver) {
            Settings.System.putInt(contentResolver, propString, 1)
        }
    }
}