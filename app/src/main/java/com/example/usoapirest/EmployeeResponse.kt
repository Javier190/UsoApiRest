package com.example.usoapirest

import com.google.gson.annotations.SerializedName

data class EmployeeResponse (@SerializedName("status") var status:String,
                             @SerializedName("data") var data: Employee,
                             @SerializedName("message") var message:String
                             )

//@SerializedName("employee") var list_employee: List<EmployeeResponse>)

