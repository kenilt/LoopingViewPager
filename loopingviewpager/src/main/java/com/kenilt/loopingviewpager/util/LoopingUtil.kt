package com.kenilt.loopingviewpager.util

import androidx.viewpager.widget.PagerAdapter

/**
 * Created by Kenilt on 3/14/20.
 */
object LoopingUtil {

    /**
     * Convert pager position to internal position
     * Example: outer position is 1 will be convert to 2
     *
     * @param originalAdapter is the adapter which was defined by outer
     * @param pagerPosition is the position outer
     */
    fun getInternalPosition(originalAdapter: PagerAdapter?, pagerPosition: Int): Int {
        val count = originalAdapter?.count ?: 0
        return if (count > 1) pagerPosition + 1 else pagerPosition
    }

    /**
     * Convert internal position to pager position
     * Example: outer position is 2 will be convert to 1
     *
     * @param originalAdapter is the adapter which was defined by outer
     * @param internalPosition is the internal position
     */
    fun getPagerPosition(originalAdapter: PagerAdapter?, internalPosition: Int): Int {
        val count = originalAdapter?.count ?: 0
        return when (internalPosition) {
            0 -> count - 1
            count + 1 -> 0
            else -> internalPosition - 1
        }
    }

    /**
     * Convert internal position to pager position for paged selected
     * The index out of range will be return -1
     *
     * @param originalAdapter is the adapter which was defined by outer
     * @param internalPosition is the internal position
     */
    fun getPagerPositionForSelected(originalAdapter: PagerAdapter?, internalPosition: Int): Int {
        val count = originalAdapter?.count ?: 0
        return when (internalPosition) {
            0 -> -1
            count + 1 -> -1
            else -> internalPosition - 1
        }
    }
}
