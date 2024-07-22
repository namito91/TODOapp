package com.sysarcomp.firstapp.todo.rvTasks

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sysarcomp.firstapp.R
import com.sysarcomp.firstapp.todo.Task
import com.sysarcomp.firstapp.todo.TaskCategory

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTask: TextView = view.findViewById(R.id.tvTask)
    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task) {

        if (task.isSelected) {
            // marca una raya sobre el item seleccionado
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        cbTask.isSelected = task.isSelected

        tvTask.text = task.name

        // se colorea cada checkbox, de acuerdo a su categoria
        val color = when (task.category) {
            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Health -> R.color.todo_health_category
            TaskCategory.Other -> R.color.todo_other_category
            TaskCategory.Personal -> R.color.todo_personal_category
        }

        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, color)
        )
    }
}