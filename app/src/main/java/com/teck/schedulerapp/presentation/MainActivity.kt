package com.teck.schedulerapp.presentation

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.teck.schedulerapp.R
import com.teck.schedulerapp.databinding.ActivityMainBinding
import com.teck.schedulerapp.presentation.classes.ClassesFragment
import com.teck.schedulerapp.presentation.main.MainFragment
import com.teck.schedulerapp.presentation.utils.navigation.BottomChipsNavigation
import com.teck.schedulerapp.presentation.utils.navigation.BottomNavigation

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by viewBinding()
    private var title: String? = "Hi, Mike!"
    private val mapNavigation: Map<Chip, Fragment> by lazy {
        mapOf(
            Pair(viewBinding.appBarMain.home, MainFragment.newInstance()),
            Pair(viewBinding.appBarMain.lessons, ClassesFragment.newInstance()),
            Pair(viewBinding.appBarMain.listTasks, ClassesFragment.newInstance()),
            Pair(viewBinding.appBarMain.star, ClassesFragment.newInstance())
        )
    }
    private val navigation: BottomNavigation by lazy {
        BottomChipsNavigation(
            navigation = viewBinding.appBarMain.navigationChipGroup,
            context = this,
            mapNavigation = mapNavigation,
            container = R.id.content_main_frame,
            titlesOfFragment = mapOf<Chip, String>(
                Pair(viewBinding.appBarMain.home, getString(R.string.chip_item_home)),
                Pair(viewBinding.appBarMain.lessons, getString(R.string.chip_item_classes)),
                Pair(viewBinding.appBarMain.listTasks,  getString(R.string.chip_item_tasks)),
                Pair(viewBinding.appBarMain.star, getString(R.string.chip_item_favorite))
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        navigationFragment(MainFragment.newInstance())
        initNavigation()
    }

    private fun initNavigation() {
        navigation.listener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initView() {
        val toolbar: MaterialToolbar = initToolbar()
        initDrawer(toolbar)
        toolbar.navigationIcon = null
        val span = SpannableString(title).apply {
            title?.length?.let {
                setSpan(
                    StyleSpan(Typeface.BOLD), 3,
                    it, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        toolbar.title = span
    }

    private fun initToolbar(): MaterialToolbar {
        val toolbar = viewBinding.appBarMain.toolbar
        setSupportActionBar(toolbar)
        return toolbar
    }


    private fun initDrawer(toolbar: MaterialToolbar) {
        val drawerLayout: DrawerLayout = viewBinding.drawerLayout
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawer_title,
            R.string.content_text_drawer_layout
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = viewBinding.navView
    }

    private fun navigationFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_main_frame, fragment)
            .commit()
    }
}