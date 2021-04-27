package com.example.usoapirest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(var list_employee: List<Employee>) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list_employee[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.lista_empleados, parent, false))
    }

    override fun getItemCount(): Int {
        return list_employee.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val id = itemView.findViewById(R.id.tv_id) as TextView
        private val employee_name = itemView.findViewById(R.id.tv_name) as TextView
        private val employee_salary = itemView.findViewById(R.id.tv_salary) as TextView
        private val employee_age = itemView.findViewById(R.id.tv_age) as TextView
        private val profile_image = itemView.findViewById(R.id.tv_profileimg) as TextView

        fun bind(employee: Employee) {
            id.text = employee.id
            employee_name.text = employee.employee_name
            employee_salary.text = employee.employee_salary
            employee_age.text = employee.employee_age
            profile_image.text = employee.profile_image
        }
    }
}
