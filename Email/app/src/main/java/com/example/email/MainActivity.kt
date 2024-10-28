package com.example.email
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var emailAdapter: EmailAdapter
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Giả lập danh sách email
        val emailList = listOf(
            Email("Edurila.com", "Learn Web Designing...", "12:34 PM"),
            Email("Chris Abad", "Campaign Monitor better...", "11:22 AM"),
            Email("Tuto.com", "Formation gratuite et...", "11:04 AM"),
            Email("Support", "SAS OVH - http://www.ovh...", "10:26 AM"),
            Email(
                senderName = "John Doe",
                snippet = "Hi, just wanted to confirm the meeting schedule for next week. Looking forward...",
                time = "10:30 AM"
            ),
            Email(
                senderName = "Alice Smith",
                snippet = "Thank you for applying to our company. We are pleased to inform you that...",
                time = "9:15 AM"
            ),
            Email(
                senderName = "Michael Johnson",
                snippet = "Your order has been shipped and is expected to arrive on...",
                time = "Yesterday"
            ),
            Email(
                senderName = "Emily Brown",
                snippet = "Your recent transaction of $500 has been processed. Please review...",
                time = "Monday"
            ),
            Email(
                senderName = "Promo Offers",
                snippet = "Get up to 50% off on your next purchase when you use the code...",
                time = "Sunday"
            )
        )
        emailAdapter = EmailAdapter(emailList)
        recyclerView.adapter = emailAdapter
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

// Bạn có thể tùy chỉnh bằng cách thêm một drawable nếu muốn
        val dividerDrawable = ContextCompat.getDrawable(this, R.drawable.custom_divider)
        itemDecoration.setDrawable(dividerDrawable!!)

        recyclerView.addItemDecoration(itemDecoration)
    }
}