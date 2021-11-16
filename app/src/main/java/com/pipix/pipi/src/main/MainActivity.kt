package com.pipix.pipi.src.main

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseActivity
import com.pipix.pipi.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottomNavigationView = binding.bottomNav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottomNavigationView, navController)



        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.homeFragment || destination.id == R.id.searchFragment || destination.id == R.id.insertFragment) {
                bottomNavigationView.visibility = View.VISIBLE

            } else {
                bottomNavigationView.visibility = View.GONE
            }
        }
    }
}