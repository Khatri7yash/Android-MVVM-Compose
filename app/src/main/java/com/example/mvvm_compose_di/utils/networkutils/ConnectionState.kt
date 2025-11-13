package com.example.mvvm_compose_di.utils.networkutils

sealed class ConnectionState {
    data object Available : ConnectionState()
    data object Unavailable : ConnectionState()
}