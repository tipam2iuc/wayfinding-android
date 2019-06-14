package com.example.wayfindingdev.Adapter

import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wayfindingdev.R

class RecyclerSectionItemDecoration(val headerHeight:Int, val sticky:Boolean, val sectionCallback:SectionCallback, private var headerView: View? = null,
                                    private var header: TextView? = null) : RecyclerView.ItemDecoration() {

    /**
     * Override
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val pos = parent.getChildAdapterPosition(view)
        if(sectionCallback.isSection(pos))
            outRect.top = headerHeight
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        if(headerView == null){
            headerView = inflateHeaderView(parent)
            header = headerView!!.findViewById(R.id.txt_group_title)
            //fixLayoutSize(headerView!!,parent)
        }

        var previousHeader: CharSequence = ""
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)

            val title = sectionCallback.getSectionHeader(position)
            header!!.text = title
            if (previousHeader != title || sectionCallback.isSection(position)) {
                drawHeader(
                    c,
                    child,
                    headerView!!
                )
                previousHeader = title
            }
        }
    }

    /**
     * other
     */
    interface SectionCallback {

        fun isSection(position: Int): Boolean

        fun getSectionHeader(position: Int): CharSequence
    }


    private fun inflateHeaderView(parent: RecyclerView): View {
        return LayoutInflater.from(parent.context).inflate(
                R.layout.group_layout,
                parent,
                false
            )
    }

//    private fun fixLayoutSize(view: View, parent: ViewGroup) {
//        val widthSpec = View.MeasureSpec.makeMeasureSpec(
//            parent.width,
//            View.MeasureSpec.EXACTLY
//        )
//        val heightSpec = View.MeasureSpec.makeMeasureSpec(
//            parent.height,
//            View.MeasureSpec.UNSPECIFIED
//        )
//
//        val childWidth = ViewGroup.getChildMeasureSpec(
//            widthSpec,
//            parent.paddingLeft + parent.paddingRight,
//            view.layoutParams.width
//        )
//        val childHeight = ViewGroup.getChildMeasureSpec(
//            heightSpec,
//            parent.paddingTop + parent.paddingBottom,
//            view.layoutParams.height
//        )
//
//        view.measure(
//            childWidth,
//            childHeight
//        )
//
//        view.layout(
//            0,
//            0,
//            view.measuredWidth,
//            view.measuredHeight
//        )
//    }

    private fun drawHeader(c: Canvas, child: View, headerView: View) {
        c.save()
        if (sticky) {
            c.translate(
                0f,
                Math.max(
                    0,
                    child.top - headerView.height
                ).toFloat()
            )
        } else {
            c.translate(
                0f,
                (child.top - headerView.height).toFloat()
            )
        }
        headerView.draw(c)
        c.restore()
    }



}