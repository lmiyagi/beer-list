package br.com.leonardomiyagi.beerlist.presentation.utils

import android.os.Environment
import br.com.leonardomiyagi.beerlist.domain.utils.ImageManager

/**
 * Created by lmiyagi on 2/4/18.
 */
class DefaultImageManager : ImageManager {

    override fun getStorageDir(): String {
        return Environment.getExternalStorageDirectory().absolutePath
    }
}