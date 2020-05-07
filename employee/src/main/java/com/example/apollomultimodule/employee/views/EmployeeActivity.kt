package com.example.apollomultimodule.employee.views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.apollomultimodule.employee.databinding.ActivityEmployeeBinding
import com.example.apollomultimodule.viewmodels.EmployeeViewModel
import kotlinx.android.synthetic.main.activity_employee.*

class EmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeBinding

    private val viewModel: EmployeeViewModel by viewModels()

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

            intent.getStringExtra(EXTRA_EMPLOYEE_ID)?.let {
                fetchEmployee(it)
            }
        }
    }

    companion object {
        const val EXTRA_EMPLOYEE_ID = "extra_employee_id"
    }
}
