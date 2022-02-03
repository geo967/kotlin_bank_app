package com.example.bankapp_kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bankapp_kotlin.model.CustomerDetails

class MainActivityViewModel: ViewModel() {

    private val customerDetails = ArrayList<CustomerDetails>()

    //live data
    var mutableLiveData = MutableLiveData<String>()

    //connecting to db
    fun data():ArrayList<CustomerDetails> {
        customerDetails.add(
            CustomerDetails(
                "C1001", "Sachin", "sachin123",
                1000000001, "savings", "1234567891", 21
            )
        )
        customerDetails.add(
            CustomerDetails(
                "C1002", "Dhoni",
                "dhoni123", 1000000002, "current", "1234567892", 22
            )
        )
        customerDetails.add(
            CustomerDetails(
                "C1003", "Kohli",
                "kohli123", 1000000003, "savings", "1234567893", 23
            )
        )
    return customerDetails
    }

    //connecting main activity and view model
    fun getData():ArrayList<CustomerDetails>{
        return customerDetails
    }
}
