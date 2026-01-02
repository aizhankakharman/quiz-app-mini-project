package com.example.afinal

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.afinal.Activity.MainActivity

class MainActivity2 : AppCompatActivity() {
    private lateinit var emailEdit: EditText
    private lateinit var passEdit: EditText
    private lateinit var button: ImageButton

    private var passString: String? = null
    private var emailString: String? = null

    // shared preferences
    private lateinit var sharedPreferences: SharedPreferences

    // shared preferences mail and password keys
    companion object {
        private const val SHARED_PREF_NAME = "mypref"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show()

        emailEdit = findViewById(R.id.emailEditText)
        passEdit = findViewById(R.id.passwordEditText)
        button = findViewById(R.id.button)

        // shared preferences initialization
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

        // shared check
        val email = sharedPreferences.getString(KEY_EMAIL, null)
        if (email != null) {
            val intent = Intent(this@MainActivity2, MainActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            // shared preferences editor
            val editor = sharedPreferences.edit()
            editor.putString(KEY_EMAIL, emailEdit.text.toString())
            editor.putString(KEY_PASSWORD, passEdit.text.toString())
            editor.apply()

            emailString = emailEdit.text.toString()
            passString = passEdit.text.toString()

            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("email", emailString)
            intent.putExtra("pass", passString)
            startActivity(intent)

            Log.i("intent", emailString.toString())
        }
    }

    override fun onPause() {
        Toast.makeText(this, "onPause()", Toast.LENGTH_LONG).show()
        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart()", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart()", Toast.LENGTH_LONG).show()
    }
}
