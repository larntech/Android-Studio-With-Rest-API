package net.larntech.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.JsonObject
import net.larntech.retrofit.apiclient.ApiClient
import net.larntech.retrofit.databinding.ActivityLoginBinding
import net.larntech.retrofit.model.request.AuthRequest
import net.larntech.retrofit.model.response.AuthResponse
import org.json.JSONObject
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
        startActivity(Intent(this,RegisterActivity::class.java))
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
        val authUser = AuthRequest(username,password);
        showToast("Please wait ...")

        val apiCall = ApiClient.getService().authenticateUser(authUser);
        apiCall.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if(response.isSuccessful) {
                    showToast("Authentication successful ...")
                    startDashboardActivity(response.body()!!.username)
                }else {
                    var error = JSONObject(response.errorBody()!!.charStream().readText())
                    showToast(error.toString())
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                showToast("An error occurred: "+t.localizedMessage)
            }

        })

    }

    private fun startDashboardActivity(username: String){
        startActivity(Intent(this, DashboardActivity::class.java).putExtra("data",username))
        finish()
    }

}