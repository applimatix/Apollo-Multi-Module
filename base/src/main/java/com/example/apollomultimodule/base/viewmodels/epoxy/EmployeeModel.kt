package com.example.apollomultimodule.base.viewmodels.epoxy

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.example.apollomultimodule.base.BR
import com.example.apollomultimodule.base.R
import com.example.apollomultimodule.base.data.models.Employee

@EpoxyModelClass
abstract class EmployeeModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    lateinit var employee: Employee

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null


    // Module workarounds
    override fun getDefaultLayout(): Int {
        return R.layout.item_employee
    }

    override fun setDataBindingVariables(binding: ViewDataBinding) {
        check(
            binding.setVariable(
                BR.employee,
                employee
            )
        ) { "The attribute employee was defined in your data binding model (com.example.apollomultimodule.base.viewmodels.epoxy.EmployeeModel) but a data variable of that name was not found in the layout." }
        check(
            binding.setVariable(
                BR.clickListener,
                clickListener
            )
        ) { "The attribute clickListener was defined in your data binding model (com.example.apollomultimodule.base.viewmodels.epoxy.EmployeeModel) but a data variable of that name was not found in the layout." }
    }

    override fun setDataBindingVariables(
        binding: ViewDataBinding,
        previousModel: EpoxyModel<*>?
    ) {
        if (previousModel !is EmployeeModel_) {
            setDataBindingVariables(binding)
            return
        }
        val that = previousModel
        if (if (employee != null) employee != that.employee else that.employee != null) {
            binding.setVariable(BR.employee, employee)
        }
        if (clickListener == null != (that.clickListener == null)) {
            binding.setVariable(BR.clickListener, clickListener)
        }
    }
}