package com.example.trip.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.trip.R
import com.example.trip.Util.ToastUtil
import com.example.trip.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.sign

class SignupActivity : AppCompatActivity() {

    private lateinit var signupBinding: ActivitySignupBinding
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        val view = signupBinding.root
        setContentView(view)

        signupBinding.signupBackBtn.setOnClickListener {
            onBackPressed()
        }

        signupBinding.signupCompleteBtn.setOnClickListener {
            val email = signupBinding.signupId.text.toString()
            val password = signupBinding.signupPassword.text.toString()
            val passwordconfirm = signupBinding.signupPasswordconfirm.text.toString()

            when {
                password != passwordconfirm -> {
                    ToastUtil.toastPrint(this, "비밀번호가 다릅니다.")
                }
                email.isBlank() -> {
                    ToastUtil.toastPrint(this, "아이디를 입력해 주세요.")
                }
                password.isBlank() -> {
                    ToastUtil.toastPrint(this, "비밀번호를 입력해 주세요.")
                }
                passwordconfirm.isBlank() -> {
                    ToastUtil.toastPrint(this, "비밀번호를 한번 더 입력해 주세요.")
                }
                else -> {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener {
                                if(it.isSuccessful){
                                    if(firebaseAuth.currentUser != null) {
                                        ToastUtil.toastPrint(this, "${firebaseAuth.currentUser!!.email}로 회원가입 완료")
                                        finish()
                                    }
                                } else {
                                    ToastUtil.toastPrint(this, "회원가입 실패")
                                }
                            }
                }
            }
        }
    }
}