package com.kristina.feedthebeast

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

import com.google.android.gms.common.AccountPicker

import android.content.Intent
import androidx.core.os.bundleOf
import com.kristina.feedthebeast.R

const val BUNDLE_KEY = "USER_NAME"

class StartFragment : Fragment() {
//
//    private val G_PLUS_SCOPE = "oauth2:https://www.googleapis.com/auth/plus.me"
//    private val USERINFO_SCOPE = "https://www.googleapis.com/auth/userinfo.profile"
//    private val EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email"
//    private val SCOPES = "$G_PLUS_SCOPE $USERINFO_SCOPE $EMAIL_SCOPE"

    private lateinit var userName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        //todo
        userName = "kristina"
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.start_feeding).setOnClickListener {
            Navigation.findNavController(view).navigate(
                R.id.action_startFragment_to_beastFragment, bundleOf(BUNDLE_KEY to userName)
            )
        }

        val btn = view.findViewById(R.id.sign_in_button) as View
        btn.setOnClickListener {
            val intent = AccountPicker.newChooseAccountIntent(
                null, null, arrayOf("com.google"),
                false, null, null, null, null
            )
            startActivityForResult(intent, 123)
        }
    }

}