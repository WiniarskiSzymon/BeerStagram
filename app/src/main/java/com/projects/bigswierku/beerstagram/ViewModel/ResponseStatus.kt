package com.projects.bigswierku.beerstagram.ViewModel

class ResponseStatus (val status: Status, val errorMessage : String? = "")

enum class Status{
    LOADING,SUCCESS,ERROR
}