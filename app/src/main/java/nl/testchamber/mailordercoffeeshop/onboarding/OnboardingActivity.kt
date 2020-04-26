package nl.testchamber.mailordercoffeeshop.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_onboarding.*
import nl.testchamber.mailordercoffeeshop.R
import nl.testchamber.mailordercoffeeshop.SharedPreferencesUtil

class OnboardingActivity : AppCompatActivity() {

    private var currentPage = 0
    private val fragments = ArrayList<androidx.fragment.app.Fragment>()
    private lateinit var onboardingSlides: List<OnboardingSlide>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        initOnboardingSlidesList()

        view_pager.adapter = populateOnboardingAdapter()
        circle_page_indicator.setViewPager(view_pager)

        setViewPagerBehaviour()
        setNavigationButtonsBehaviour()


    }

    private fun initOnboardingSlidesList() {
        onboardingSlides = listOf(
                OnboardingSlide(getString(R.string.onboarding_create_custom_order), R.drawable.onboarding_slide_custom_order),
                OnboardingSlide(getString(R.string.onboarding_browse_menu), R.drawable.onboarding_slide_recyclerview),
                OnboardingSlide(getString(R.string.onboarding_order_by_mail), R.drawable.onboarding_slide_orderoverview))
    }

    private fun setNavigationButtonsBehaviour() {
        go_on_button.setOnClickListener {
            if (currentPage == fragments.size - 1) {
                closeOnboarding()
            } else {
                view_pager.setCurrentItem(currentPage + 1, true)
            }
        }

        close_button.setOnClickListener {
            closeOnboarding()
        }

        done_button.setOnClickListener { closeOnboarding() }
    }

    private fun closeOnboarding() {
        startActivity(Intent(this, nl.testchamber.mailordercoffeeshop.MainActivity::class.java))
        SharedPreferencesUtil.setIsFirstLaunchToFalse(applicationContext)
        finish()
    }

    private fun populateOnboardingAdapter(): OnboardingViewPagerAdapter {
        val adapter = OnboardingViewPagerAdapter(supportFragmentManager)
        onboardingSlides.forEach { fragments.add(OnboardingFragment.newInstance(it.title, it.drawable)) }
        adapter.addFragments(fragments)
        return adapter
    }

    private fun setViewPagerBehaviour() {
        circle_page_indicator.setOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) =
                    if (position < fragments.lastIndex) {
                        done_button.visibility = View.GONE
                        go_on_button.visibility = View.VISIBLE
                    } else {
                        go_on_button.visibility = View.GONE
                        done_button.visibility = View.VISIBLE
                    }

            override fun onPageSelected(position: Int) {
                currentPage = position
            }
        })
    }
}

data class OnboardingSlide(val title: String, val drawable: Int)
