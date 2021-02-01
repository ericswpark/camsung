package android.com.ericswpark.camsung

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi


class BootUpReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent?) {
        val sharedPref = context.getSharedPreferences("android.com.ericswpark.camsung.PREFERENCES",
            Context.MODE_PRIVATE)
        val startBootEnabled = (sharedPref.getInt("start_at_boot", 0) == 1)

        if (startBootEnabled) {
            if (Settings.System.canWrite(context)) {
                CameraHelper.setCameraMute(context.contentResolver)
                Toast.makeText(context, R.string.main_activity_mute_enabled, Toast.LENGTH_SHORT).show()
            }
            Toast.makeText(context, R.string.boot_error_no_permissions, Toast.LENGTH_SHORT).show()
        }
    }
}