package com.example.firebasekotlincrud

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.*
import com.example.firebasekotlincrud.databinding.ActivityLoginBinding
import com.huawei.hms.common.ApiException
import com.huawei.hms.support.account.AccountAuthManager
import com.huawei.hms.support.account.request.AccountAuthParams
import com.huawei.hms.support.account.request.AccountAuthParamsHelper
import com.huawei.hms.support.account.service.AccountAuthService


import java.util.*


class LoginActivity: AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "LoginActivity"
    }

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.huaweiIdBtn.setOnClickListener(this)

        binding.mailbtn.setOnClickListener {
            val intent: Intent = Intent (this, EmailActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.register.setOnClickListener {
            val intent: Intent = Intent (this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.arrow.setOnClickListener {
            val intent: Intent = Intent (this, PrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private val responseLauncher = registerForActivityResult(StartActivityForResult()){ result ->
        Log.i("dataresponse", result.resultCode.toString())
        if (result.resultCode != 0) {
            val authAccountTask = AccountAuthManager.parseAuthResultFromIntent(result.data)

            if (authAccountTask.isSuccessful) {
                // The sign-in is successful, and the user's ID information and ID token are obtained.
                //val authAccount = authAccountTask.result
                // Obtain the ID type (0: HUAWEI ID; 1: AppTouch ID).


                Toast.makeText(this, "Inició sesión", Toast.LENGTH_LONG)
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "No inició sesión", Toast.LENGTH_SHORT).show()
                // The sign-in failed. No processing is required. Logs are recorded for fault locating.
                Log.e(TAG,"sign in failed : " + (authAccountTask.exception as ApiException).statusCode
                )
            }
        }
    }

    override fun onClick(v: View?) {
        val authParams: AccountAuthParams =
            AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setIdToken()
                .setEmail()
                .createParams()
        val service: AccountAuthService = AccountAuthManager.getService(this@LoginActivity, authParams)
        responseLauncher.launch(service.signInIntent)
    }
}