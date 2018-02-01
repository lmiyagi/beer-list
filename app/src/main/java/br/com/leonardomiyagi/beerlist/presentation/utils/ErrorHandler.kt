package br.com.leonardomiyagi.beerlist.presentation.utils

import android.content.Context
import br.com.leonardomiyagi.beerlist.R
import br.com.leonardomiyagi.beerlist.data.utils.RequestException


/**
 * Created by lmiyagi on 01/02/18.
 */
class ErrorHandler(private val context: Context) {

    fun handleError(throwable: Throwable): PlaceholderData {
        throwable.printStackTrace()
        return handleError(throwable, null)
    }

    fun handleError(throwable: Throwable, tryAgainAction: Runnable?): PlaceholderData {
        throwable.printStackTrace()
        return if (throwable is RequestException) {
            handleRequestException(throwable, tryAgainAction)
        } else {
            unexpectedErrorPlaceholderData(throwable.message)
        }
    }

    fun hideAll(): PlaceholderData {
        return PlaceholderData(false, null, null, true)
    }

    fun handleRequestException(exception: RequestException, tryAgainAction: Runnable?): PlaceholderData {
        return if (exception.isNetworkError()) {
            if (exception.isTimeOutException()) {
                timeoutError(context, tryAgainAction)
            } else {
                PlaceholderData(false, context.getString(R.string.error_connection), tryAgainAction, true)
            }
        } else if (exception.isHttpError()) {
            when (exception.getHtppError()) {
                RequestException.HttpError.INTERNAL_SERVER_ERROR -> PlaceholderData(false, context.getString(R.string.error_internal_server), tryAgainAction, true)
                RequestException.HttpError.TIMEOUT -> timeoutError(context, tryAgainAction)
                else -> PlaceholderData(false, exception.getErrorMessage(), tryAgainAction, true)
            }
        } else {
            unexpectedErrorPlaceholderData(exception.message)
        }
    }

    private fun unexpectedErrorPlaceholderData(message: String?): PlaceholderData {
        return PlaceholderData(false, context.getString(R.string.error_unexpected, message), null, true)
    }

    private fun timeoutError(context: Context, tryAgainAction: Runnable?): PlaceholderData {
        return PlaceholderData(false, context.getString(R.string.error_server_timeout), tryAgainAction, true)
    }
}