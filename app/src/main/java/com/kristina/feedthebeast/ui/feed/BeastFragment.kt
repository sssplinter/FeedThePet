package com.kristina.feedthebeast

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kristina.feedthebeast.database.FeedTheBeastDatabase
import com.kristina.feedthebeast.databinding.FragmentBeastBinding
import com.kristina.feedthebeast.ui.feed.viewModel.BeastViewModel
import com.kristina.feedthebeast.ui.feed.viewModel.FeedTheBeastViewModelFactory

private const val FILE_KEY = "PREF_FILE_KEY"
private const val SAVED_KEY = "SAVED_KEY"

class BeastFragment : Fragment() {

    private lateinit var beastViewModel: BeastViewModel
    private lateinit var binding: FragmentBeastBinding

    private lateinit var userName: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        binding = FragmentBeastBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = FeedTheBeastDatabase.getInstance(application).feedTheBeastDatabaseDao

        val viewModelFactory = FeedTheBeastViewModelFactory(dataSource, application)

        beastViewModel = ViewModelProvider(this, viewModelFactory).get(BeastViewModel::class.java)

        userName = arguments?.getString(BUNDLE_KEY).toString()

        binding.userName.text = userName

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.score.text = beastViewModel.score.toString()

        beastViewModel.score.observe(viewLifecycleOwner) { score ->
            binding.score.text = score.toString()
        }



        binding.feedButton.setOnClickListener {
            beastViewModel.feed()
        }

        beastViewModel.achieveName.observe(viewLifecycleOwner) { achieveName ->
            if (achieveName.isNotEmpty()) {
                Toast.makeText(context, "New achievement: $achieveName", Toast.LENGTH_LONG).show()
                beastViewModel.addAchievementToDatabase(userName)
            }
        }

        beastViewModel.animate.observe(viewLifecycleOwner) { animate ->
            if (animate) {
                beastViewModel.doneAnimation()
                val animationDuration = 500L

                val appearAnim0 =
                    ObjectAnimator.ofFloat(binding.heart0, "alpha", 0f, 1f).apply {
                        duration = animationDuration
                    }
                val appearAnim1 =
                    ObjectAnimator.ofFloat(binding.heart1, "alpha", 0f, 1f).apply {
                        duration = animationDuration
                        startDelay = animationDuration
                    }
                val appearAnim2 =
                    ObjectAnimator.ofFloat(binding.heart2, "alpha", 0f, 1f).apply {
                        duration = animationDuration
                        startDelay = animationDuration * 2
                    }
                val fadeAnim0 = ObjectAnimator.ofFloat(binding.heart0, "alpha", 1f, 0f).apply {
                    duration = animationDuration
                    startDelay = animationDuration * 3
                }
                val fadeAnim1 = ObjectAnimator.ofFloat(binding.heart1, "alpha", 1f, 0f).apply {
                    duration = animationDuration
                    startDelay = animationDuration * 4
                }
                val fadeAnim2 = ObjectAnimator.ofFloat(binding.heart2, "alpha", 1f, 0f).apply {
                    duration = animationDuration
                    startDelay = animationDuration * 5
                }

                AnimatorSet().apply {
                    play(appearAnim0).with(appearAnim1).with(appearAnim2)
                        .with(fadeAnim1).with(fadeAnim0).with(fadeAnim2)

                    start()
                }
            }
        }

        // animation
        val topAnimation = AnimationUtils.loadAnimation(context, R.anim.top_animation)
        val cat = view.findViewById<View>(R.id.cat)
        cat.startAnimation(topAnimation)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.beast_menu, menu)

        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            // hide the menu item if it doesn't resolve
            menu?.findItem(R.id.share)?.isVisible = false
        }
    }

    private fun getShareIntent(): Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plane"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.share_success_text, beastViewModel.score.value)
        )
        return shareIntent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    // for items we selected from menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        beastViewModel.addFeedingToDatabase(userName)

        val sharedPref = activity?.getSharedPreferences(
            FILE_KEY, Context.MODE_PRIVATE
        )

        if (sharedPref != null) {
            with(sharedPref.edit()) {
                putInt(SAVED_KEY, beastViewModel.score.value!!)
                apply()
            }
            beastViewModel.updateWorkoutsWidget(requireContext())
        }
    }
}