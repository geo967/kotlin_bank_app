package com.example.bankapp_kotlin.model

data class CustomerDetails(val customerId:String,
                           val customerName:String,
                           val customerPassword:String,
                           val customerAccNo:Int,
                          val customerAccType:String,
                          val customerPhoneNo:String,
                          val customerAge:Int)
/*{
    val customerDetails = arrayListOf<CustomerDetails>()


init{

    customerDetails.add(CustomerDetails(customerId,customerName,customerPassword,customerAccNo,customerAccType,customerPhoneNo,customerAge))

}

fun addMoreCustomers(id:String,name:String,pass:String,acc:Int,type:String,no:String,age:Int){
    customerDetails.add(CustomerDetails(id, name, pass, acc, type, no, age))
}
    @JvmName("getCustomerDetails1")
    fun getCustomerDetails(): ArrayList<CustomerDetails> {
        return customerDetails
    }
}*/
