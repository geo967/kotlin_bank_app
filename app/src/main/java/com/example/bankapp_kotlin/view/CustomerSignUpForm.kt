package com.example.bankapp_kotlin.view

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.bankapp_kotlin.R
import com.example.bankapp_kotlin.databinding.ActivityCustomerSignUpFormBinding
import com.example.bankapp_kotlin.model.CustomerDetails
import java.time.LocalDate
import java.util.*

class CustomerSignUpForm : AppCompatActivity() {
    private var mYear = 0
    private var mMonth:Int = 0
    private var mDay:Int = 0
    private var mage:Int = 0
    private var dobYear = 0
    private var dobMonth:Int = 0
    private var dobDay:Int = 0
    var genderSelected: String? = null
    var radioButtonSelected:kotlin.String? = null
    private lateinit var binding:ActivityCustomerSignUpFormBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityCustomerSignUpFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.datePicker.setOnClickListener {
            openCalender(binding)
        }

        val gender= arrayListOf("Male", "Female", "Others")
        val adapter=ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            gender
        )
        binding.sex.adapter=adapter
        binding.sex.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                genderSelected = gender[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                genderSelected = "male"
            }
        }
        binding.radioGp.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radio_savings) {
                radioButtonSelected = "Savings"
            }
            if (checkedId == R.id.radio_current) {
                radioButtonSelected = "Current"
            }
        })

        binding.submitButton.setOnClickListener {
            saveUserDate()
            finish()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun openCalender(binding: ActivityCustomerSignUpFormBinding){
        val c = Calendar.getInstance()
        mYear = c[Calendar.YEAR]
        mMonth = c[Calendar.MONTH]
        mDay = c[Calendar.DAY_OF_MONTH]


        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val dateToSetText =
                    dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                dobYear = year
                dobMonth = monthOfYear + 1
                dobDay = dayOfMonth
                mage = mYear - dobYear
                if (dobMonth > mMonth) {
                    mage--
                } else if (mMonth == dobMonth) {
                    if (dobDay > mDay) {
                        mage--
                    }
                }
                val ageForText = "Age:$mage"
                binding.age.text = ageForText
                binding.datePicker.setText(dateToSetText)
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
        //calculateAge();

        //calculateAge();
        val myObj = LocalDate.now()
      //  val year = myObj.year
        val month = myObj.monthValue
        val day = myObj.dayOfMonth


        var mage: Int = mYear - dobYear

        if (dobMonth > month) {
            mage--
        } else if (month == dobMonth) {
            if (dobDay > day) {
                mage--
            }
        }
        val ageForText = "Age:$mage"
        binding.age.text = ageForText
    }

    private fun saveUserDate(){

        val nameOfCustomer=binding.name.text.toString()
      //  val dobOfCustomer=binding.datePicker.text.toString()
      //  val ageOfCustomer=binding.age.text
     //   val sexOfCustomer=genderSelected
        val passwordOfCustomer= binding.password.text.toString()
        val mobileOfCustomer=binding.mobileNo.text.toString()
        val accountTypeOfCustomer=radioButtonSelected.toString()

        val customerDetails: ArrayList<CustomerDetails>
        val customerLogin= CustomerLogin()
        customerDetails=customerLogin.getCustomerDetails()

        var newCustomerId:String
        var newCustomerAcc:Int
        fun getRandomCustomerId():String{
            val newRandomCustomerId = (1005..9999).random()
            newCustomerId = "C$newRandomCustomerId"
            for (i in 0 until customerDetails.size) {
                if ((customerDetails[i].customerId.toString()) == newRandomCustomerId.toString()) {
                    getRandomCustomerId()
                } else {
                    return newCustomerId
                }
            }
            return newCustomerId
        }
       fun getRandomCustomerAcc():Int {
           val newRandomCustomerAcc = (100000000..999999999).random()
           newCustomerAcc = newRandomCustomerAcc
           for (i in 0 until customerDetails.size) {
               if ((customerDetails[i].customerAccNo) == newRandomCustomerAcc) {
                   getRandomCustomerAcc()
               } else {
                   return newCustomerAcc
               }
           }
           return newCustomerAcc
       }


        customerDetails.add(
            CustomerDetails(getRandomCustomerId(),nameOfCustomer,passwordOfCustomer,
                getRandomCustomerAcc(),accountTypeOfCustomer,mobileOfCustomer,mage
            )
        )

    }
}