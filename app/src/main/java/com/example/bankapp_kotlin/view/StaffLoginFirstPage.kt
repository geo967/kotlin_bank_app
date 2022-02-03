package com.example.bankapp_kotlin.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.bankapp_kotlin.R
import com.example.bankapp_kotlin.databinding.ActivityStaffLoginFirstPageBinding
import com.example.bankapp_kotlin.model.Bank
import com.example.bankapp_kotlin.model.Customer
import com.example.bankapp_kotlin.model.CustomerDetails
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList

class StaffLoginFirstPage : AppCompatActivity() {
    private lateinit var bindingStaffLoginFirstPage: ActivityStaffLoginFirstPageBinding
    private lateinit var customerIdLayout: TextInputLayout
    private lateinit var inputId: String
    private lateinit var customerDetails: ArrayList<CustomerDetails>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        bindingStaffLoginFirstPage = ActivityStaffLoginFirstPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingStaffLoginFirstPage.root)

        customerIdLayout = findViewById(R.id.id_customerId_staffLogin)


        handleCustomerSelection()

        handleNewCustomerCreation()
        val bank = Bank()
        handleInterestRateView(bank)

        handleTotalCustomerView(bank)

        handleCustomerSignInFromStaff()

    }

    private fun handleCustomerSignInFromStaff() {
        bindingStaffLoginFirstPage.idSignInCustomerFromStaffLogin.setOnClickListener {

            inputId = bindingStaffLoginFirstPage.idCustomerIdStaffLogin.editText!!.text.toString()

            for (i in 0 until customerDetails.size) {
                if (customerDetails[i].customerId == inputId) {
                    val intent = Intent(this, StaffLoginCustomerPage::class.java)
                    val sp: SharedPreferences = getSharedPreferences("inputId", MODE_PRIVATE)
                    val ed: SharedPreferences.Editor = sp.edit()
                    ed.putString("customerIdInput", inputId)
                    ed.apply()
                    intent.putExtra("cDetails", customerDetails)
                    startActivity(intent)

                }
            }
        }
    }

    private fun handleTotalCustomerView(bank: Bank) {
        bindingStaffLoginFirstPage.idTotalNoOfCustomers.setOnClickListener {
            bindingStaffLoginFirstPage.idCustomerCount.text =
                "The total no of customers is ${bank.noOfCustomers}"
            bindingStaffLoginFirstPage.idCustomerCount.visibility = View.VISIBLE
        }
    }

    private fun handleInterestRateView(bank: Bank) {
        bindingStaffLoginFirstPage.idViewInterestRate.setOnClickListener {
            bindingStaffLoginFirstPage.idCurrentInterest.text =
                "Current Account Interest Rate is ${bank.currentInterest}"
            bindingStaffLoginFirstPage.idSavingsInterest.text =
                "Savings Account Interest Rate is ${bank.savingsInterest}"
            bindingStaffLoginFirstPage.idCurrentInterest.visibility = View.VISIBLE
            bindingStaffLoginFirstPage.idSavingsInterest.visibility = View.VISIBLE
        }
    }

    private fun handleNewCustomerCreation() {
        bindingStaffLoginFirstPage.idNewCustomerCreation.setOnClickListener {
            val intent = Intent(this, CustomerSignUpForm::class.java)
            startActivity(intent)
        }
    }

    private fun handleCustomerSelection() {
        bindingStaffLoginFirstPage.idCustomerSelection.setOnClickListener {
            customerIdLayout.visibility = View.VISIBLE
            bindingStaffLoginFirstPage.idSignInCustomerFromStaffLogin.visibility = View.VISIBLE
        }
    }

}