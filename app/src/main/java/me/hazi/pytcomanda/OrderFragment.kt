package me.hazi.pytcomanda

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import me.hazi.pytcomanda.data.MenuData
import me.hazi.pytcomanda.model.MenuItem

class OrderFragment : Fragment(R.layout.order_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateTable(MenuData.items)
    }

    fun populateTable(items: List<MenuItem>) {
        val tableLayout = view?.findViewById<TableLayout>(R.id.tableOrder)

        items.forEach { item ->
            val firstParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2f)
            val normalParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)

            val row = TableRow(requireContext()).apply {
                layoutParams = TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
                )
                setPadding(8.dpToPx(), 8.dpToPx(), 8.dpToPx(), 8.dpToPx())
            }

            val name = TextView(requireContext()).apply {
                text = item.name
                setTextColor(Color.WHITE)
                typeface = ResourcesCompat.getFont(context, R.font.lexend)
                layoutParams = firstParams
            }

            val qty = TextView(requireContext()).apply {
                text = item.quantity.toString()
                setTextColor(ContextCompat.getColor(context, R.color.text_light))
                typeface = ResourcesCompat.getFont(context, R.font.lexend)
                layoutParams = normalParams
                gravity = Gravity.CENTER
            }

            val price = TextView(requireContext()).apply {
                text = "$${item.price}"
                setTextColor(ContextCompat.getColor(context, R.color.text_light))
                typeface = ResourcesCompat.getFont(context, R.font.lexend)
                layoutParams = normalParams
                gravity = Gravity.END
            }

            val total = TextView(requireContext()).apply {
                text = "$${item.total}"
                setTextColor(Color.WHITE)
                typeface = ResourcesCompat.getFont(context, R.font.lexend)
                layoutParams = normalParams
                gravity = Gravity.END
            }

            row.addView(name)
            row.addView(qty)
            row.addView(price)
            row.addView(total)

            tableLayout?.addView(row)
        }
    }

    fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

}