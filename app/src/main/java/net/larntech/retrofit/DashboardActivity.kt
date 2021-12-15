package net.larntech.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.larntech.retrofit.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater);
        val view = binding.root
        setContentView(view)
        initData();
    }

    private fun initData(){
//        var username = ""
//       val intent = intent.extras;
//        if(intent != null){
//            username = intent.getString("data")!!;
//        }
//
//        binding.tvUsername.text = "Welcome: $username"

        clickListener();
    }

    private fun clickListener(){
        binding.fabButton.setOnClickListener {
            showAddNewUser();
        }
    }

    private fun showAddNewUser(){
        startActivity(Intent(this,NewUserActivity::class.java))
    }

}