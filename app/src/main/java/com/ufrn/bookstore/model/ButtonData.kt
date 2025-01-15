package com.ufrn.bookstore.model

data class ButtonData(
    val label: String,
    val onClick: () -> Unit
)