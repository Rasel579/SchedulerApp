package com.teck.schedulerapp.presentation.utils.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class BottomChipsNavigation(
    private val navigation: ChipGroup,
    private val context: FragmentActivity,
    private val mapNavigation: Map<Chip, Fragment>,
    private val container: Int,
    private val titlesOfFragment: Map<Chip, String>
) : BottomNavigation{
    override fun listener() = navigation.setOnCheckedChangeListener { group, _ ->
        when (group.checkedChipId) {
            mapNavigation.keys.find { it.id == group.checkedChipId }?.id -> {
                val keyChip = mapNavigation.keys.find { it.id == group.checkedChipId }
                mapNavigation[keyChip]?.let { navigate(it, container) }
                keyChip?.text = keyChip?.let { titlesOfFragment[keyChip] }
                mapNavigation.keys.map {
                    if (it.id != group.checkedChipId) {
                        it.text = null
                    }
                }
            }
        }
    }

    private fun navigate(fragment: Fragment, @IdRes container: Int) {
        context.supportFragmentManager
            .beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
    }
}