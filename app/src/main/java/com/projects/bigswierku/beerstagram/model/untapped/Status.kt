package com.projects.bigswierku.beerstagram.model.untapped

class ResponseStatus (val status: Status, val errorMessage : String? = "")

enum class Status{
    LOADING,SUCCESS,ERROR
}