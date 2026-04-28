package me.hazi.pytcomanda.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import me.hazi.pytcomanda.R
import me.hazi.pytcomanda.data.MenuItem

class MenuAdapter(private val items: List<MenuItem>) : BaseAdapter() {

    override fun getCount(): Int = items.size
    override fun getItem(position: Int): MenuItem = items[position]
    override fun getItemId(position: Int): Long = items[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_menu, parent, false)

        val item = items[position]
        val card = view.findViewById<MaterialCardView>(R.id.Card)
        val txtQty = view.findViewById<TextView>(R.id.txtQuantity)
        val btnAdd = view.findViewById<View>(R.id.btnAdd)
        val btnRemove = view.findViewById<View>(R.id.btnRemove)

        txtQty.text = item.quantity.toString()
        updateCardStyle(card, item.quantity)

        btnAdd.setOnClickListener {
            item.quantity++
            txtQty.text = item.quantity.toString()
            updateCardStyle(card, item.quantity)
        }

        btnRemove.setOnClickListener {
            if (item.quantity > 0) {
                item.quantity--
                txtQty.text = item.quantity.toString()
                updateCardStyle(card, item.quantity)
            }
        }

        view.findViewById<TextView>(R.id.txtName).text = item.name
        view.findViewById<TextView>(R.id.txtPrice).text = "$${item.price}"

        val imageView = view.findViewById<ImageView>(R.id.imgItem)
        if (item.imageRes != 0) {
            imageView.setImageResource(item.imageRes)
        }

        return view
    }

    private fun updateCardStyle(card: MaterialCardView, quantity: Int) {
        if (quantity > 0) {
            card.strokeWidth = 1.dpToPx()
        } else {
            card.strokeWidth = 0
        }
    }
}