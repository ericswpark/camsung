package android.com.ericswpark.camsung.faq

import android.com.ericswpark.camsung.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class FAQActivity : AppCompatActivity() {
    val faqList = arrayListOf(
        FAQ(getString(R.string.faq_activity_phone_mute_title),
            getString(R.string.faq_activity_phone_mute_description)),
        FAQ(getString(R.string.faq_activity_samsung_update_title),
            getString(R.string.faq_activity_samsung_update_description)),
        FAQ(getString(R.string.faq_activity_android_warning_title),
            getString(R.string.faq_activity_android_warning_description))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        setTitle(R.string.main_activity_action_faq)

        val recyclerView = findViewById<RecyclerView>(R.id.faq_recycler_view)
        val faqAdapter = FAQAdapter(faqList)
        recyclerView.adapter = faqAdapter
    }
}