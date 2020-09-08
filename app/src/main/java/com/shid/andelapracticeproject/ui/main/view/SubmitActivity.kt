package com.shid.andelapracticeproject.ui.main.view

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.shid.andelapracticeproject.R
import com.shid.andelapracticeproject.data.api.ApiHelper
import com.shid.andelapracticeproject.data.api.RetrofitBuilder
import com.shid.andelapracticeproject.ui.base.SubmitViewModelFactory
import com.shid.andelapracticeproject.ui.main.viewmodel.SubmitViewModel
import com.shid.andelapracticeproject.utils.Status
import kotlinx.android.synthetic.main.activity_submit.*


class SubmitActivity : AppCompatActivity() {

    private lateinit var viewModel: SubmitViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        setupViewModel()
        clickListeners()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            SubmitViewModelFactory(ApiHelper(RetrofitBuilder.apiServicePost))
        ).get(SubmitViewModel::class.java)
    }

    private fun clickListeners() {
        btn_submit.setOnClickListener {
            confirmationDialog()
        }

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun confirmationDialog() {
        MaterialDialog(this).show {
            title(text = getString(R.string.title_dialog))
            message(text = getString(R.string.dialog_confirm))
            positiveButton(text = getString(R.string.yes)) { dialog ->
                dialog.cancel()
                setupObservers()
            }
            negativeButton(text = getString(R.string.no)) { dialog ->
                dialog.cancel()

            }
        }
    }

    private fun setupObservers() {
        val first: String = first_name.text.toString()
        val mail: String = email.text.toString()
        val nom: String = last_name.text.toString()
        val lien: String = link.text.toString()
        viewModel.sendInformation(mail, first, nom, lien).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        successDialog()
                    }
                    Status.ERROR -> {
                        failureDialog()
                    }
                    Status.LOADING -> {

                    }
                }
            }
        })

    }

    private fun successDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_success)
        dialog.setTitle("File Submitted!")
        dialog.show()
    }

    private fun failureDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_failure)
        dialog.setTitle("File not Submitted!")
        dialog.show()
    }
}