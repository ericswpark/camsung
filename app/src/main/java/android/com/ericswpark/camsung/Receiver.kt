package android.com.ericswpark.camsung

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi

const val ACTION_SET_CAMERA = "android.com.ericswpark.camsung.ACTION_SET_CAMERA"

class Receiver : BroadcastReceiver() {

    private fun muteCamera(context: Context) {
        CameraHelper.setCameraMute(context.contentResolver)
        Toast.makeText(context, R.string.main_activity_mute_enabled, Toast.LENGTH_SHORT).show()
    }

    private fun unmuteCamera(context: Context) {
        CameraHelper.setCameraUnmute(context.contentResolver)
        Toast.makeText(context, R.string.main_activity_mute_disabled, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent?) {
        when (intent!!.action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                val sharedPref = context.getSharedPreferences("android.com.ericswpark.camsung.PREFERENCES",
                    Context.MODE_PRIVATE)
                val startBootEnabled = (sharedPref.getInt("start_at_boot", 0) == 1)

                if (startBootEnabled) {
                    if (Settings.System.canWrite(context)) {
                        muteCamera(context)
                    } else {
                        Toast.makeText(context, R.string.error_no_permissions, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            ACTION_SET_CAMERA -> {
                val data = intent.data?.toString()

                when (data) {
                    "app://mute" -> {
                        muteCamera(context)
                    }
                    "app://unmute" -> {
                        unmuteCamera(context)
                    }
                }
            }
        }
    }
}