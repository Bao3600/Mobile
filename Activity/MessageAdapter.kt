package com.example.foodbuddy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class MessageAdapter //Context, child layout ID, generic object data
internal constructor(
    context: Context?,
    private val ResourceId: Int,
    `object`: List<*>?
) : ArrayAdapter<Any?>(context!!, ResourceId, `object`!!) {
    override fun getView(
        position: Int,
        textViewResourceId: View?,
        parent: ViewGroup
    ): View {
        val text = getItem(position) as String?
        val view = LayoutInflater.from(context).inflate(ResourceId, null)
        val tv1 = view.findViewById<View>(R.id.tv1) as TextView
        val tv2 = view.findViewById<View>(R.id.tv2) as TextView
        val tv3 = view.findViewById<View>(R.id.tv3) as TextView
        val s = text!!.split("  ".toRegex()).toTypedArray()
        tv1.text = s[0]
        tv2.text = s[1]
        tv3.text = s[2]
        return view
    }

}
