package com.sysarcomp.firstapp.todo.rvTasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sysarcomp.firstapp.R
import com.sysarcomp.firstapp.todo.Task


class TasksAdapter(var tasks: List<Task>, private val onTaskSelected: (Int) -> Unit) :
    RecyclerView.Adapter<TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task, parent, false)

        return TasksViewHolder(view)
    }

    //    onBindViewHolder se usa para actualizar un ViewHolder con los datos
    //    correspondientes a una posición específica en el conjunto de datos.
    //    Este método se llama cada vez que un ViewHolder necesita mostrar nuevos datos.
    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.render(tasks[position])

        // se marca la tarea , solo cuando se clickea sobre ella,
        holder.itemView.setOnClickListener { onTaskSelected(position) }
    }

    override fun getItemCount() = tasks.size
}