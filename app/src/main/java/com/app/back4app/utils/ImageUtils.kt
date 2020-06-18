package com.app.back4app.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.net.URL


class ImageUtils {
    companion object {
        val TAG: String = "ImageUtils"
        fun newInstance() =
            ImageUtils()
    }

    /**
     * convert image url to bitmap
     */
    fun convertImageUrlToBitmap(url: String) : Bitmap{
        return BitmapFactory
            .decodeStream(
                URL(url).openConnection()
                    .getInputStream()
            )
    }

    /**
     * encode bitmap to
     */
    fun encodeToByteArray(image: Bitmap): ByteArray? {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        return baos.toByteArray()
    }

}