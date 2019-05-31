package com.example.androidkotlinsprintchallenge11

import android.support.annotation.WorkerThread
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object NetworkAdapter {
    interface NetworkCallback {
        fun returnResult(success: Boolean?, result: String)
    }

    @WorkerThread
    suspend fun httpRequest(urlString: String, requestType: String, content: String? = null, headerProperties: Map<String, String>? = null): String {
        var result = ""
        var stream: InputStream? = null
        var connection: HttpURLConnection? = null
        try {
            val url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection
            //                    connection.setRequestProperty("x-api-key", API_KEY);
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.readTimeout = 3000
            connection.connectTimeout = 3000
            connection.requestMethod = requestType

            if (headerProperties != null) {
                for((key, value) in headerProperties) {
                    connection.setRequestProperty(key, value)
                }
            }

            connection.doInput = true

            if (requestType == "POST" && content != null) {
                val outputStream = connection.outputStream
                outputStream.write(content.toString().toByteArray())
                outputStream.close()
            } else {
                connection.connect()
            }
            val responseCode = connection.responseCode
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                result = Integer.toString(responseCode)
                throw IOException("HTTP error code: $responseCode")
            }
            stream = connection.inputStream
            if (stream != null) {
                val reader = BufferedReader(InputStreamReader(stream))
                val builder = StringBuilder()
                var line: String? = reader.readLine()
                while (line != null) {
                    builder.append(line)
                    line = reader.readLine()
                }
                result = builder.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (stream != null) {
                try {
                    stream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            connection?.disconnect()

        }


        return result
    }

    @WorkerThread
    suspend fun httpGetRequest(urlString: String): Pair<Boolean, String> {
        var result = ""
        var success = false
        var connection: HttpURLConnection? = null
        var stream: InputStream? = null
        try {
            val url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                stream = connection.inputStream
                if (stream != null) {
                    val reader = BufferedReader(InputStreamReader(stream))
                    val builder = StringBuilder()
                    var line: String? = reader.readLine()
                    while (line != null) {
                        builder.append(line)
                        line = reader.readLine()
                    }
                    result = builder.toString()
                    success = true
                }
            } else {
                result = responseCode.toString()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            connection?.disconnect()

            if (stream != null) {
                try {
                    stream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            return success to result
        }
    }

    fun httpGetRequest(urlString: String, callback: NetworkCallback) {
        Thread(Runnable {
            var result = ""
            var success = false
            var connection: HttpURLConnection? = null
            var stream: InputStream? = null
            try {
                val url = URL(urlString)
                connection = url.openConnection() as HttpURLConnection
                connection.connect()

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    stream = connection.inputStream
                    if (stream != null) {
                        val reader = BufferedReader(InputStreamReader(stream))
                        val builder = StringBuilder()
                        var line: String? = reader.readLine()
                        while (line != null) {
                            builder.append(line)
                            line = reader.readLine()
                        }
                        result = builder.toString()
                        success = true
                    }
                } else {
                    result = responseCode.toString()
                }
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()

                if (stream != null) {
                    try {
                        stream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }

                callback.returnResult(success, result)
            }
        }).start()
    }
}