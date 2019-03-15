package com.jbseppanen.android_kotlinsprintchallenge

import android.content.Context
import android.support.annotation.WorkerThread
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object NetworkAdapter {
    const val GET = "GET"
    const val POST = "POST"
    const val PUT = "PUT"
    const val DELETE = "DELETE"
    const val TIMEOUT = 3000
    const val RESPONSE_STRING = "String Response"
    const val RESPONSE_VIDEO = "Video Response"


    @WorkerThread
    fun httpRequest(stringUrl: String, requestType: String, expectedResponseType: String, context: Context?, jsonBody: String?, headerProperties: Map<String, String>? = null): Pair<Boolean, String> {
        var result = ""
        var success = false
        var stream: InputStream? = null
        var connection: HttpURLConnection? = null

//        val file: File? = File(context!!.cacheDir.path + "/" + "SpaceVideo")
        try {
            val url = URL(stringUrl)
            connection = url.openConnection() as HttpURLConnection
            connection.readTimeout = TIMEOUT
            connection.connectTimeout = TIMEOUT
            connection.requestMethod = requestType
            connection.setRequestProperty("Content-Type", "application/json")

            if (headerProperties != null) {
                for ((key, value) in headerProperties) {
                    connection.setRequestProperty(key, value)
                }
            }

            if (requestType == GET || requestType == DELETE) {
                connection.connect()
            } else if (requestType == POST || requestType == PUT) {
                if (jsonBody != null) {
                    val outputStream = connection.outputStream
                    outputStream.write(jsonBody.toByteArray())
                    outputStream.close()
                }
            }

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                stream = connection.inputStream
                if (stream != null) {
                    if (expectedResponseType == RESPONSE_STRING) {
                        val reader = BufferedReader(InputStreamReader(stream))
                        val builder = StringBuilder()
                        var line: String? = reader.readLine()
                        while (line != null) {
                            builder.append(line)
                            line = reader.readLine()
                            success = true
                        }
                        result = builder.toString()
                    } /*else if (expectedResponseType == RESPONSE_VIDEO) {
                        val fileStream = FileOutputStream(file)

                        val buffer = ByteArray(1024)
                        var len1 = 0
                        stream.read(buffer)
                        while (len1 > 0) {
                            fileStream.write(buffer, 0, len1)
                            len1 = stream.read(buffer)
                        }
                        fileStream.close()
                        success = true
                    }*/
                }
            }

        } catch (e: MalformedURLException) {
            e.printStackTrace()
            result = e.message.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            result = e.message.toString()
        } finally {
            connection?.disconnect()

            if (stream != null) {
                try {
                    stream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return if (expectedResponseType == RESPONSE_STRING) {
            success to result
        } else {
/*            if (file != null) {
                success to file.absolutePath
            } else {*/
                success to ""
//            }
        }
    }


}
