package me.hazi.pytcomanda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import me.hazi.pytcomanda.model.MenuItem

class MenuAdapter(private val items: List<MenuItem>) : BaseAdapter() {

    override fun getCount(): Int = items.size
    override fun getItem(position: Int): Any = items[position]
    override fun getItemId(position: Int): Long = items[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_menu, parent, false)

        val item = items[position]

        view.findViewById<TextView>(R.id.txtName).text = item.name
        view.findViewById<TextView>(R.id.txtPrice).text = "$${item.price}"

        /*val imageView = view.findViewById<ImageView>(R.id.imgItem)
        if (item.imageRes != 0) {
            imageView.setImageResource(item.imageRes)
        }*/

        return view
    }
}