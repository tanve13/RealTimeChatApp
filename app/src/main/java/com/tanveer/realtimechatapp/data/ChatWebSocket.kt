package com.tanveer.realtimechatapp.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ChatWebSocket {
    private val _message: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val message: StateFlow<List<String>> = _message

    private val client = OkHttpClient()
    private lateinit var webSocket: WebSocket
     //webSocket :: hold connections
     //lateinit ::used to initalize websocket to connect
     //WebSocketListener :: response ko attach krk daina
    fun connect(){
        val request = Request.Builder().url("wss://echo.webSocket.events").build()
        webSocket = client.newWebSocket(request,object : WebSocketListener() {
           override fun onMessage(ws: WebSocket, text: String) {
                val updated = _message.value + "Friend: $text"
                _message.value = updated
            }

            override fun onFailure(ws: WebSocket, t: Throwable, response: Response?) {
                println("WebSocket Failed : ${t.message}")
            }
        })
    }



}