package com.kristina.feedthebeast.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kristina.feedthebeast.R

class FeedFragment : Fragment() {

    private lateinit var beastViewModel: BeastViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        return inflater.inflate(R.layout.fragment_feed, container, false).apply {
            findViewById<View>(R.id.feed_button)?.setOnClickListener {
                beastViewModel.feed()
            }
        }
    }

//    override fun onStart() {
//        super.onStart()
//
//        beastViewModel = BeastViewModel().apply {
//            score.observe(viewLifecycleOwner) { score ->
//                view?.findViewById<TextView>(R.id.score)?.apply {
//                    text = score.toString()
//                }
//            }
//
//            animate.observe(viewLifecycleOwner) { animate ->
//                if (animate) {
//                    beastViewModel.doneAnimation()
//                    val root = view
//                    if (root != null) {
//                        val animationDuration = 500L
//                        val heart0 = root.findViewById<View>(R.id.heart_0)
//                        val heart1 = root.findViewById<View>(R.id.heart_1)
//                        val heart2 = root.findViewById<View>(R.id.heart_2)
//
//                        val appearAnim0 = ObjectAnimator.ofFloat(heart0, "alpha", 0f, 1f).apply {
//                            duration = animationDuration
//                        }
//                        val appearAnim1 = ObjectAnimator.ofFloat(heart1, "alpha", 0f, 1f).apply {
//                            duration = animationDuration
//                            startDelay = animationDuration
//                        }
//                        val appearAnim2 = ObjectAnimator.ofFloat(heart2, "alpha", 0f, 1f).apply {
//                            duration = animationDuration
//                            startDelay = animationDuration * 2
//                        }
//                        val fadeAnim0 = ObjectAnimator.ofFloat(heart0, "alpha", 1f, 0f).apply {
//                            duration = animationDuration
//                            startDelay = animationDuration * 3
//                        }
//                        val fadeAnim1 = ObjectAnimator.ofFloat(heart1, "alpha", 1f, 0f).apply {
//                            duration = animationDuration
//                            startDelay = animationDuration * 4
//                        }
//                        val fadeAnim2 = ObjectAnimator.ofFloat(heart2, "alpha", 1f, 0f).apply {
//                            duration = animationDuration
//                            startDelay = animationDuration * 5
//                        }
//
//
//                        AnimatorSet().apply {
//                            play(appearAnim0).with(appearAnim1).with(appearAnim2)
//                                .with(fadeAnim1).with(fadeAnim0).with(fadeAnim2)
//
//                            start()
//                        }
//                    }
//
//                }
//            }
//        }
//    }
}