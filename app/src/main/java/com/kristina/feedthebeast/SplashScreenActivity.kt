package com.kristina.feedthebeast

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class SplashScreenActivity : AppCompatActivity() {

    lateinit var ivTop: ImageView
    lateinit var ivHeart: ImageView
    lateinit var ivBottom: ImageView
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        ivTop = findViewById(R.id.iv_top)
        ivHeart = findViewById(R.id.iv_heart)
        ivBottom = findViewById(R.id.iv_bottom)
        textView= findViewById(R.id.text_view)

        // todo avoid deprecated
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // top animation
        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_wave)
        ivTop.startAnimation(topAnim)

        // bottom animation
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_wave)
        ivBottom.startAnimation(bottomAnim)

        // text animation
        val textAnim = AnimationUtils.loadAnimation(this,R.anim.text_animation);
        textView.startAnimation(textAnim);

        // object animator
        val objAnimator = ObjectAnimator.ofPropertyValuesHolder(
            ivHeart,
            PropertyValuesHolder.ofFloat("scaleX", 1.2f),
            PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        )
        objAnimator.duration = 800
        objAnimator.repeatCount = 5
        // set repeat mode
        objAnimator.repeatMode = ValueAnimator.REVERSE

        objAnimator.start()

        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }, 4000)

    }
}