package com.example.trip.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trip.R
import com.example.trip.Util.ToastUtil
import com.example.trip.databinding.ActivityFindPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class FindPasswordActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    lateinit var findPasswordBinding: ActivityFindPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findPasswordBinding = ActivityFindPasswordBinding.inflate(layoutInflater)
        val view = findPasswordBinding.root
        setContentView(view)
        firebaseAuth = FirebaseAuth.getInstance()

        findPasswordBinding.findBackBtn.setOnClickListener {
            onBackPressed()
        }

        findPasswordBinding.findCompleteBtn.setOnClickListener {
            val email = findPasswordBinding.findId.text.toString()
            if(email.isBlank()){
                ToastUtil.toastPrint(this, "이메일을 입력해 주세요")
            } else {
                firebaseAuth.setLanguageCode("kr")
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if(it.isSuccessful){
                        ToastUtil.toastPrint(this, "이메일 전송 완료")
                    }else {
                        ToastUtil.toastPrint(this, "이메일을 다시 입력하세요.")
                    }
                }
            }
        }

    }
}