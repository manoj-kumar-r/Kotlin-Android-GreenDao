package com.manojkumarr.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manojkumarr.greendaolibrary.models.EmployeeModel

class EmployeeAdapter(
    private var patientList: List<EmployeeModel>,
    private var customClickListener: CustomClickListener
) :
    RecyclerView.Adapter<EmployeeAdapter.PatientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        return PatientViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_emp_details,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return patientList.size
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.setData(patientList[position])
    }

    inner class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvName: TextView = itemView.findViewById(R.id.tvName)
        private var tvGender: TextView = itemView.findViewById(R.id.tvGender)
        private var tvAge: TextView = itemView.findViewById(R.id.tvAge)
        private var tvDepartment: TextView = itemView.findViewById(R.id.tvDepartment)
        init {
            itemView.setOnClickListener {
                val pos = adapterPosition
                customClickListener.onRecyclerViewItemClick(pos, patientList[pos])
            }
        }

        fun setData(employeeModel: EmployeeModel) {
            tvName.text = employeeModel.empName
            tvGender.text = employeeModel.empGender
            tvAge.text = employeeModel.empAge.toString()
            tvDepartment.text = employeeModel.empDepartment.toString()
        }
    }
}