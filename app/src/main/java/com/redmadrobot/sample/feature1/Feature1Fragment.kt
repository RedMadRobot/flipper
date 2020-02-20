package com.redmadrobot.sample.feature1


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.redmadrobot.flipper.ext.flipperPoint
import com.redmadrobot.sample.Features
import com.redmadrobot.sample.R
import kotlinx.android.synthetic.main.fragment_feature1.*


class Feature1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feature1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flipperPoint(Features.Feature4) {
            Log.e("Feature4", "Hello from feature 4")
        }

        with(feature4_button) {
            setOnClickListener { findNavController().navigate(Feature1FragmentDirections.toFeature4()) }
            flipperPoint(Features.Feature4)
        }
    }
}
