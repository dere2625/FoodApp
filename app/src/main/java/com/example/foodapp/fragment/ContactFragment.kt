package com.example.foodapp.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.R

class ContactFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_contact, container, false)

        val btnPhoneCall = rootView.findViewById<View>(R.id.btnPhoneCall)
        val btnSendEmail = rootView.findViewById<View>(R.id.btnSendEmail)

        btnPhoneCall.setOnClickListener {
            initiatePhoneCall()
        }

        btnSendEmail.setOnClickListener {
            initiateEmail()
        }

        return rootView
    }

    private fun initiatePhoneCall() {
        val phoneNumber = "+1 642 819 2222" // Not my real phone number
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }

    private fun initiateEmail() {
        val email = "dereje@gmail.com" // not a real email address
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$email")
        startActivity(intent)
    }

}