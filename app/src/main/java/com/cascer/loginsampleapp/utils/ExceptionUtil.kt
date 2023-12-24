package com.cascer.loginsampleapp.utils

import com.cascer.loginsampleapp.data.remote.response.ErrorResponse
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody

object ExceptionUtil {
    private fun ResponseBody?.convertErrorBody(): ErrorResponse {
        return try {
            this?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            } ?: ErrorResponse("Unknown error has occurred. Please try again later")
        } catch (exception: Exception) {
            ErrorResponse(exception.message)
        }
    }

    private fun ErrorResponse.getMessage(): String {
        return error ?: "Unknown error has occurred. Please try again later"
    }

    fun ResponseBody?.toException(): Exception {
        return Exception(this.convertErrorBody().getMessage())
    }
}