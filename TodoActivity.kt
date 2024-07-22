package com.sysarcomp.firstapp.todo

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sysarcomp.firstapp.R
import com.sysarcomp.firstapp.todo.rvCategories.CategoriesAdapter
import com.sysarcomp.firstapp.todo.rvTasks.TasksAdapter

class TodoActivity : AppCompatActivity() {

    private val categories = listOf(
        TaskCategory.Personal,
        TaskCategory.Business,
        TaskCategory.Health,
        TaskCategory.Other
    )

    private val tasks = mutableListOf(
        Task("Prueba business", TaskCategory.Business),
        Task("Prueba Personal", TaskCategory.Personal),
        Task("Prueba Health", TaskCategory.Health)

    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var catAdapter: CategoriesAdapter

    private lateinit var rvTasks: RecyclerView
    private lateinit var taskAdapter: TasksAdapter

    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_todo)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponent()

        initUI()

        initListeners()
    }

    private fun initListeners() {

        fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            // se captura el radio button seleccionado por el usuario
            // atraves de su id
            val currentTask = etTask.text.toString()

            if (currentTask.isNotEmpty()) {

                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)

                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.todo_dialog_cat_business) -> TaskCategory.Business
                    getString(R.string.todo_dialog_cat_personal) -> TaskCategory.Personal
                    getString(R.string.todo_dialog_cat_health) -> TaskCategory.Health
                    else -> TaskCategory.Other
                }

                tasks.add(Task(etTask.text.toString(), currentCategory))
                updateTasks() // actualiza las tareas en la vista
                dialog.hide()
            }
        }

        dialog.show()
    }

    private fun initUI() {

        catAdapter = CategoriesAdapter(categories) { updateCategories(it) }

        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rvCategories.adapter = catAdapter

        // TasksAdapter(tasks , { onItemSelected(it) }) se pasa una funcion lambda como 2do parametro
        // position -> onItemSelected(position)
        taskAdapter = TasksAdapter(tasks, { onItemSelected(it) })
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = taskAdapter
    }

    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }


    private fun onItemSelected(position: Int) {

        // cambiar estado de tarea seleccionada,
        // seleccionada -> no seleccionada
        // no  seleccionada -> seleccionada
        tasks[position].isSelected = !tasks[position].isSelected

        // necesito actualizar solo el item que se ha seleccionado en la vista(y no todos los items)
        // se debe modificar el task adapter para eso !!
        updateTasks()
    }

    private fun updateCategories(position: Int) {

        categories[position].isSelected = !categories[position].isSelected
        catAdapter.notifyItemChanged(position)
        updateTasks()
    }

    private fun updateTasks() {

        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        taskAdapter.tasks = newTasks

        // avisa al adaptador que hay nuevas tareas,
        // para asi,ponerlas en la vista
        // no es la forma mas optima(chequea todos los items uno a uno,y no el que se actualizo/inserto)
        taskAdapter.notifyDataSetChanged()
    }

}