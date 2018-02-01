package br.com.leonardomiyagi.beerlist.presentation.utils

import android.content.Context
import br.com.leonardomiyagi.beerlist.R

/**
 * Created by lmiyagi on 01/02/18.
 */
class PlaceholderData(public val loading: Boolean = false,
                      public val message: String? = null,
                      public val action: Runnable? = null,
                      public val visible: Boolean) {

    public fun onActionClicked() {
        action?.run()
    }

    companion object {

        public fun loading(context: Context): PlaceholderData {
            return PlaceholderData(true, context.getString(R.string.global_loading), null, true)
        }

        public fun empty(context: Context): PlaceholderData {
            return PlaceholderData(false, context.getString(R.string.global_empty_list), null, true)
        }

        public fun error(message: String, tryAgain: Runnable?): PlaceholderData {
            return PlaceholderData(false, message, tryAgain, true)
        }

        public fun hide(): PlaceholderData {
            return PlaceholderData(false, null, null, false)
        }
    }
}