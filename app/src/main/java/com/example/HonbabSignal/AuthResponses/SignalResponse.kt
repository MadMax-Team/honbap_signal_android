package com.example.HonbabSignal.AuthResponses

import com.google.gson.annotations.SerializedName

data class SignalResponse(val isSuccess : Boolean, val code: Int, val message : String)
