package com.example.HonbabSignal

import android.app.AlertDialog
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditingProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditingProfileBinding
    lateinit var foodPerferenceSpn: Spinner
    lateinit var nickName: String

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


        var retrofit = getRetorfit()

        var EditingProfileService = retrofit.create(EditingProfileService::class.java)
        var userIdx: Int = 1
        Log.d("editingProfile","retrofit")
        EditingProfileService.getUserIdx(userIdx)
            .enqueue(object: Callback<ProfileAuthResponse>{
                override fun onResponse(
                    call: Call<ProfileAuthResponse>,
                    response: Response<ProfileAuthResponse>
                ) {
                    var respIdx = response.body()!!
                    Log.d("editingProfile",respIdx.code.toString())
                    when (respIdx.code) {
                        1000 -> {
                            Log.d("editingProfile","success")
                            nickName = respIdx.result[0].nickName
                            binding.editingProfileNicknameTv.text = nickName
                            binding.editingProfileNicknameEt.setText(nickName)
                        }
                    }
                }

                override fun onFailure(call: Call<ProfileAuthResponse>, t: Throwable) {
                    var dialog = AlertDialog.Builder(this@EditingProfileActivity)
                    dialog.setTitle("bad")
                }
            })



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
            }
        })

    }

    //spinner
    private fun setupSpinner() {
        val foodPreferenceArray = resources.getStringArray(R.array.foodPreference)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, foodPreferenceArray)
        binding.editingProfileFoodPreferenceSpn.adapter = adapter

        val hateFoodArray = resources.getStringArray(R.array.hateFood)
        val hateFoodAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hateFoodArray)
        binding.editingProfileFoodHateSpn.adapter = hateFoodAdapter


    }

    private fun setupSpinnerHandler() {
        binding.editingProfileFoodPreferenceSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.editingProfileFoodHateSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }


    }
}

