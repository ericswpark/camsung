package android.com.ericswpark.camsung.FAQActivity

import android.com.ericswpark.camsung.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class FAQActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        setTitle(R.string.main_activity_action_faq)

        val recyclerView = findViewById<RecyclerView>(R.id.faq_recycler_view)

        val faqList = ArrayList<FAQ>()

        // Define all FAQs here
        faqList.add(FAQ(getString(R.string.faq_activity_phone_mute_title),
                getString(R.string.faq_activity_phone_mute_description)))
        faqList.add(FAQ(getString(R.string.faq_activity_samsung_update_title),
                getString(R.string.faq_activity_samsung_update_description)))
        faqList.add(FAQ(getString(R.string.faq_activity_android_warning_title),
            getString(R.string.faq_activity_android_warning_description)))
        val faqAdapter = FAQAdapter(faqList)

        recyclerView.adapter = faqAdapter
    }
}