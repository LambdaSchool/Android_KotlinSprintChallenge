package com.isra.israel.sprint11

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class NetworkAdapter {
    companion object {
        const val READ_TIMEOUT = 3000
        const val CONNECT_TIMEOUT = 3000

        const val GET = "GET"
        const val POST = "POST"
        const val HEAD = "HEAD"
        const val OPTIONS = "OPTIONS"
        const val PUT = "PUT"
        const val DELETE = "DELETE"
        const val TRACE = "TRACE"

        fun httpsRequest(urlString: String, requestMethod: String, requestBody: String?, headerProperties: HashMap<String, String>?, responseCallback: (Int, String?)->Unit) {
            var httpsURLConnection: HttpsURLConnection? = null

            try {
                val url = URL(urlString)
                httpsURLConnection = url.openConnection() as HttpsURLConnection
                httpsURLConnection.readTimeout = READ_TIMEOUT
                httpsURLConnection.connectTimeout = CONNECT_TIMEOUT
                httpsURLConnection.requestMethod = requestMethod

                headerProperties?.forEach {
                    httpsURLConnection.setRequestProperty(it.key, it.value)
                }

                when {
                    requestMethod == POST || requestMethod == PUT && requestBody != null -> {
                        // write request body to output stream
                        httpsURLConnection.doInput = true
                        val data = requestBody.toString()
                        httpsURLConnection.outputStream.write(data.toByteArray())
                        httpsURLConnection.outputStream.close()
                        if (httpsURLConnection.responseCode < 200 || httpsURLConnection.responseCode > 299) {
                            responseCallback(httpsURLConnection.responseCode, null)
                            return
                        }
                    }
                    else -> {
                        httpsURLConnection.connect()
                    }
                }

                if (httpsURLConnection.inputStream == null) {
                    responseCallback(httpsURLConnection.responseCode, null)
                    return
                }

                val reader = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val builder = StringBuilder()

                reader.forEachLine {
                    builder.append(it)
                }

                responseCallback(httpsURLConnection.responseCode, builder.toString())

            } catch (e: MalformedURLException) {
                e.printStackTrace()
                responseCallback(-1, null)
            } catch (e: IOException) {
                e.printStackTrace()
                responseCallback(-1, null)
            } finally {
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect()

                    try {
                        if (httpsURLConnection.inputStream != null) {
                            httpsURLConnection.inputStream.close()
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        fun httpRequest(urlString: String, requestMethod: String, requestBody: String?, headerProperties: HashMap<String, String>?, responseCallback: (Int, String?)->Unit) {
            var httpsURLConnection: HttpURLConnection? = null

            try {
                val url = URL(urlString)
                httpsURLConnection = url.openConnection() as HttpURLConnection
                httpsURLConnection.readTimeout = READ_TIMEOUT
                httpsURLConnection.connectTimeout = CONNECT_TIMEOUT
                httpsURLConnection.requestMethod = requestMethod

                headerProperties?.forEach {
                    httpsURLConnection.setRequestProperty(it.key, it.value)
                }

                when {
                    requestMethod == POST || requestMethod == PUT && requestBody != null -> {
                        // write request body to output stream
                        httpsURLConnection.doInput = true
                        val data = requestBody.toString()
                        httpsURLConnection.outputStream.write(data.toByteArray())
                        httpsURLConnection.outputStream.close()
                        if (httpsURLConnection.responseCode < 200 || httpsURLConnection.responseCode > 299) {
                            responseCallback(httpsURLConnection.responseCode, null)
                            return
                        }
                    }
                    else -> {
                        httpsURLConnection.connect()
                    }
                }

                if (httpsURLConnection.inputStream == null) {
                    responseCallback(httpsURLConnection.responseCode, null)
                    return
                }

                val reader = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val builder = StringBuilder()

                reader.forEachLine {
                    builder.append(it)
                }

                responseCallback(httpsURLConnection.responseCode, builder.toString())

            } catch (e: MalformedURLException) {
                e.printStackTrace()
                responseCallback(-1, null)
            } catch (e: IOException) {
                e.printStackTrace()
                responseCallback(-1, null)
            } finally {
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect()

                    try {
                        if (httpsURLConnection.inputStream != null) {
                            httpsURLConnection.inputStream.close()
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        fun isSuccessful(code: Int) = code in 200..299
    }
}