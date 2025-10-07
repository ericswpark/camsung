package android.com.ericswpark.camsung.faq

import android.com.ericswpark.camsung.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class FAQActivity : AppCompatActivity() {

    val faqList = arrayListOf(
        FAQ(
            R.string.faq_activity_phone_mute_title, R.string.faq_activity_phone_mute_description
        ), FAQ(
            R.string.faq_activity_samsung_update_title,
            R.string.faq_activity_samsung_update_description
        ), FAQ(
            R.string.faq_activity_android_warning_title,
            R.string.faq_activity_android_warning_description
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        setTitle(R.string.main_activity_action_faq)

        val recyclerView = findViewById<RecyclerView>(R.id.faq_recycler_view)
        val faqAdapter = FAQAdapter(faqList)
        recyclerView.adapter = faqAdapter

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}