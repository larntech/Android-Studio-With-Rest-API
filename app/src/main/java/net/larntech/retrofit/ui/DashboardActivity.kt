package net.larntech.retrofit.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import net.larntech.retrofit.adapter.AllUsersAdapter
import net.larntech.retrofit.adapter.TabChoiceAdapter
import net.larntech.retrofit.network.apiclient.ApiClient
import net.larntech.retrofit.databinding.ActivityDashboardBinding
import net.larntech.retrofit.model.response.users.AllUsersResponse
import net.larntech.retrofit.ui.users.EditUserActivity
import net.larntech.retrofit.ui.users.NewUserActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity(), AllUsersAdapter.SelectedConsumer {

    private lateinit var binding: ActivityDashboardBinding;

    private lateinit var allUsersAdapter: AllUsersAdapter;

    private var adapter: TabChoiceAdapter? = null

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
        setTabData();
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
        startActivity(Intent(this, NewUserActivity::class.java))
    }

    override fun selectedAllConsumers(userBean: AllUsersResponse.UsersBean) {
        startActivity(Intent(this, EditUserActivity::class.java).putExtra("user",userBean))
    }

    private fun getAllUsers(){


        val apiCall = ApiClient.getService().getAllUsers();

        apiCall.enqueue(object : Callback<AllUsersResponse> {

            override fun onResponse(call: Call<AllUsersResponse>, response: Response<AllUsersResponse>) {
                if(response.isSuccessful && response.body()!!.isSuccess == 1){
                    if(response.body()!!.users.size > 0) {
                        allUsersAdapter.setItems(response.body()!!.users)
                    }else{
                        binding.tvNoUserFound.visibility = View.VISIBLE
                    }
                }else{
                    binding.tvNoUserFound.visibility = View.VISIBLE
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

    fun setTabData() {
        adapter = TabChoiceAdapter(this)
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.adapter = adapter
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "All"
                1 -> tab.text = "Active"
                2 -> tab.text = "Expired"
            }
        }.attach()
        binding.tabLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 ->{}
                    1 ->{}
                    2 ->{}
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }



}