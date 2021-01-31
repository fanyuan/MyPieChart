package com.convert.mypiechart

import android.content.Context
import android.widget.Toast

class Utils {
    companion object{
        @JvmStatic
        fun toast(context: Context,msg:String){
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
        }
    }
}