package com.beone.kevin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

object CustomImageUtils {

    fun stringToBitmap(imageString: String): Bitmap {
        try {
            val encodeByte = Base64.decode(imageString,Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.size)
            return bitmap
        }catch (e:Exception){
            val bitmap:Bitmap =  Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888)
            return bitmap
        }
    }

    fun BitmapToString(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,60,byteArrayOutputStream)
        val bytedata =  byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytedata,Base64.DEFAULT)

    }


}