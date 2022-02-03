package com.example.bankapp_kotlin.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bankapp_kotlin.databinding.ActivityStaffLoginCustomerPageBinding
import com.example.bankapp_kotlin.model.CustomerDetails
import java.util.ArrayList

class StaffLoginCustomerPage : AppCompatActivity() {
    lateinit var binding: ActivityStaffLoginCustomerPageBinding
    private lateinit var customerDetails: ArrayList<CustomerDetails>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityStaffLoginCustomerPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.idCustomerAccInfo.setOnClickListener {

            val sp: SharedPreferences =getSharedPreferences("inputId", MODE_PRIVATE)
            val getInput:String?=sp.getString("customerIdInput","")



            for(i in 0 until customerDetails.size){
                if(customerDetails[i].customerId.toString()==getInput.toString()){

                    binding.idCustomerId.text=customerDetails[i].customerId
                    binding.name.text=customerDetails[i].customerName
                    binding.password.text=customerDetails[i].customerPassword
                    binding.accountNo.text= customerDetails[i].customerAccNo.toString()
                    binding.idAccountType.text=customerDetails[i].customerAccType
                    binding.idPhone.text=customerDetails[i].customerPhoneNo
                    binding.idAge.text=customerDetails[i].customerAge.toString()


                }
            }
            binding.linearLayoutCustomerDetails.visibility= View.VISIBLE
        }
    }
}