package com.redmadrobot.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.redmadrobot.flipper.flipperPoint
import com.redmadrobot.flipper.flipperPointSelect
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onBackPressed() {
        if (currentNavController?.value?.popBackStack() != true) {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()

        with(bottom_nav.menu) {
            findItem(R.id.feature1).flipperPoint(Features.Feature1)
            findItem(R.id.feature2).flipperPoint(Features.Feature2)
            findItem(R.id.feature3).flipperPoint(Features.Feature3)
        }
    }

    private fun setupBottomNavigationBar() {

        val navGraphIds = listOf(R.navigation.feature1, R.navigation.feature2, R.navigation.feature3)

        val controller = bottom_nav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })

        currentNavController = controller
    }
}
