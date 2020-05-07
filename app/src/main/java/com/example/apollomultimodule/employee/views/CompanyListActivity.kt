package com.example.apollomultimodule.employee.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.apollomultimodule.databinding.ActivityCompanyListBinding
import com.example.apollomultimodule.viewmodels.CompanyViewModel
import kotlinx.android.synthetic.main.activity_company_list.*

class CompanyListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyListBinding

    private val viewModel: CompanyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCompanyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(toolbar)

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this@CompanyListActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        with(viewModel) {
            companyListController.observe(this@CompanyListActivity, Observer {
                recyclerView.setControllerAndBuildModels(it)
            })

            actions.observe(this@CompanyListActivity, Observer {
                when(it) {
                    is CompanyViewModel.Actions.ShowCompanyEmployees -> {
                        val intent = Intent(this@CompanyListActivity, EmployeeListActivity::class.java)
                        intent.putExtra(EmployeeListActivity.EXTRA_COMPANY_ID, it.company.id)
                        startActivity(intent)
                    }
                    is CompanyViewModel.Actions.ShowError -> {
                        Toast.makeText(this@CompanyListActivity, it.exception.message, Toast.LENGTH_LONG).show()
                    }
                }
            })

            swipeRefresh.setOnRefreshListener {
                fetchCompanies()
                swipeRefresh.isRefreshing = false
            }
        }

    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchCompanies()
    }
}
