package com.sysarcomp.firstapp.todo

data class Task(val name: String, val category: TaskCategory, var isSelected: Boolean = false)

