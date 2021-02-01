package android.com.ericswpark.camsung

import android.com.ericswpark.camsung.FAQActivity.FAQActivity
import android.content.Context
import android.content.Intent
import android.os.*
import android.os.storage.StorageManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.concurrent.thread
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keepWipeFileCheckBox = findViewById<CheckBox>(R.id.main_activity_do_not_delete_wipe_file_checkbox)
        keepWipeFileCheckBox.setOnLongClickListener {
            explainKeepWipeFile()
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_faq -> launchFAQ()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun launchFAQ() {
        val intent = Intent(this, FAQActivity::class.java)
        startActivity(intent)
    }

    fun keepScreenOnSwitchClicked(v: View) {
        val switch = findViewById<Switch>(R.id.main_activity_keep_screen_on_switch)

        if (switch.isChecked)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        else
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    fun startWipeClicked(v: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
            .setTitle(R.string.main_activity_start_confirm_title)
            .setMessage(R.string.main_activity_start_confirm_message)
            .setPositiveButton(android.R.string.ok) { _, _ -> wipeRoutine(v) }
            .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.cancel() }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    fun explainKeepWipeFile() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
            .setTitle(R.string.main_activity_keep_wipe_file_explanation_title)
            .setMessage(R.string.main_activity_keep_wipe_file_explanation_message)
            .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.cancel() }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun wipeRoutine(v: View) {
        // On start, disable button
        val startButton = findViewById<Button>(R.id.main_activity_start_button)
        startButton.isEnabled = false

        // Enable progress bars
        val wipeProgressBar = findViewById<ProgressBar>(R.id.main_activity_wipe_progress_bar)
        val wipeProgressText = findViewById<TextView>(R.id.main_activity_wipe_progress_text)
        wipeProgressBar.visibility = View.VISIBLE
        wipeProgressText.visibility = View.VISIBLE

        Snackbar.make(v, R.string.main_activity_wipe_started, Snackbar.LENGTH_SHORT).show()
        vibratePhone(v.context)

        val doNotDeleteFileCheckBox = findViewById<CheckBox>(R.id.main_activity_do_not_delete_wipe_file_checkbox)
        thread {
            wipe(!doNotDeleteFileCheckBox.isChecked)

            Snackbar.make(v, R.string.main_activity_wipe_finished, Snackbar.LENGTH_SHORT).show()
            vibratePhone(v.context)

            // On end, enable button
            runOnUiThread {
                startButton.isEnabled = true
            }
        }
    }

    private fun wipe(deleteFile: Boolean) {
        // Query free space
        val availableBytes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val storageManager = applicationContext.getSystemService(
                StorageManager::class.java
            )
            val appSpecificInternalDirUuid: UUID = storageManager.getUuidForPath(filesDir)
            storageManager.getAllocatableBytes(appSpecificInternalDirUuid)
        } else {
            val stat = StatFs(Environment.getExternalStorageDirectory().getPath())
            stat.blockSize.toLong() * stat.blockCount.toLong()
        }

        Log.d("MainActivity", "Available bytes: $availableBytes")
        Log.d("MainActivity", "Available MB: " + availableBytes / 1024 / 1024)

        val file = File(applicationContext.filesDir, "wipeFile")
        file.createNewFile()

        if (file.exists()) {
            val fo = FileOutputStream(file, true)

            var byteCount: Long = 0

            while (true) {
                try {
                    val randomBytes = Random.nextBytes(1024 * 1024)
                    fo.write(randomBytes)
                    fo.flush()
                    byteCount += 1024 * 1024
                    runOnUiThread {
                        updateWipeProgress(byteCount, availableBytes)
                    }
                } catch (e: Exception) {
                    // We ran out of space!
                    break
                }
            }

            fo.close()
        }

        if (deleteFile) {
            file.delete()
        }
    }

    private fun updateWipeProgress(currentBytes: Long, totalBytes: Long) {
        val progressBar = findViewById<ProgressBar>(R.id.main_activity_wipe_progress_bar)
        val progressText = findViewById<TextView>(R.id.main_activity_wipe_progress_text)

        val percentage: Double = currentBytes.toDouble() / totalBytes * 100

        if (percentage.toInt() <= 100) {
            progressBar.progress = percentage.toInt()
            progressText.text = String.format(
                    "%d/%d, %.2f%%",
                    currentBytes,
                    totalBytes,
                    currentBytes.toDouble() / totalBytes * 100
            )
        } else {
            // Overshoot warning
            progressBar.progress = 100
            progressText.text = String.format(
                    "%d/%d, 100%%%n%s",
                    currentBytes,
                    totalBytes,
                    getString(R.string.main_activity_overshoot_warning)
            )
        }
    }

    // From https://stackoverflow.com/a/45605249
    private fun vibratePhone(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            (context.getSystemService(VIBRATOR_SERVICE) as Vibrator)
                .vibrate(
                    VibrationEffect
                        .createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE)
                )
        } else {
            (context.getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(150)
        }
    }
}