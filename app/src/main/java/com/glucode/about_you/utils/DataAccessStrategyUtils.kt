package com.glucode.about_you.utils

import com.glucode.about_you.enums.Status

object DataAccessStrategyUtils {
    suspend inline fun <A, T> lazyCache(
        crossinline dbQuery: suspend () -> Resource<T>,
        crossinline wsCall: suspend () -> Resource<A>,
        crossinline saveCall: suspend (A) -> Unit
    ): Resource<T> {
        val result = dbQuery.invoke()
        return if (result.data != null) {
            result
        } else {
            val response = wsCall.invoke()
            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.let { saveCall(it) }
                    dbQuery.invoke()
                }

                Status.ERROR -> Resource.error(response.message)
                else -> Resource.error("No data found.")
            }
        }
    }
}