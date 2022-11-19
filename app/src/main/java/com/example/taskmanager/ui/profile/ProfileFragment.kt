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
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding


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
        binding.editText.setText(pref.takeEdittext())
        binding.editText.addTextChangedListener {
            pref.saveEditText(binding.editText.text.toString())
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
}




