package com.example.firebasekotlincrud


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val SPLASH_SCREEN = 2000
    private lateinit var topanimation: Animation
    private lateinit var bottompanimation: Animation

    private  lateinit var imageView :ImageView
    private  lateinit var title_txt :TextView
    private  lateinit var description :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topanimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottompanimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        imageView = findViewById(R.id.Uttab)
        title_txt = findViewById(R.id.title_txt)
        description = findViewById(R.id.description)

        imageView.animation = topanimation
        title_txt.animation = bottompanimation
        description.animation = bottompanimation

        Handler().postDelayed({
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN.toLong())

    }
}