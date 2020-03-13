package com.kenilt.loopingviewpager.example

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.kenilt.loopingviewpager.example.model.PageModel
import kotlinx.android.synthetic.main.item_page.view.*


/**
 * Created by thangnguyen on 3/11/20.
 */
class TextPagerAdapter(private var mContext: Context, private var dataList: List<PageModel>): PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(R.layout.item_page, collection, false) as ViewGroup
        layout.item_txtText.text = dataList[position].text
        layout.item_txtText.setBackgroundColor(ContextCompat.getColor(mContext, dataList[position].color))
        collection.addView(layout)
        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View?)
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}
