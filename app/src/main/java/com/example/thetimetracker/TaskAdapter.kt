package com.example.thetimetracker
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var tasks: MutableList<Task> = mutableListOf()
    private var editClickListener: OnEditClickListener? = null

    interface OnEditClickListener {
        fun onEditClick(task: Task)
    }

    fun setOnEditClickListener(listener: OnEditClickListener?) {
        this.editClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
        holder.btnDelete.setOnClickListener {
            removeTask(tasks[position].id)
        }
        holder.btnEdit.setOnClickListener {
            if (position < tasks.size) {
                editClickListener?.onEditClick(tasks[position])
            }
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun updateTasks(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }

    private fun removeTask(id: Int) {
        val databaseHelper = DatabaseHelper(context)
        val deletedRows = databaseHelper.deleteTask(id)
        if (deletedRows > 0) {
            // Remove the task from the dataset
            tasks.removeAll { it.id == id }
            // Notify the adapter about the item removal
            notifyDataSetChanged()
        }
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewTaskName)
        private val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)
        private val startTimeTextView: TextView = itemView.findViewById(R.id.textViewStartTime)
        private val endTimeTextView: TextView = itemView.findViewById(R.id.textViewEndTime)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
        private val textCategoryTextView: TextView = itemView.findViewById(R.id.textCategory)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)

        fun bind(task: Task) {
            nameTextView.text = task.name
            dateTextView.text = task.date
            startTimeTextView.text = task.startTime
            endTimeTextView.text = task.endTime
            descriptionTextView.text = task.description
            textCategoryTextView.text = task.category
        }
    }
}