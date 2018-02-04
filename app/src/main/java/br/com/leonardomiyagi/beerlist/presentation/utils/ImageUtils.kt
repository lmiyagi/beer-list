package br.com.leonardomiyagi.beerlist.presentation.utils

import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream


/**
 * Created by lmiyagi on 2/4/18.
 */
class ImageUtils {
    companion object {
        fun bitmapToFile(bitmap: Bitmap?, path: String): File? {
            if (bitmap == null) return null
            val file = File(path)
            try {
                val outputStream = FileOutputStream(file)

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.flush()
                outputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
            return file
        }
    }
}