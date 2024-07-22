package com.sysarcomp.firstapp.todo.rvCategories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sysarcomp.firstapp.R
import com.sysarcomp.firstapp.todo.TaskCategory

class CategoriesAdapter(
    private var categories: List<TaskCategory>,
    private val onItemSelected: (Int) -> Unit
) :
    RecyclerView.Adapter<CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)

        return CategoriesViewHolder(view)
    }

    // retorna el tamaño de la lista de categorias
    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {

        holder.render(categories[position],onItemSelected)

    }
}