package net.larntech.retrofit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.larntech.retrofit.databinding.ActivityMainBinding
import net.larntech.retrofit.ui.auth.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        val view = binding.root;
        setContentView(view)
        switchLogin()
    }

    private fun switchLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}