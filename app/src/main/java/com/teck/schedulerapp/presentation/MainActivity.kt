package com.teck.schedulerapp.presentation

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.appbar.MaterialToolbar
import com.teck.schedulerapp.R
import com.teck.schedulerapp.databinding.ActivityMainBinding
import com.teck.schedulerapp.presentation.main.MainFragment
import java.time.format.TextStyle

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by viewBinding()
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        navigationFragment(MainFragment.newInstance())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initView() {
            val toolbar: MaterialToolbar = initToolbar()
            initDrawer(toolbar)
            toolbar.navigationIcon = null
            title = "Hi, Mike!"
            val span = SpannableString(title).apply{
                title?.length?.let {
                    setSpan(StyleSpan(Typeface.BOLD), 3,
                        it, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
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