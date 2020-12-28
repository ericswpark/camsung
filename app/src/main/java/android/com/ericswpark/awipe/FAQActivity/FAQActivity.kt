package android.com.ericswpark.awipe.FAQActivity

import android.com.ericswpark.awipe.R
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
        faqList.add(FAQ(getString(R.string.faq_activity_overshoot_title),
                getString(R.string.faq_activity_overshoot_description)))
        faqList.add(FAQ(getString(R.string.faq_activity_undershoot_title),
                getString(R.string.faq_activity_undershoot_description)))
        faqList.add(FAQ(getString(R.string.faq_activity_leftover_space_title),
                getString(R.string.faq_activity_leftover_space_description)))
        faqList.add(FAQ(getString(R.string.faq_activity_force_close_title),
                getString(R.string.faq_activity_force_close_description)))
        faqList.add(FAQ(getString(R.string.faq_activity_wipe_security_title),
                getString(R.string.faq_activity_wipe_security_description)))
        val faqAdapter = FAQAdapter(faqList)

        recyclerView.adapter = faqAdapter
    }
}