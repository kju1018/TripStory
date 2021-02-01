package com.example.trip.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import com.example.trip.R
import com.example.trip.Util.ToastUtil
import com.example.trip.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)
        firebaseAuth = FirebaseAuth.getInstance()

        loginBinding.loginBackBtn.setOnClickListener {
            onBackPressed()
        }
        loginBinding.loginFindpassword.setOnClickListener {
            startActivity(Intent(this, FindPasswordActivity::class.java))
        }

        loginBinding.loginCompleteBtn.setOnClickListener{
            val email = loginBinding.loginId.text.toString()
            val password = loginBinding.loginPassword.text.toString()
            when {
                email.isBlank() -> {
                    ToastUtil.toastPrint(this, "아이디를 입력해 주세요.")
                }
                password.isBlank() -> {
                    ToastUtil.toastPrint(this, "비밀번호를 입력해 주세요.")
                }
                else -> {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                        if(it.isSuccessful){
                            ToastUtil.toastPrint(this, "로그인 완료")
                            val intent = Intent(this, HomeActivity::class.java)
                            finish()
                            startActivity(intent)
                        }else{
                            ToastUtil.toastPrint(this, "아이디 비밀번호를 확인해주세요.")
                        }
                    }
                }
            }
        }
    }
}