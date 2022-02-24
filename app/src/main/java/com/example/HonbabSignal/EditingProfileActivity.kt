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
    lateinit var userIntroduce: String
    lateinit var hateFood: String
    lateinit var taste: String
    lateinit var avgSpeed : String
    lateinit var interest : String
    lateinit var preferArea : String
    lateinit var mbti : String


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
        var userIdx: Int = 11
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
                            nickName = respIdx.result.nickName
                            userIntroduce = respIdx.result.userIntroduce
                            hateFood = respIdx.result.hateFood
                            taste = respIdx.result.taste
                            avgSpeed = respIdx.result.avgSpeed
                            interest = respIdx.result.interest
                            preferArea = respIdx.result.preferArea
                            mbti = respIdx.result.mbti

                            binding.editingProfileNicknameEt.setText(nickName)
                            binding.editingProfilePrEt.setText(userIntroduce)
//                            binding.editingProfileFoodHateSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//                                override fun onItemSelected(
//                                    p0: AdapterView<*>?,
//                                    p1: View?,
//                                    p2: Int,
//                                    p3: Long
//                                ) {
//                                }
//
//                                override fun onNothingSelected(p0: AdapterView<*>?) {
//                                }
//
//                            }
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

        val habitArray = resources.getStringArray(R.array.habit)
        val habitAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, habitArray)
        binding.editingProfileHabitSpn.adapter = habitAdapter

        val eatingTimeArray = resources.getStringArray(R.array.eatingTime)
        val eatingTimeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, eatingTimeArray)
        binding.editingEatingTimeSpn.adapter = eatingTimeAdapter

        val locationArray = resources.getStringArray(R.array.location)
        val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationArray)
        binding.editingLocationSpn.adapter = locationAdapter

        val mbtiArray = resources.getStringArray(R.array.mbti)
        val mbtiAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mbtiArray)
        binding.editingMbtiSpn.adapter = mbtiAdapter


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

        binding.editingProfileHabitSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.editingEatingTimeSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.editingLocationSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.editingMbtiSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }
}

