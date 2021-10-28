package net.larntech.retrofit

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.larntech.retrofit.apiclient.ApiClient
import net.larntech.retrofit.databinding.ActivityRegisterBinding
import net.larntech.retrofit.model.request.RegisterUserRequest
import net.larntech.retrofit.model.response.RegisterUserResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initData()
    }


    private fun initData(){
        binding.llHaveAccount.setOnClickListener {
            loginUser()
        }

        binding.btnRegister.setOnClickListener {
            getInputs()
        }

    }

    private fun loginUser(){
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    private fun getInputs() {
        val username = binding.edUsername.text.toString()
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        val cpassword = binding.edConfirmPassword.text.toString()

        if (username.isNotBlank() && email.isNotEmpty() && password.isNotEmpty() && cpassword.isNotEmpty()) {
            if (password == cpassword) {
                registerUser(username, email, password)
            } else {
                showToast("Password and Confirm Password should be the same")
            }

        }else{
            showToast("All inputs required ... ")
        }

    }

    private fun showToast( message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun registerUser(username: String, userEmail: String, userPassword: String){
        showToast("Please wait ...")
        val apiCall = ApiClient.getService().registerUser(RegisterUserRequest(username,userEmail,"2021-10-18 05:54:29",userPassword))
        apiCall.enqueue(object : Callback<RegisterUserResponse>{
            override fun onResponse(
                call: Call<RegisterUserResponse>,
                response: Response<RegisterUserResponse>
            ) {
                if(response.isSuccessful){
                    showToast("User registered successfully, Proceed to login ")
                    Handler().postDelayed({
                                 loginUser()
                    },1500)
                }else{
                    val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                    showToast("Unable to register user ..." + jsonObj.toString())
                }

            }

            override fun onFailure(call: Call<RegisterUserResponse>, t: Throwable) {
                showToast("Server Error: " + t.localizedMessage)
            }

        })

    }



}