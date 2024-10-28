package com.example.email

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class EmailAdapter(private val emailList: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]
        holder.senderName.text = email.senderName
        holder.emailSnippet.text = email.snippet
        holder.timeStamp.text = email.time
        holder.avatar.text = email.senderName[0].toString().uppercase()
        holder.avatar.background = holder.itemView.context.getDrawable(R.drawable.circle_background)
        holder.avatar.background.setTint(getRandomColor())
    }

    override fun getItemCount() = emailList.size

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderName: TextView = itemView.findViewById(R.id.senderName)
        val emailSnippet: TextView = itemView.findViewById(R.id.emailSnippet)
        val timeStamp: TextView = itemView.findViewById(R.id.timeStamp)
        val avatar: TextView = itemView.findViewById(R.id.avatar)

        fun bind(email: Email) {
            emailSnippet.text = if (email.snippet.length > 50) {
                email.snippet.substring(0, 50) + "..."
            } else {
                email.snippet
            }
        }
    }

    // Hàm để lấy màu ngẫu nhiên
    private fun getRandomColor(): Int {
        val random = Random
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }
}
