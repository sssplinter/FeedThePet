package com.kristina.feedthebeast.ui.start

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.kristina.feedthebeast.BUNDLE_KEY
import com.kristina.feedthebeast.R

class UserClickListener constructor(var userName: String) : View.OnClickListener {

    override fun onClick(v: View?) {
        v?.let { it1 ->
            Navigation.findNavController(it1).navigate(
                R.id.action_startFragment_to_beastFragment, bundleOf(BUNDLE_KEY to userName)
            )
        }
    }
}