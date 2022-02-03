package com.example.bankapp_kotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bankapp_kotlin.R
import com.example.bankapp_kotlin.databinding.ActivityStaffLoginBinding
import com.example.bankapp_kotlin.model.StaffDetails
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Matcher
import java.util.regex.Pattern

class StaffLogin : AppCompatActivity() {
    lateinit var binding:ActivityStaffLoginBinding

    private lateinit var staffId: TextInputLayout
    private lateinit var staffPassword: TextInputLayout
    private lateinit var inputStaffId:String
    private lateinit var inputStaffPassword:String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityStaffLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        handleStaffSignIn()

        val staffDetails= arrayListOf<StaffDetails>()
        staffDetails.add(StaffDetails("S1001","Geo","Geo@12345"))
        staffDetails.add(StaffDetails("S1002","Adam","Adam@12345"))
        staffDetails.add(StaffDetails("S1003","Sam","Sam@12345"))
        staffDetails.add(StaffDetails("S1004","Pinky","Pinky@12345"))



    }

    private fun handleStaffSignIn() {
        binding.idSignInStaff.setOnClickListener {
            staffId = findViewById(R.id.id_staffId)
            staffPassword = findViewById(R.id.id_staff_password)
            inputStaffId = staffId.editText!!.text.toString()
            inputStaffPassword = staffPassword.editText!!.text.toString()
            val resultOfIdValidation=validateStaffId(inputStaffId)
            val resultOfPasswordValidation=validateStaffPassword(inputStaffPassword)
            if(resultOfIdValidation&&resultOfPasswordValidation){
                val intent=Intent(this, StaffLoginFirstPage::class.java)
                startActivity(intent)
            }
        }
    }


    private fun validateStaffPassword(inputStaffPassword: String):Boolean {
        val p: Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=()])(?=\\S+\$).{8,20}\$")
        val m: Matcher = p.matcher(inputStaffPassword)
        if (inputStaffPassword == "") {
            binding.idStaffPassword.error = "Field is empty"
            return false
        } else if (!m.matches()) {
            binding.idStaffPassword.error = "not a valid password format"
            return false
        } else if(m.matches()){
            return true
        }
        else {
            binding.idStaffPassword.error = null
            return false
        }
    }

    private fun validateStaffId(inputStaffId: String):Boolean {
        val p: Pattern = Pattern.compile("[S][0-9]{4}")
        val m: Matcher = p.matcher(inputStaffId)
        if (inputStaffId == "") {
            binding.idStaffId.error = "Field is empty"
            return false
        } else if (!m.matches()) {
            binding.idStaffId.error= "not a valid email format"
            return false
        }  else if(m.matches()){
            return true
        }
        else {
            binding.idStaffId.error= null
            return false
        }
    }
}