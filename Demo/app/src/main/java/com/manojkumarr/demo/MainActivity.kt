package com.manojkumarr.demo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.manojkumarr.greendaolibrary.GreenDaoDataBase
import com.manojkumarr.greendaolibrary.database.DbHelper
import com.manojkumarr.greendaolibrary.database.converters.EmpType
import com.manojkumarr.greendaolibrary.models.EmployeeModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), CustomClickListener {

    //region Custom Listener
    private val mOnItemSelectedListener = object : OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            toggleButtonVisibility()
        }
    }
    private val mTextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            toggleButtonVisibility()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    }
    //endregion Custom Listener

    //region Variable
    private lateinit var dbHelper: DbHelper
    private var employeeModel: EmployeeModel? = null
    private var employeeModelList: List<EmployeeModel> = ArrayList()
    private var genderArray: List<String> = ArrayList()
    private var deptArray: List<String> = ArrayList()
    private var empTypeArray: List<String> = ArrayList()
    //endregion Variable

    //region Override Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setVariable()
        clearData()
        fetchDataFromDb()
    }

    override fun onRecyclerViewItemClick(position: Int, anyObject: Any) {

    }
    //endregion Override Methods


    //region Private Methods
    private fun setVariable() {

        etEmpName.addTextChangedListener(mTextWatcher)

        etAge.addTextChangedListener(mTextWatcher)

        dbHelper = GreenDaoDataBase.getDataBase(this@MainActivity, "EmpDb", "TestPassword")

        spGender.onItemSelectedListener = mOnItemSelectedListener

        spDepartment.onItemSelectedListener = mOnItemSelectedListener

        spEmpType.onItemSelectedListener = mOnItemSelectedListener

        btSave.setOnClickListener {
            saveData()
        }

        btCancel.setOnClickListener {
            clearData()
        }

        rvEmpList.layoutManager = LinearLayoutManager(this@MainActivity)

        genderArray = listOf(
            getString(R.string.select),
            getString(R.string.male),
            getString(R.string.fe_male)
        )

        deptArray = listOf(
            getString(R.string.select),
            getString(R.string.information),
            getString(R.string.network),
            getString(R.string.development)
        )

        empTypeArray = listOf(
            getString(R.string.select),
            getString(R.string.hr),
            getString(R.string.manager),
            getString(R.string.employee)
        )

        spGender.adapter = ArrayAdapter(this, R.layout.drop_down_textview, genderArray)

        spDepartment.adapter = ArrayAdapter(this, R.layout.drop_down_textview, deptArray)

        spEmpType.adapter = ArrayAdapter(this, R.layout.drop_down_textview, empTypeArray)

        etEmpName.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, _, position, _ ->
                val model = adapterView.adapter.getItem(position) as EmployeeModel
                employeeModel = employeeModelList[employeeModelList.indexOf(model)]
                setData()
            }
    }

    private fun fetchDataFromDb() {
        employeeModelList = ArrayList()
        employeeModelList = dbHelper.allEmployees
        rvEmpList.adapter = EmployeeAdapter(employeeModelList, this@MainActivity)

        etEmpName.setAdapter(
            ArrayAdapter<EmployeeModel>(
                this,
                R.layout.drop_down_textview,
                employeeModelList
            )
        )
    }

    private fun setData() {
        employeeModel?.let {
            etEmpName.setText(it.empName.toString())
            etAge.setText(it.empAge.toString())

            spGender.setSelection(genderArray.indexOf(it.empGender))

            spDepartment.setSelection(deptArray.indexOf(it.empDepartment))

            it.empType.let { type ->
                val typeText = when (type) {
                    EmpType.DEFAULT -> {
                        getString(R.string.select)
                    }
                    EmpType.EMPLOYEE -> {
                        getString(R.string.employee)
                    }
                    EmpType.MANAGER -> {
                        getString(R.string.manager)
                    }
                    EmpType.HR -> {
                        getString(R.string.hr)
                    }
                }
                spEmpType.setSelection(empTypeArray.indexOf(typeText))
            }
        }
    }

    private fun toggleButtonVisibility() {
        val buttonVisible = when {
            etEmpName.length() == 0 -> {
                false
            }
            etAge.length() == 0 -> {
                false
            }
            spGender.selectedItemPosition == 0 -> {
                false
            }
            spDepartment.selectedItemPosition == 0 -> {
                false
            }
            spEmpType.selectedItemPosition == 0 -> {
                false
            }
            else -> {
                true
            }
        }

        btSave.visibility = if (buttonVisible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    private fun clearData() {
        etEmpName.setText("")
        etAge.setText("")
        spGender.setSelection(0)
        spDepartment.setSelection(0)
        spEmpType.setSelection(0)
        etEmpName.requestFocus()
        employeeModel = null
    }

    private fun saveData() {
        if (this::dbHelper.isInitialized) {
            val empModel = employeeModel ?: kotlin.run {
                val model = EmployeeModel()
                model.id = UUID.randomUUID().toString()
                model
            }

            empModel.empName = etEmpName.text.toString()
            empModel.empAge = etAge.text.toString().toInt()
            empModel.empGender = spGender.selectedItem.toString()
            empModel.empDepartment = spDepartment.selectedItem.toString()

            empModel.empType = when (spEmpType.selectedItem.toString()) {
                getString(R.string.hr) -> {
                    EmpType.HR
                }
                getString(R.string.manager) -> {
                    EmpType.MANAGER
                }
                getString(R.string.employee) -> {
                    EmpType.EMPLOYEE
                }
                else -> {
                    EmpType.DEFAULT
                }
            }

            dbHelper.insertEmployeeModel(empModel)
            clearData()
            fetchDataFromDb()
        }
    }
    //endregion Private Methods
}