package com.vivek.chikitsak

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class LoginActivity : AppCompatActivity() {

    lateinit var providers : List<AuthUI.IdpConfig>
    private val MY_REQUEST_CODE: Int = 7119
    private lateinit var auth: FirebaseAuth

    // Initialize Firebase Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        //Init
        providers = Arrays.asList<AuthUI.IdpConfig>(
                AuthUI.IdpConfig.EmailBuilder().build(), //Email Builder
                AuthUI.IdpConfig.GoogleBuilder().build(), //Google Sign In
                AuthUI.IdpConfig.PhoneBuilder().build() //Phone Sign In
        )

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
        else {
            showSignInOptions()
            Toast.makeText(this, "You need to Sign-In", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.drawable.login_background)
                .setIsSmartLockEnabled(false)
                .build(), MY_REQUEST_CODE)

        if(requestCode == MY_REQUEST_CODE)
        {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this, ""+user!!.email, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, ""+user!!.phoneNumber, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
            if (response == null)
            {
                Toast.makeText(this, "Closed", Toast.LENGTH_SHORT).show()
                val a = Intent(Intent.ACTION_MAIN)
                a.addCategory(Intent.CATEGORY_HOME)
                a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(a)
                finishAndRemoveTask()
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Sign-In Cancelled", Toast.LENGTH_SHORT).show()
                val a = Intent(Intent.ACTION_MAIN)
                a.addCategory(Intent.CATEGORY_HOME)
                a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(a)
                finishAndRemoveTask()
            }
        }
    }

    private fun showSignInOptions() {
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.AppTheme_Fullscreen)
                .build(),MY_REQUEST_CODE)
    }

}
