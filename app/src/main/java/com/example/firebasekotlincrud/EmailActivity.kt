package com.example.firebasekotlincrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasekotlincrud.databinding.ActivityEmailBinding

class EmailActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        binding = ActivityEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.login.setOnClickListener {
            val intent: Intent = Intent (this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.arrow.setOnClickListener {
            val intent: Intent = Intent (this, PrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}