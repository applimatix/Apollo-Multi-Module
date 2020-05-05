package com.example.apollomultimodule.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.apollomultimodule.databinding.ActivityEmployeeListBinding
import com.example.apollomultimodule.viewmodels.EmployeeListViewModel
import kotlinx.android.synthetic.main.activity_employee.*
import kotlinx.android.synthetic.main.activity_employee_list.*
import kotlinx.android.synthetic.main.activity_employee_list.recyclerView
import kotlinx.android.synthetic.main.activity_employee_list.toolbar

class EmployeeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeListBinding

    private val viewModel: EmployeeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmployeeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this@EmployeeListActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        with(viewModel) {
            employeeController.observe(this@EmployeeListActivity, Observer {
                recyclerView.setControllerAndBuildModels(it)
            })

            actions.observe(this@EmployeeListActivity, Observer {
                when(it) {
                    is EmployeeListViewModel.Actions.ShowEmployee -> {
                        val intent = Intent(this@EmployeeListActivity, EmployeeActivity::class.java)
                        intent.putExtra(EmployeeActivity.EXTRA_EMPLOYEE_ID, it.employee.id)
                        startActivity(intent)
                    }
                    is EmployeeListViewModel.Actions.ShowError -> {
                        Toast.makeText(this@EmployeeListActivity, it.exception.message, Toast.LENGTH_LONG).show()
                    }
                }
            })

            intent.getStringExtra(EXTRA_COMPANY_ID)?.let {
                fetchEmployees(it)
            }
        }
    }

    companion object {
        const val EXTRA_COMPANY_ID = "extra_company_id"
    }
}
