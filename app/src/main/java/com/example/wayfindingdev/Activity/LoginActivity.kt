package com.example.wayfindingdev.Activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import com.example.wayfindingdev.Model.LoginResponse
import com.example.wayfindingdev.Network.GetDataService
import com.example.wayfindingdev.Network.RetrofitInstance
import com.example.wayfindingdev.R
import com.example.wayfindingdev.Tools.currentUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {

    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var btncon:Button
    private lateinit var dialog:Dialog
    private lateinit var closeMsg:ImageButton
    private lateinit var msgText:TextView
    private lateinit var progressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.wayfindingdev.R.layout.activity_login)

        email = findViewById(R.id.editText_email_con)
        password = findViewById(R.id.editText_password_con)
        btncon = findViewById(R.id.button_connexion)
        progressBar = findViewById(R.id.progressBarLogin)




        dialog = Dialog(this)


        btncon.setOnClickListener {
            Login(email.text.toString(),password.text.toString())
        }
    }

    private fun Login(email: String, password: String) {
        btncon.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        val dataService = RetrofitInstance.
            retrofitInstance
            .create(GetDataService::class.java)
        val thethis = this

        val call = dataService.Login(email.trim(), password.trim())
        call.enqueue(object:Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                showErrorMsg(t.message)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val body = response.body()
                if(body != null){
                    if(body.status == 1){
                        currentUser = body.user
                        val intent = Intent(applicationContext,HomeActivity::class.java)
                        startActivity(intent)
                        thethis.finish()
                    }else{
                        showErrorMsg("Utilisateur inconnu")
                    }
                }
            }

        })


    }

    private fun showErrorMsg(message: String?) {
        dialog.setContentView(R.layout.layout_popup_error_login)
        closeMsg = dialog.findViewById(R.id.imageButton_close_msg)
        msgText = dialog.findViewById(R.id.textview_msg_text)

        closeMsg.setOnClickListener {
            dialog.dismiss()
            btncon.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
        if (message != null) {
            msgText.text = message
        }else{
            msgText.text = "Erreur inconnue !"
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()


    }
}
