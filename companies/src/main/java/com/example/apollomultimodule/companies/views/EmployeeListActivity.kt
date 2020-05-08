package com.example.apollomultimodule.companies.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.apollomultimodule.base.navigation.Navigation
import com.example.apollomultimodule.companies.databinding.ActivityEmployeeListBinding
import com.example.apollomultimodule.companies.viewmodels.EmployeeListViewModel
import kotlinx.android.synthetic.main.activity_employee_list.recyclerView
import kotlinx.android.synthetic.main.activity_employee_list.toolbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class EmployeeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeListBinding

    private val viewModel: EmployeeListViewModel by viewModel()

    private val navigation: Navigation by inject()

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
            employeeListController.observe(this@EmployeeListActivity, Observer {
                recyclerView.setControllerAndBuildModels(it)
            })

            actions.observe(this@EmployeeListActivity, Observer {
                when(it) {
                    is EmployeeListViewModel.Actions.ShowEmployee -> {
                        val intent = navigation.intentTo(Navigation.Destination.Employee)
                        intent.putExtra(Navigation.Destination.Employee.EXTRA_EMPLOYEE_ID, it.employee.id)
                        startActivity(intent)
                    }
                    is EmployeeListViewModel.Actions.ShowError -> {
                        Toast.makeText(this@EmployeeListActivity, it.exception.message, Toast.LENGTH_LONG).show()
                    }
                }
            })

            intent.getStringExtra(Navigation.Destination.EmployeeList.EXTRA_COMPANY_ID)?.let {
                fetchEmployees(it)
            }
        }
    }
}
