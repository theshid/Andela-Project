package com.shid.andelapracticeproject.ui.main.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.shid.andelapracticeproject.R
import com.shid.andelapracticeproject.data.api.ApiHelper
import com.shid.andelapracticeproject.data.api.ApiService
import com.shid.andelapracticeproject.data.api.RetrofitBuilder
import com.shid.andelapracticeproject.utils.Status
import kotlinx.android.synthetic.main.activity_submit.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SubmitActivity : AppCompatActivity() {


    private lateinit var apiHelper: ApiHelper
    var call: Call<ResponseBody>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)


        clickListeners()
    }

    private fun postData(){
        val first: String = first_name.text.toString()
        val mail: String = email.text.toString()
        val nom: String = last_name.text.toString()
        val lien: String = link.text.toString()
        apiHelper = ApiHelper(RetrofitBuilder.apiServicePost)
        call = apiHelper.sendInformation(mail, first, nom, lien)
        call!!.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                
                    successDialog()

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
               failureDialog()
                Log.d("Submit","failed +onFailure")
            }

        })
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
                postData()
            }
            negativeButton(text = getString(R.string.no)) { dialog ->
                dialog.cancel()

            }
        }
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