package net.larntech.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import net.larntech.retrofit.apiclient.ApiClient
import net.larntech.retrofit.databinding.ActivityNewUserBinding
import net.larntech.retrofit.model.response.RegisterUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import android.widget.RadioButton
import java.text.SimpleDateFormat


class NewUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewUserBinding;

    private var expiryDate: String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        val view = binding.root;
        setContentView(view)
        initData();
    }


    private fun initData(){
        binding.btnNewUser.setOnClickListener {
            getInputs();
        }

    }



    private fun getInputs(){
        val username = binding.edUsername.text.toString();
        val password = binding.edPassword.text.toString();

        if(username.isNotBlank() && password.isNotEmpty() ){
                addUser(username,password,expiryDate)
        }else{
            showToast("All inputs required ... ");
        }

    }

    private fun showToast( message: String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }


    private fun addUser(username: String, userPassword: String, userExpiry: String){
        showToast("Please wait ...")
        Log.e(" date ","expiry date"+userExpiry)
        val apiCall = ApiClient.getService().addUser(username,userPassword,userExpiry)
        apiCall.enqueue(object : Callback<RegisterUserResponse>{
            override fun onResponse(
                call: Call<RegisterUserResponse>,
                response: Response<RegisterUserResponse>
            ) {
                if(response.isSuccessful && response.body()!!.isSuccess == 1){
                    showToast(response.body()!!.message)
                    Handler().postDelayed({
                        goToDashboard()
                    },1500)
                }else{
                    showToast(response.body()!!.message)
                }

            }

            override fun onFailure(call: Call<RegisterUserResponse>, t: Throwable) {
                Log.e(" fail "," error "+t.localizedMessage)
                showToast("Server Error: "+t.localizedMessage)
            }

        })

    }


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                binding.rbOneDay.id -> {
                    if (checked) {
                        expiryDate = getExpiryDateTime(1)
                    }
                }
                binding.rbThreeDay.id -> {
                    if (checked) {
                        expiryDate = getExpiryDateTime(3)
                    }
                }
                binding.rbOneWeek.id -> {
                    if (checked) {
                        expiryDate = getExpiryDateTime(7)

                    }
                }
                binding.rbOneMonth.id -> {
                    if (checked) {
                        expiryDate = getExpiryDateTime(30)
                    }
                }
                else -> {
                }


            }
        }

    }


    private fun getExpiryDateTime(days: Int): String {
        var dt = Date()
        val c = Calendar.getInstance()
        c.time = dt
        c.add(Calendar.DATE, days)
        dt = c.time
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(dt);
    }

    private fun  goToDashboard(){
        startActivity(Intent(this,DashboardActivity::class.java))
        finish()
    }

}