package nl.testchamber.mailordercoffeeshop.onboarding

class OnboardingViewPagerAdapter(fragmentManager: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fragmentManager) {

    var fragments = ArrayList<androidx.fragment.app.Fragment>()

    fun addFragments(fragments: ArrayList<androidx.fragment.app.Fragment>) {
        this.fragments = fragments
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}