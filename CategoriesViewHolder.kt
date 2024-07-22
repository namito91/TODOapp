package com.sysarcomp.firstapp.todo.rvCategories

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sysarcomp.firstapp.R
import com.sysarcomp.firstapp.todo.TaskCategory

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCatName: TextView = view.findViewById(R.id.tvCatName)
    private val divider: View = view.findViewById(R.id.divider)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        // pinta la categoria, al ser seleccionada
        val color = if (taskCategory.isSelected) {
            R.color.todo_background_disabled
        } else {
            R.color.todo_background_card
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context,color))

        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        when (taskCategory) {

            TaskCategory.Business -> {
                tvCatName.text = "Negocios"

                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_business_category)
                )
            }

            TaskCategory.Health -> {
                tvCatName.text = "Salud"

                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_health_category)
                )
            }

            TaskCategory.Other -> {
                tvCatName.text = "Otros"

                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_other_category)
                )
            }

            TaskCategory.Personal -> {
                tvCatName.text = "Personal"

                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_personal_category)
                )
            }
        }
    }
}