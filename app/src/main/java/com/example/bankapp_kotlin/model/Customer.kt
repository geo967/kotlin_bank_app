package com.example.bankapp_kotlin.model

import android.util.Log

class Customer {
    init {
       addCustomers("C1001", "Sachin", "sachin123", 1000000001, "savings", "1234567891", 21)
        addCustomers("C1001", "Sachin", "sachin123", 1000000001, "savings", "1234567891", 21)
        addCustomers("C1002", "Dhoni", "dhoni123", 1000000002, "current", "1234567892", 22)
        addCustomers("C1003", "Kohli", "kohli123", 1000000003, "savings", "1234567893", 23)
        addCustomers("C1004", "Rohit", "rohit123", 1000000004, "current", "1234567894", 24)

    }

    private val customerDetails=arrayListOf<CustomerDetails>()

    private fun addCustomers(customerId:String, customerName:String,
                             customerPassword:String, customerAccNo:Int,
                             customerAccType:String, customerPhoneNo:String, customerAge:Int){

        customerDetails.add(CustomerDetails(customerId,customerName,
            customerPassword,customerAccNo,customerAccType,customerPhoneNo,customerAge))

        Log.d("cat", customerDetails.toString())
    }

    fun getCustomerDetails():ArrayList<CustomerDetails>{
        return customerDetails
    }
}