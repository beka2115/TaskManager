package com.example.taskmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.databinding.FragmentAuthBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private var auth = Firebase.auth
    lateinit var storedVerificationId: String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGetOtp.setOnClickListener {
            if (binding.editPhone.text.isNotEmpty()) {
                sendVerificationCode(binding.editPhone.text.toString())

            } else {
                Toast.makeText(context, "Введите ваш номер", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnSingUp.setOnClickListener {
            if (binding.edOtp.text.isNotEmpty()) {
                veryfycationCode(binding.edOtp.text.toString())
            } else {
                Toast.makeText(context, "Введиет код", Toast.LENGTH_LONG).show()
            }
        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(context, "Auth failed- $e", Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                storedVerificationId = verificationId
                resendToken = token
                binding.phoneLayout.visibility = View.GONE
                binding.otpLayout.visibility = View.VISIBLE

            }
        }
    }

    private fun sendVerificationCode(phoneNo: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNo)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun veryfycationCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
        singUp(credential)
    }

    private fun singUp(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    Toast.makeText(context, "SingUp successfully", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.navigation_home)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(context, "Code entered is incorrect", Toast.LENGTH_LONG)
                            .show()
                        binding.edOtp.setText("")
                    }
                }
            }
    }

}