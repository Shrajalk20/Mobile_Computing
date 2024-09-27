package com.example.project1_mobilecomputing.classes

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import com.example.project1_mobilecomputing.MainActivity
import kotlin.math.min

class heartratecaller {
    var result : Int = 0

    @RequiresApi(Build.VERSION_CODES.P)
    fun runcalculator(context: Context, fileName :String): Int
    {
        Log.d("MediaPath", "getVideoByName: $fileName")
       // val queryUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

       // Log.d("MediaPath", "getVideoByName: $queryUri")
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DURATION
        )
        ActivityCompat.requestPermissions(
            context as MainActivity,
            arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_MEDIA_VIDEO
            ),
            0
        )
        Log.d("MediaPath", "Permission granted")


        val selection: String = MediaStore.Video.Media.DISPLAY_NAME+ " == ?"
        Log.d("MediaPath", "getVideoByName: $selection")
        val selectionArgs: Array<String> = arrayOf("HeartRate.mp4")
        Log.d("MediaPath", "getVideoByName: $selectionArgs")
        val mediaFile = mutableListOf<MediaFile>()
//        Log.d("MediaPath", "getVideoByName: $mediaFile")
        context.contentResolver.query(
            Uri.parse(MediaStore.Video.Media.EXTERNAL_CONTENT_URI.toString()), projection, selection, selectionArgs, "${MediaStore.Video.Media.DISPLAY_NAME} ASC"

        )?.use { cursor ->
            Log.d("MediaPath", "cursor: $cursor")
            val idcolumn = cursor.getColumnIndexOrThrow(

                MediaStore.Video.Media._ID
            )
            Log.d("MediaPath", "idcolumn: $idcolumn")
            val namecolumn = cursor.getColumnIndexOrThrow(

                MediaStore.Video.Media.DISPLAY_NAME
            )
            Log.d("MediaPath", "namecolumn: $namecolumn")

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idcolumn)
                Log.d("MediaPath", "id: $id")
                val name = cursor.getString(namecolumn)
                Log.d("MediaPath", "name: $name")
//                val durationn = cursor.getLong(duration)
                val contenturi = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id)
                Log.d("MediaPath", "contenturi: $contenturi")
                // mediaFile.add(MediaFile(id, name, contenturi))
//                Log.d("MediaPath", "Duration of video : $durationn")
//                println("URI of file: $name -> $contenturi")

                val contentResolver = context.contentResolver
                Log.d("MediaPath", "ContentResolver: $contentResolver")
//                Log.d("MediaPath", "C2: $contentResolver")
//                println("C2: $contentResolver")
                result = heartRateCalculator(contenturi,contentResolver)
                Log.d("MediaPath", "Result: $result")
            }
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun heartRateCalculator(contenturi: Uri, contentresolver: ContentResolver?): Int {

        val result: Int
        Log.d("MediaPath", "Inside heart rate calculator uri : $?contenturi and content reslover : $contentresolver")
        val proj = arrayOf(MediaStore.Video.Media.DATA)
        Log.d("MediaPath", "Inside heart rate calculator uri : $contenturi and content reslover : $contentresolver")
        val cursor = contentresolver?.query(contenturi, proj, null, null, null)
        val columnIndex =
            cursor?.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
        Log.d("MediaPath", "Inside heart rate calculator, column index : $columnIndex")
        cursor?.moveToFirst()
        val path = cursor?.getString(columnIndex ?: 0)
        Log.d("MediaPath", "Inside heart rate calculator path : $path")
        cursor?.close()
//        Log.d("Medi?aPath", "Inside heart rate calculator uri : $contenturi and content reslover : $contentresolver")
//        Log.d("MediaPath", "Inside heart rate calculator ")
        val retriever = MediaMetadataRetriever()
        Log.d("MediaPath", "Inside heart rate calculator retriever : $retriever")
        val frameList = ArrayList<Bitmap>()
        Log.d("MediaPath", "Inside heart rate calculator framelist : $frameList")
        try {
            retriever.setDataSource(path)
            val duration =
                retriever.extractMetadata(
                    MediaMetadataRetriever.METADATA_KEY_VIDEO_FRAME_COUNT
                )
            Log.d("MediaPath", "Inside heart rate calculator duration : $duration")
            val frameDuration = min(duration!!.toInt(), 425)
            Log.d("MediaPath", "Inside heart rate calculator frame duration : $frameDuration")
            var i = 10
            while (i < frameDuration) {
                val bitmap = retriever.getFrameAtIndex(i)
                bitmap?.let { frameList.add(it) }
                i += 15
                Log.d("MediaPath", "Inside heart rate calculator i : $i")
            }
        } catch (e: Exception) {
            Log.d("MediaPath", "convertMediaUriToPath: ${e.stackTrace} ")
        } finally {
            retriever.release()
            var redBucket: Long
            var pixelCount: Long = 0
            val a = mutableListOf<Long>()
            for (i in frameList) {
                redBucket = 0
                for (y in 350 until 450) {
                    for (x in 350 until 450) {
                        val c: Int = i.getPixel(x, y)
                        pixelCount++
                        redBucket += Color.red(c) + Color.blue(c) +
                                Color.green(c)
                    }
                }
                a.add(redBucket)
            }
            val b = mutableListOf<Long>()
            for (i in 0 until a.lastIndex - 5) {
                val temp =
                    (a.elementAt(i) + a.elementAt(i + 1) + a.elementAt(i + 2)
                            + a.elementAt(
                        i + 3
                    ) + a.elementAt(
                        i + 4
                    )) / 4
                b.add(temp)
            }
            var x = b.elementAt(0)
            var count = 0
            for (i in 1 until b.lastIndex) {
                val p = b.elementAt(i)
                if ((p - x) > 3500) {
                    count += 1
                }
                x = b.elementAt(i)
            }
            val rate = ((count.toFloat()) * 60).toInt()
            result = (rate / 4)
            println("Heart Rate :  $result")
        }
        Log.d("MediaPath", "Inside heart rate calculator result : $result")
        return result
    }

}