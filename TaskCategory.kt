package com.sysarcomp.firstapp.todo


// var isSelected:Boolean = false -> agrega este atributo a todos los object
sealed class TaskCategory(var isSelected:Boolean = false) {

    object Personal:TaskCategory()

    object Business:TaskCategory()

    object Health:TaskCategory()

    object Other:TaskCategory()
}