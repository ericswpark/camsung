package android.com.ericswpark.camsung

import android.content.ContentResolver
import android.provider.Settings

class CameraHelper {
    companion object {
        val propString = "csc_pref_camera_forced_shuttersound_key"

        fun isCameraMuted(contentResolver: ContentResolver): Boolean {
            val propValue = Settings.System.getInt(contentResolver, propString)
            return propValue == 0
        }

        fun setCameraMute(contentResolver: ContentResolver) {
            Settings.System.putInt(contentResolver, propString, 0)
        }

        fun setCameraUnmute(contentResolver: ContentResolver) {
            Settings.System.putInt(contentResolver, propString, 1)
        }
    }
}