package com.noviam.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.loginn.*


class login  : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.loginn)

        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener {
            if(inputEmail.text.trim().toString().isNotEmpty() || inputPassword.text.trim().toString().isNotEmpty()) {
                Loginn(inputEmail.text.trim().toString(), inputPassword.text.trim().toString());

            }else{
                Toast.makeText(this,"Input Required", Toast.LENGTH_LONG).show();
            }
        }


    }

    fun Loginn(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful){
                    val intent = Intent(this, MainActivity::class.java);
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Eror !!"+task.exception, Toast.LENGTH_LONG).show()
                }

            }

    }


}














