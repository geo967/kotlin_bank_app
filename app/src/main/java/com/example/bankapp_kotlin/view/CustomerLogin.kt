package com.example.bankapp_kotlin.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bankapp_kotlin.R
import com.example.bankapp_kotlin.databinding.ActivityCustomerLoginBinding
import com.example.bankapp_kotlin.model.CustomerDetails
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList

class CustomerLogin : AppCompatActivity() {

    private lateinit var binding:ActivityCustomerLoginBinding
    private lateinit var customerId:TextInputLayout
    private lateinit var customerPassword: TextInputLayout
    private lateinit var inputCustomerId:String
    private lateinit var inputCustomerPassword:String
    private val customerDetails = arrayListOf<CustomerDetails>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCustomerLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        handleCustomerSignIn()
        handleNewCustomerSignUp()

       // val customerDetails = arrayListOf<CustomerDetails>()
        customerDetails.add(CustomerDetails("C1001", "Sachin", "sachin123", 1000000001, "savings", "1234567891", 21))
        customerDetails.add(CustomerDetails("C1002", "Dhoni", "dhoni123", 1000000002, "current", "1234567892", 22))
        customerDetails.add(CustomerDetails("C1003", "Kohli", "kohli123", 1000000003, "savings", "1234567893", 23))
        customerDetails.add(CustomerDetails("C1004", "Rohit", "rohit123", 1000000004, "current", "1234567894", 24))

    }

    private fun handleNewCustomerSignUp() {
        binding.idNewCustomer.setOnClickListener {
            val intent = Intent(this, CustomerSignUpForm::class.java)
            startActivity(intent)
        }
    }

    private fun handleCustomerSignIn() {
        binding.idSignInCustomer.setOnClickListener {
            customerId = findViewById(R.id.id_customerId)
            customerPassword = findViewById(R.id.id_password_customer)
            inputCustomerId = customerId.editText!!.text.toString()
            inputCustomerPassword = customerPassword.editText!!.text.toString()
            validateCustomerId(inputCustomerId)
            validatePassword(inputCustomerPassword)
        }
    }

    private fun validatePassword(inputCustomerPassword: String):Boolean {
        val p: Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=()])(?=\\S+\$).{8,20}\$")
        val m: Matcher = p.matcher(inputCustomerPassword)
        if (inputCustomerPassword == "") {
            binding.idPasswordCustomer.error = "Field is empty"
            return false
        } else if (!m.matches()) {
            binding.idPasswordCustomer.error = "not a valid password format"
            return false
        } else {
            binding.idPasswordCustomer.error = null
            return false
        }
    }

    private fun validateCustomerId(inputCustomerId: String):Boolean {
        val p: Pattern = Pattern.compile("[C][0-9]{4}")
        val m: Matcher = p.matcher(inputCustomerId)
        if (inputCustomerId == "") {
            binding.idCustomerId.error = "Field is empty"
            return false
        } else if (!m.matches()) {
            binding.idCustomerId.error= "not a valid email format"
            return false
        } else {
            binding.idCustomerId.error= null
            return false
        }
    }

fun getCustomerDetails():ArrayList<CustomerDetails>{
    return customerDetails
}

}