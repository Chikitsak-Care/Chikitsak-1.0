package com.vivek.chikitsak

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var sp: Splash
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSplash()
        setFullScreen(yes = true)
        setFullScreen()
        auth = FirebaseAuth.getInstance()

    }

    private fun setFullScreen(yes: Boolean)  : MainActivity {
        intent.putExtra(SplashActivity.FULL_SCREEN, yes)
        return this
    }
    private fun setFullScreen() {

        if (intent.hasExtra(SplashActivity.FULL_SCREEN)) {

            if (intent.getBooleanExtra(SplashActivity.FULL_SCREEN, false))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.statusBarColor = Color.TRANSPARENT
                    window.navigationBarColor = Color.TRANSPARENT
                    window.setFlags(
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                    )
                }
        }

    }

    public fun getrandomanim(): Splash.Animation {
        val rnds = (1..6).random()
        return when (rnds) {
            1 -> Splash.Animation.SLIDE_IN_TOP_BOTTOM

            2 -> Splash.Animation.SLIDE_IN_LEFT_RIGHT

            3 -> Splash.Animation.GLOW_LOGO

            4 -> Splash.Animation.SLIDE_IN_LEFT_BOTTOM

            5 -> Splash.Animation.SLIDE_LEFT_ENTER

            6 -> Splash.Animation.GLOW_LOGO_TITLE

            else -> Splash.Animation.GLOW_LOGO
        }
    }

    private fun setSplash() {
        Splash(this)
                .setLogo(R.drawable.logo)
                .setAnimation(getrandomanim())
                .setBackgroundResource(R.color.white)
                .setTitleColor(R.color.black)
                .setProgressColor(R.color.black)
                .setTitle(R.string.stitle)
                .setSubTitle(R.string.Splash_subtitle)
                .setFullScreen(true)
                .show()

        Splash.onComplete(object : Splash.OnComplete {
            override fun onComplete() {
                Toast.makeText(this@MainActivity, "Welcome", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, LoginActivity::class.java ))
                finish()
            }

        })
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}
