package com.kenilt.loopingviewpager.widget

import android.content.Context
import android.database.DataSetObserver
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * Created by Kenilt on 3/11/20.
 */
class LoopingViewPager : ViewPager {

    var originalAdapter: PagerAdapter? = null
    var internalAdapter: PagerAdapter? = null
    private val pageChangeListenerMap = HashMap<OnPageChangeListener, InternalOnPageChangeListener>()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        addOnPageChangeListener(object : SimpleOnPageChangeListener() {
            override fun onPageScrollStateChanged(state: Int) {
                if (state == SCROLL_STATE_IDLE) {
                    handleSetCurrentItem(getSuperCurrentItem())
                }
            }
        })
    }

    override fun getAdapter(): PagerAdapter? {
        return originalAdapter
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        if (adapter == null) {
            super.setAdapter(null)
            return
        }

        internalAdapter = InternalLoopingAdapter(adapter)
        originalAdapter = adapter
        super.setAdapter(internalAdapter)
        originalAdapter?.registerDataSetObserver(object: DataSetObserver() {
            override fun onChanged() {
                internalAdapter?.notifyDataSetChanged()
            }

            override fun onInvalidated() {
                internalAdapter?.notifyDataSetChanged()
            }
        })
        post {
            if (adapter.count > 1) {
                super.setCurrentItem(1, false)
            }
        }
    }

    override fun getCurrentItem(): Int {
        return getPagerPosition(super.getCurrentItem())
    }

    fun getSuperCurrentItem(): Int {
        return super.getCurrentItem()
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(getInternalPosition(item))
    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        val count = adapter?.count ?: 0
        if (item == 0 && currentItem == count - 1) {
            super.setCurrentItem(count + 2, smoothScroll)
        } else {
            super.setCurrentItem(getInternalPosition(item), smoothScroll)
        }
    }

    override fun addOnPageChangeListener(listener: OnPageChangeListener) {
        val internalListener = InternalOnPageChangeListener(listener)
        pageChangeListenerMap[listener] = internalListener
        super.addOnPageChangeListener(internalListener)
    }

    override fun removeOnPageChangeListener(listener: OnPageChangeListener) {
        pageChangeListenerMap.remove(listener)?.let {
            super.removeOnPageChangeListener(it)
        }
    }

    private fun handleSetCurrentItem(position: Int) {
        val itemCount = internalAdapter?.count ?: 0
        if (itemCount <= 1) return

        val lastPosition = itemCount - 1
        if (position == 0) {
            super.setCurrentItem(lastPosition - 1, false)
        } else if (position == lastPosition) {
            super.setCurrentItem(1, false)
        }
    }

    private fun getInternalPosition(position: Int): Int {
        val count = originalAdapter?.count ?: 0
        return if (count > 1) position + 1 else position
    }

    private fun getPagerPosition(position: Int): Int {
        val count = originalAdapter?.count ?: 0
        return when (position) {
            0 -> count - 1
            count + 1 -> 0
            else -> position - 1
        }
    }

    inner class InternalLoopingAdapter(private val pagerAdapter: PagerAdapter): PagerAdapter() {

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return pagerAdapter.isViewFromObject(view, `object`)
        }

        override fun getCount(): Int {
            val itemsSize = pagerAdapter.count
            return if (itemsSize > 1) itemsSize + 2 else itemsSize
        }

        override fun startUpdate(container: ViewGroup) {
            pagerAdapter.startUpdate(container)
        }

        override fun getItemPosition(`object`: Any): Int {
            return pagerAdapter.getItemPosition(`object`)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            return pagerAdapter.instantiateItem(container, getPagerPosition(position))
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            pagerAdapter.destroyItem(container, getPagerPosition(position), `object`)
        }

        override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
            pagerAdapter.setPrimaryItem(container, position, `object`)
        }

        override fun finishUpdate(container: ViewGroup) {
            pagerAdapter.finishUpdate(container)
        }

        override fun saveState(): Parcelable? {
            return pagerAdapter.saveState()
        }

        override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
            pagerAdapter.restoreState(state, loader)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return pagerAdapter.getPageTitle(position)
        }

        override fun getPageWidth(position: Int): Float {
            return pagerAdapter.getPageWidth(position)
        }
    }

    inner class InternalFragmentPagerAdapter(fm: FragmentManager, private val pagerAdapter: FragmentPagerAdapter) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return pagerAdapter.getItem(position)
        }

        override fun getCount(): Int {
            val itemsSize = pagerAdapter.count
            return if (itemsSize > 1) itemsSize + 2 else itemsSize
        }

        override fun startUpdate(container: ViewGroup) {
            pagerAdapter.startUpdate(container)
        }

        override fun getItemPosition(`object`: Any): Int {
            return pagerAdapter.getItemPosition(`object`)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            return pagerAdapter.instantiateItem(container, getPagerPosition(position))
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            pagerAdapter.destroyItem(container, getPagerPosition(position), `object`)
        }

        override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
            pagerAdapter.setPrimaryItem(container, position, `object`)
        }

        override fun finishUpdate(container: ViewGroup) {
            pagerAdapter.finishUpdate(container)
        }

        override fun saveState(): Parcelable? {
            return pagerAdapter.saveState()
        }

        override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
            pagerAdapter.restoreState(state, loader)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return pagerAdapter.getPageTitle(position)
        }

        override fun getPageWidth(position: Int): Float {
            return pagerAdapter.getPageWidth(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
    }

    inner class InternalOnPageChangeListener(private val pageChangeListener: OnPageChangeListener) : OnPageChangeListener {

        override fun onPageScrollStateChanged(state: Int) {
            pageChangeListener.onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            val pagerPosition = getPagerPosition(position)
            val lastPosition = originalAdapter?.let { it.count - 1 } ?: 0
            val isScrollToLooping = pagerPosition == lastPosition && positionOffset != 0f
            val positionLoop = if (isScrollToLooping) 0 else getPagerPosition(position)
            val offset = if (isScrollToLooping) lastPosition * (1 - positionOffset) else positionOffset
            pageChangeListener.onPageScrolled(positionLoop, offset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            pageChangeListener.onPageSelected(getPagerPosition(position))
        }
    }
}
