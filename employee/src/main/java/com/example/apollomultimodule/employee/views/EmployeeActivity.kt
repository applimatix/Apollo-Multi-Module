package com.example.apollomultimodule.employee.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.apollomultimodule.base.navigation.Navigation
import com.example.apollomultimodule.employee.databinding.ActivityEmployeeBinding
import com.example.apollomultimodule.employee.viewmodels.EmployeeViewModel
import kotlinx.android.synthetic.main.activity_employee.*
import org.koin.android.viewmodel.ext.android.viewModel

class EmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeBinding

    private val viewModel: EmployeeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this@EmployeeActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        with(viewModel) {
            employeeListController.observe(this@EmployeeActivity, Observer {
                recyclerView.setControllerAndBuildModels(it)
            })

            actions.observe(this@EmployeeActivity, Observer {
                when (it) {
                    is EmployeeViewModel.Actions.ShowError -> {
                        Toast.makeText(
                            this@EmployeeActivity,
                            it.exception.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })

            intent.getStringExtra(Navigation.Destination.Employee.EXTRA_EMPLOYEE_ID)?.let {
                fetchEmployee(it)
            }
        }
    }
}
