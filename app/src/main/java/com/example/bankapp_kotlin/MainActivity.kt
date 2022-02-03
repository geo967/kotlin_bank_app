package com.example.bankapp_kotlin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bankapp_kotlin.databinding.ActivityMainBinding
import com.example.bankapp_kotlin.model.CustomerDetails
import com.example.bankapp_kotlin.model.StaffDetails
import com.example.bankapp_kotlin.view.CustomerLogin
import com.example.bankapp_kotlin.view.StaffLogin
import com.example.bankapp_kotlin.viewmodel.MainActivityViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private var mainActivityViewModel=MainActivityViewModel()
    private var customerDetails = arrayListOf<CustomerDetails>()
    private val staffDetails= arrayListOf<StaffDetails>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //view model provider is deprecated
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        customerDetails=mainActivityViewModel.getData()

        Log.d("cat",customerDetails.toString())

        val sp:SharedPreferences=getSharedPreferences("cDetails", MODE_PRIVATE)
        val editor:SharedPreferences.Editor=sp.edit()
        val gson=Gson()
        val json:String=gson.toJson(customerDetails)
        editor.putString("task list", json)
        editor.apply()
        handleStaffSelection(binding)
        handleCustomerSelection(binding)

    }

    private fun handleCustomerSelection(binding: ActivityMainBinding) {
        binding.customerLogin.setOnClickListener {
            val intent = Intent(this, CustomerLogin::class.java)

            startActivity(intent)
        }
    }


    private fun handleStaffSelection(binding: ActivityMainBinding) {
        binding.staffLogin.setOnClickListener {

            val intent = Intent(this, StaffLogin::class.java)

            startActivity(intent)
        }
    }
}