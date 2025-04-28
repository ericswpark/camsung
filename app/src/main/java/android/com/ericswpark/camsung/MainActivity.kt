package android.com.ericswpark.camsung

import android.com.ericswpark.camsung.faq.FAQActivity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.google.android.material.switchmaterial.SwitchMaterial


class MainActivity : AppCompatActivity() {
    private val sharedPrefKey = "android.com.ericswpark.camsung.PREFERENCES"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initial update views
        updateToggle()
        updateBootToggle()

        if (!isPermissionGranted()) {
            showPermissionDialog()
        }

        handleIntents(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_faq -> launchFAQ()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun launchFAQ() {
        val intent = Intent(this, FAQActivity::class.java)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun handleIntents(intent: Intent) {
        val data = intent.data?.toString()

        when (data) {
            "app://mute" -> {
                muteCamera()
            }
            "app://unmute" -> {
                unmuteCamera()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun toggleMuteClicked(button: View) {
        assert(button.id == R.id.main_activity_switch)

        if (!isPermissionGranted()) {
            showPermissionDialog()
            updateToggle()
            return
        }

        if (CameraHelper.isCameraMuted(contentResolver)) {
            unmuteCamera()
        } else {
            muteCamera()
        }
    }

    private fun updateToggle() {
        val switch = findViewById<SwitchMaterial>(R.id.main_activity_switch)
        switch.isChecked = CameraHelper.isCameraMuted(contentResolver)
    }

    private fun updateBootToggle() {
        val bootLockButton = findViewById<ImageView>(R.id.main_activity_boot_lock)
        if (isBootEnabled()) {
            bootLockButton.setImageResource(R.drawable.ic_baseline_lock_24)
        } else {
            bootLockButton.setImageResource(R.drawable.ic_baseline_lock_open_24)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun muteCamera() {
        if (!isPermissionGranted()) {
            Toast.makeText(this, R.string.error_no_permissions, Toast.LENGTH_SHORT).show()
            return
        }

        CameraHelper.setCameraMute(contentResolver)
        Toast.makeText(this, R.string.main_activity_mute_enabled, Toast.LENGTH_SHORT).show()
        updateToggle()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun unmuteCamera() {
        if (!isPermissionGranted()) {
            Toast.makeText(this, R.string.error_no_permissions, Toast.LENGTH_SHORT).show()
            return
        }

        CameraHelper.setCameraUnmute(contentResolver)
        Toast.makeText(this, R.string.main_activity_mute_disabled, Toast.LENGTH_SHORT).show()
        updateToggle()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun bootLockClicked(button: View) {
        assert(button.id == R.id.main_activity_boot_lock)

        if (!isPermissionGranted()) {
            showPermissionDialog()
            return
        }

        val sharedPref = getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)

        if (!isBootEnabled())  {
            with(sharedPref.edit()) {
                putInt("start_at_boot", 1)
                apply()
            }
            Toast.makeText(this, R.string.main_activity_boot_enabled, Toast.LENGTH_SHORT).show()
        } else {
            with(sharedPref.edit()) {
                putInt("start_at_boot", 0)
                apply()
            }
            Toast.makeText(this, R.string.main_activity_boot_disabled, Toast.LENGTH_SHORT).show()
        }

        updateBootToggle()
    }

    private fun isBootEnabled(): Boolean {
        val sharedPref = getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)
        return sharedPref.getInt("start_at_boot", 0) == 1
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isPermissionGranted(): Boolean {
        return Settings.System.canWrite(applicationContext)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showPermissionDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
            .setTitle(R.string.main_activity_write_settings_permission_title)
            .setMessage(R.string.main_activity_write_settings_permission_message)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                    "package:$packageName".toUri())
                startActivity(intent)
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.cancel() }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

}