package com.wisdomrider.sqliteclosedhelperr

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64

import com.wisdomrider.sqliteclosedhelper.SqliteClosedHelper
import java.io.ByteArrayOutputStream
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.Toast
import com.wisdomrider.sqliteclosedhelper.SQLITECONSTANTS


class MainActivity : AppCompatActivity() {

    class App(var name: String,
              var link: String,
              var icon: String) {
        constructor() : this("", "", "")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val helper = SqliteClosedHelper(this, "DBNAME")
        helper.createTable(App("", "", ""))
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        var apps = getPackageManager().queryIntentActivities(mainIntent, 0)
        helper.removeAll(App())
        helper.delete(App(), SQLITECONSTANTS.AND)
        apps.forEachIndexed { index, it ->
            var label = it.activityInfo.loadLabel(packageManager)
            var icon = drawableToBitmap(it.loadIcon(packageManager))!!
            val bao = ByteArrayOutputStream()
            icon.compress(Bitmap.CompressFormat.JPEG, 90, bao)
            val ba = bao.toByteArray()
            val ba1 = Base64.encodeToString(ba, Base64.NO_WRAP)
            val item =
                    App(label.toString().replace("'", "\""), it.activityInfo.applicationInfo.publicSourceDir, ba1)
            helper.insertTable(item)

        }

        helper.getAll(App("", "", "")).forEach {
            Toast.makeText(this@MainActivity, it.name, Toast.LENGTH_SHORT).show()
        }

    }


    fun drawableToBitmap(drawable: Drawable): Bitmap? {
        var bitmap: Bitmap? = null

        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }

        if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }
}
