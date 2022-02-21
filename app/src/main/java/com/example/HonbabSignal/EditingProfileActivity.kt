package com.example.HonbabSignal

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityEditingProfileBinding

class EditingProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditingProfileBinding
    lateinit var foodPerferenceSpn: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditingProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        setupSpinnerHandler()


        //뒤로 가기 버튼
        binding.toolbarBackIv.setOnClickListener {
            finish()
            overridePendingTransition(0,0)
        }


        //실시간 글자 수 변경
        binding.editingProfilePrEt.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("editingProfile","beforeTextChanged")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("editingProfile","onTextChanged")

            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("editingProfile","afterTextChanged")
                val input = binding.editingProfilePrEt.text.toString().length
                binding.editingProfilePrCntTv.text = "$input/100 글자"
                Log.d("editingProfile",input.toString())
                Log.d("editingProfile",input.toString())
            }

        })
    }






    //spinner
    private fun setupSpinner() {
        val foodPreferenceArray = resources.getStringArray(R.array.foodPreference)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, foodPreferenceArray)
        binding.editingProfileFoodPreferenceSpn.adapter = adapter
    }


    private fun setupSpinnerHandler() {
        binding.editingProfileFoodPreferenceSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}

