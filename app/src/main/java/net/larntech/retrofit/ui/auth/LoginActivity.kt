package net.larntech.retrofit.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import net.larntech.retrofit.ui.DashboardActivity
import net.larntech.retrofit.network.apiclient.ApiClient
import net.larntech.retrofit.databinding.ActivityLoginBinding
import net.larntech.retrofit.model.response.auth.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        var view = binding.root;
        setContentView(view)
        initData()

    }

    private fun initData(){
        binding.llNoAccount.setOnClickListener {
            registerUser();
        }
        binding.btnLogin.setOnClickListener {
            getInputs();
        }
    }

    private fun registerUser(){
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

    private fun getInputs(){
        val username = binding.edUsername.text.toString();
        val password = binding.edPassword.text.toString();

        if(username.isNotEmpty() && password.isNotEmpty()){
            authUser(username,password)
        }else{
            showToast("All inputs required ...");
        }

    }

    private fun showToast( message: String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    private fun authUser(username:String, password: String){
        showToast("Authentication, Please wait ...")
        val apiCall = ApiClient.getService().authenticateUser(username,password);
        apiCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful && response.body()!!.isSuccess == 1) {
                        startDashboardActivity(response.body()!!.username)
                }else {
                    showToast(response.body()!!.message)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                showToast("An error occurred: "+t.localizedMessage)
            }

        })

    }

    private fun startDashboardActivity(username: String){
        startActivity(Intent(this, DashboardActivity::class.java).putExtra("data",username))
        finish()
    }

}