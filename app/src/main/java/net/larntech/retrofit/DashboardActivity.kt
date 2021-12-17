package net.larntech.retrofit

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import net.larntech.retrofit.adapter.AllUsersAdapter
import net.larntech.retrofit.apiclient.ApiClient
import net.larntech.retrofit.databinding.ActivityDashboardBinding
import net.larntech.retrofit.model.response.AllUsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity(), AllUsersAdapter.SelectedConsumer {

    private lateinit var binding: ActivityDashboardBinding;

    private lateinit var allUsersAdapter: AllUsersAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater);
        val view = binding.root
        setContentView(view)
        initData();
    }

    private fun initData(){
        clickListener();
        setUpNewTrainerRecyclerView();
        getAllUsers();
    }

    private fun setUpNewTrainerRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = layoutManager
        allUsersAdapter = AllUsersAdapter(this)
        binding.recyclerView.adapter = allUsersAdapter
    }

    private fun clickListener(){
        binding.fabButton.setOnClickListener {
            showAddNewUser();
        }
    }

    private fun showAddNewUser(){
        startActivity(Intent(this,NewUserActivity::class.java))
    }

    override fun selectedAllConsumers(userBean: AllUsersResponse.UsersBean) {
        startActivity(Intent(this,EditUserActivity::class.java).putExtra("user",userBean))
    }


    private fun getAllUsers(){

        showToast("Fetching users ...")

        val apiCall = ApiClient.getService().getAllUsers();

        apiCall.enqueue(object : Callback<AllUsersResponse> {

            override fun onResponse(call: Call<AllUsersResponse>, response: Response<AllUsersResponse>) {
                if(response.isSuccessful && response.body()!!.isSuccess == 1){
                    allUsersAdapter.setItems(response.body()!!.users)
                }else{
                    showToast(response.body()!!.message)
                }
            }

            override fun onFailure(call: Call<AllUsersResponse>, t: Throwable) {
                showToast("An error occurred: "+t.localizedMessage)
            }

        })

    }


    private fun showToast( message: String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }




}