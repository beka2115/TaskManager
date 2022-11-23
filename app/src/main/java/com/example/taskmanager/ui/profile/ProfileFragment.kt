package com.example.taskmanager.ui.profile

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding
import java.text.NumberFormat
import java.text.ParsePosition


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var launcher: ActivityResultLauncher<String>
    private lateinit var pref: Pref

    companion object {
        const val CODE_FOR_INTENT = "image/*"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.textMaterialName.setText(pref.takeEditText())
        //binding.editText.setText(pref.takeEditText())
        binding.textMaterialName.addTextChangedListener {
            pref.saveEditText(binding.textMaterialName.text.toString())
        }
        binding.textMaterialAge.setText(pref.takeNumber())
        binding.textMaterialAge.addTextChangedListener {
            doSave()
        }
        saveAndShowPicture()
    }

    private fun saveAndShowPicture() {
        launcher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
                if (imageUri != null) {
                    pref.savePicture(imageUri.toString())
                    binding.imgProfile.setImageURI(imageUri)
                }
            }

        binding.imgProfile.setOnClickListener {
            launcher.launch(CODE_FOR_INTENT)
        }
        Glide.with(binding.imgProfile).load(pref.takePicture()).into(binding.imgProfile)

    }

    private fun doSave() {
        if (isNumeric(binding.textMaterialAge.text.toString())) {
            pref.saveEditNum(binding.textMaterialAge.text.toString())
            binding.editAge.error = null
        } else {
            binding.editAge.error = getString(R.string.save_num_error)
        }
    }

    private fun isNumeric(s: String): Boolean {
        val pos = ParsePosition(0)
        NumberFormat.getInstance().parse(s, pos)
        return s.length == pos.index
    }
}




