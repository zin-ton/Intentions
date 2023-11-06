package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val SMS = findViewById<View>(R.id.smsButton) as Button
        val text1 = findViewById<View>(R.id.text1) as TextView
        val text2 = findViewById<View>(R.id.Text2) as TextView
        val openMap = findViewById<View>(R.id.mapButton) as Button
        val documents = findViewById<View>(R.id.documentsButton) as Button
        val mailButton = findViewById<View>(R.id.mailButton) as Button
        val callButton = findViewById<View>(R.id.phoneButton) as Button
        val music = findViewById<View>(R.id.musicButton) as Button
        val secondActivity = findViewById<View>(R.id.activityButton) as Button
        val calendar = findViewById<View>(R.id.calendarButton) as Button
        val close = findViewById<View>(R.id.exitButton) as Button
        SMS.setOnClickListener(View.OnClickListener {
            val number = text1.text.toString()
            val text = text2.text.toString()
            SendSMS(number, text)
            Toast.makeText(this, "sms: " + text + " to: " + number, Toast.LENGTH_SHORT)
        })
        openMap.setOnClickListener(View.OnClickListener {
            val text = text1.text.toString()
            FindOnMap(text);
        })
        documents.setOnClickListener(View.OnClickListener {
            OpenAPP()
        })
        callButton.setOnClickListener(View.OnClickListener {
            val phone = text2.text.toString().trim()
            Call(phone)
        })
        music.setOnClickListener(View.OnClickListener {
            OpenPlayer()
        })
        secondActivity.setOnClickListener(View.OnClickListener {
            val q = text1.text.toString()
            val w = text2.text.toString()
            activity2(q,w)
        })
        calendar.setOnClickListener(View.OnClickListener {
           val dateTime = text1.text.toString()
            val title = text2.text.toString()
            calendar(dateTime, title)
        })
        close.setOnClickListener(View.OnClickListener {
            Close()
        })
    }
    fun SendSMS(number:String, text: String){
        val uri = Uri.parse("smsto:$number")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", text)
        startActivity(intent)
    }
    fun FindOnMap(text: String){
        val uri = Uri.parse("geo:0,0?q=$text")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
    fun OpenAPP(){
        val viewDoc = Intent(Intent.ACTION_VIEW)
        val uri = Uri.parse("file:///sdcard/folder/file.pdf")
        viewDoc.setDataAndType(null, "application/pdf")
        startActivity(viewDoc)
    }
    fun SendMail(email:String, text:String){
        val email = Intent(Intent.ACTION_SENDTO)
        email.setType("text/plain")
        email.putExtra(Intent.EXTRA_EMAIL, email)
        email.putExtra(Intent.EXTRA_SUBJECT, "idk")
        email.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(email)
    }
    fun Call(phone: String){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${Uri.encode(phone)}")
        startActivity(intent)
    }
    fun OpenPlayer(){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(null, "audio/*")
        startActivity(intent)
    }
    fun activity2(contact1: String, contact2: String){
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("q", contact1)
        intent.putExtra("w", contact2)
        startActivity(intent)
    }
    fun calendar(dateTime: String, title: String){
        val intent = Intent(Intent.ACTION_EDIT)
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("title", title);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, dateTime)
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, dateTime)
        startActivity(intent)
    }
    fun Close(){
        finishAffinity()
        System.exit(0)
    }
}
