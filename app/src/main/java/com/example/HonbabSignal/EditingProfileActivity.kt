package com.example.HonbabSignal

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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

    lateinit var foodPreferenceArray : Array<String>
    lateinit var hateFoodArray : Array<String>
    lateinit var habitArray : Array<String>
    lateinit var eatingTimeArray : Array<String>
    lateinit var locationArray : Array<String>
    lateinit var mbtiArray : Array<String>

    //for img upload
    private lateinit var activityResult: ActivityResultLauncher<Intent>
    private lateinit var coverImgUrl: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditingProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        setupSpinnerHandler()

        val spf_jwt = this.getSharedPreferences("jwt", Context.MODE_PRIVATE)
        val jwt: String = spf_jwt?.getString("jwt","").toString()
        Log.d("jwt",jwt)


        //img upload
        activityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                coverImgUrl = it.data!!.data!!
                binding.editingProfileProfileImgIv.setImageURI(coverImgUrl)
            }
        }

        binding.editingProfileOpenGalleryBtn.setOnClickListener {
            val galleryIntent = Intent()
            galleryIntent.action = Intent.ACTION_GET_CONTENT
            galleryIntent.type = "image/"
            activityResult.launch(galleryIntent)
        }

        //뒤로 가기 버튼
        binding.toolbarBackIv.setOnClickListener {
            finish()
            overridePendingTransition(0,0)
        }


        var retrofit = getRetorfit()

        var EditingProfileService = retrofit.create(EditingProfileService::class.java)

        Log.d("editingProfile","retrofit")
        EditingProfileService.getUserIdx(jwt)
            .enqueue(object: Callback<ProfileAuthResponse>{
                override fun onResponse(
                    call: Call<ProfileAuthResponse>,
                    response: Response<ProfileAuthResponse>
                ) {
                    var respIdx = response.body()!!
                    Log.d("editingProfile_code", respIdx.code.toString())
                    when (respIdx.code) {
                        1000 -> {
                            Log.d("editingProfile","success")
                            Log.d("editingProfile", respIdx.result.toString())

                            nickName = respIdx.result.nickName
                            userIntroduce = respIdx.result.userIntroduce
                            hateFood = respIdx.result.hateFood
                            taste = respIdx.result.taste
                            avgSpeed = respIdx.result.avgSpeed
                            interest = respIdx.result.interest
                            preferArea = respIdx.result.preferArea
                            mbti = respIdx.result.mbti

                            binding.editingProfileNicknameEt.setText(nickName)
                            //img x
                            binding.editingProfilePrEt.setText(userIntroduce)
                            binding.editingProfileFoodPreferenceSpn.setSelection(foodPreferenceArray.indexOf(taste))
                            binding.editingProfileFoodHateSpn.setSelection(hateFoodArray.indexOf(hateFood))
                            binding.editingEatingTimeSpn.setSelection(eatingTimeArray.indexOf(avgSpeed))
                            binding.editingProfileHabitSpn.setSelection(habitArray.indexOf(interest))
                            binding.editingLocationSpn.setSelection(locationArray.indexOf(preferArea))
                            binding.editingMbtiSpn.setSelection(mbtiArray.indexOf(mbti))
                        }
                    }
                }

                override fun onFailure(call: Call<ProfileAuthResponse>, t: Throwable) {
                    var dialog = AlertDialog.Builder(this@EditingProfileActivity)
                    dialog.setTitle("bad")
                }
            })

        var profileImg: String = "img"

        binding.editingSaveBtn.setOnClickListener {

            Log.d("editingProfile", "save btn click")
            EditingProfileService.patchProfile( jwt, profileImg, taste, hateFood, interest, avgSpeed, preferArea, mbti, userIntroduce )
                .enqueue(object: Callback<ProfilePatchResponse>{
                override fun onResponse(
                    call: Call<ProfilePatchResponse>,
                    response: Response<ProfilePatchResponse>
                ) {
                    var respUserPatch = response.body()!!
                    Log.d("editingProfile", "saving"+respUserPatch.code.toString())
                    when(respUserPatch.code){
                        1000->{
                            Toast.makeText(this@EditingProfileActivity, "프로필 수정 완료",Toast.LENGTH_SHORT).show()

                        }
                        else->{
                            Toast.makeText(this@EditingProfileActivity, "실패",Toast.LENGTH_SHORT).show()

                        }
                    }
                }

                override fun onFailure(call: Call<ProfilePatchResponse>, t: Throwable) {
                    Log.d("DEBUGInSignUp", t.message.toString())
                    var dialog = AlertDialog.Builder(this@EditingProfileActivity)

                    dialog.setTitle("실패!")
                    dialog.setMessage("통신에 실패했습니다!")
                    dialog.show()
                }

            })




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
                val input = binding.editingProfilePrEt.text.toString()
                binding.editingProfilePrCntTv.text = "$input/100 글자"
                Log.d("editingProfile",input.toString())
            }
        })

    }

    //spinner
    private fun setupSpinner() {
        foodPreferenceArray = resources.getStringArray(R.array.foodPreference)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, foodPreferenceArray)
        binding.editingProfileFoodPreferenceSpn.adapter = adapter

        hateFoodArray = resources.getStringArray(R.array.hateFood)
        val hateFoodAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hateFoodArray)
        binding.editingProfileFoodHateSpn.adapter = hateFoodAdapter

        habitArray = resources.getStringArray(R.array.habit)
        val habitAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, habitArray)
        binding.editingProfileHabitSpn.adapter = habitAdapter

        eatingTimeArray = resources.getStringArray(R.array.eatingTime)
        val eatingTimeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, eatingTimeArray)
        binding.editingEatingTimeSpn.adapter = eatingTimeAdapter

        locationArray = resources.getStringArray(R.array.location)
        val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationArray)
        binding.editingLocationSpn.adapter = locationAdapter

        mbtiArray = resources.getStringArray(R.array.mbti)
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

//    // upload the image to firebase
//    private fun uploadImg(uri: Uri) {
//        val mFileReference: StorageReference = mStorageReference.child(USER_ID).child("${System.currentTimeMillis()}.${getFileExtension(uri)}")
//
//        mFileReference.putFile(uri).addOnSuccessListener {
//
//            mFileReference.downloadUrl.addOnSuccessListener {
//                coverImgUrl = it
//                mShopsReference.child(USER_ID).child("coverImg").setValue(coverImgUrl.toString())
//                Toast.makeText(this, "업로드 성공", Toast.LENGTH_SHORT).show()
//                binding.shopkeeperRegisterCoverImgIv.setImageURI(uri)
//            }
//        }.addOnFailureListener {
//            Toast.makeText(this, "업로드 실패", Toast.LENGTH_SHORT).show()
//        }
//    }
}

