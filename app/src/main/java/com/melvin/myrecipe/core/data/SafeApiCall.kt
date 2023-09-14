package com.melvin.myrecipe.core.data

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response: Response<T> = apiToBeCalled()

            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(data = it)
                } ?: Resource.Error(errorMessage = "Null body")
            } else {
                val errorResponse: ErrorResponse? = convertErrorBody(response.errorBody())
                Resource.Error(errorMessage = errorResponse?.message ?: "Something went wrong")
            }

        } catch (e: HttpException) {
            Resource.Error(errorMessage = e.message ?: "Something went wrong")
        } catch (e: IOException) {
            Resource.Error("Please check your network connection")
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Resource.Error(errorMessage = "Something went wrong")
        }
    }
}

private fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
    return try {
        errorBody?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}

data class ErrorResponse(
    val message: String = ""
)