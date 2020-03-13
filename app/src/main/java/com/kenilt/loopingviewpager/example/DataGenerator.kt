package com.kenilt.loopingviewpager.example

/**
 * Created by thangnguyen on 3/11/20.
 */
object DataGenerator {
    private val colors = listOf(R.color.light_slate_gray, R.color.pink, R.color.silver, R.color.thistle, R.color.medium_aquamarine, R.color.azure, R.color.moccasin, R.color.snow)

    fun generateList(length: Int = 4): List<PageModel> {
        val dataList = ArrayList<PageModel>()
        repeat(length) {
            dataList.add(PageModel("Page ${it + 1}", colors[it % colors.size]))
        }
        return dataList
    }
}
