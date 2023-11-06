package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val back = findViewById<View>(R.id.back)
        val close = findViewById<View>(R.id.close)
        back.setOnClickListener(View.OnClickListener {
            Back()
        })
        close.setOnClickListener(View.OnClickListener {
            Close()
        })
    }
    fun Back(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun Close(){
        finishAffinity()
        System.exit(0)
    }
}