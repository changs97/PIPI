package com.pipix.pipi.src.discover

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseActivity
import com.pipix.pipi.databinding.ActivityDiscoverBinding

class DiscoverActiviy : BaseActivity<ActivityDiscoverBinding>(ActivityDiscoverBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment2 = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController2 = navHostFragment2.navController
        val bottomNavigationView = binding.bottomNav


        NavigationUI.setupWithNavController(binding.bottomNav, navController2)
        navController2.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.profileFragment) {
                bottomNavigationView.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }

        NavigationUI.setupWithNavController(binding.bottomNav, navController2)
    }
}