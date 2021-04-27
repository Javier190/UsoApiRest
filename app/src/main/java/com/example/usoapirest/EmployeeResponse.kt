package com.example.usoapirest

import com.google.gson.annotations.SerializedName

data class EmployeeResponse (@SerializedName("status") var status:String,
                             @SerializedName("message") var message:String,
                             @SerializedName("data") var data:List<Employee>)

//@SerializedName("employee") var list_employee: List<EmployeeResponse>)

