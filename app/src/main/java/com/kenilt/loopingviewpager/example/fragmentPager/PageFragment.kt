package com.kenilt.loopingviewpager.example.fragmentPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import com.kenilt.loopingviewpager.example.R
import com.kenilt.loopingviewpager.example.model.PageModel
import kotlinx.android.synthetic.main.fragment_page.view.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PAGE_MODEL = "arg_page"

/**
 * A simple [Fragment] subclass.
 * Use the [PageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PageFragment : Fragment() {
    private var pageModel: PageModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pageModel = it.getSerializable(ARG_PAGE_MODEL) as PageModel?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_page, container, false)
        pageModel?.let { pageModel ->
            layout.item_txtText.text = pageModel.text
            layout.item_txtText.setBackgroundColor(ContextCompat.getColor(requireContext(), pageModel.color))
        }
        return layout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param pageModel Parameter 1.
         * @return A new instance of fragment PageFragment.
         */
        @JvmStatic
        fun newInstance(pageModel: PageModel) =
            PageFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PAGE_MODEL, pageModel)
                }
            }
    }
}
