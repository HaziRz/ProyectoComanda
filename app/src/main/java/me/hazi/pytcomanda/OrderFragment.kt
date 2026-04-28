package me.hazi.pytcomanda

import android.graphics.Color
import android.icu.text.NumberFormat
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import me.hazi.pytcomanda.data.MenuData
import me.hazi.pytcomanda.data.MenuItem
import me.hazi.pytcomanda.util.dpToPx
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class OrderFragment : Fragment(R.layout.order_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a", Locale.getDefault())

        val textOrderDate = view.findViewById<TextView>(R.id.txtOrderDate)
        populateTable(MenuData.items)
        textOrderDate.text = currentDateTime.format(formatter)

        var subtotal = 0.0
        MenuData.items.forEach { item ->
            subtotal += item.total
        }
        var iva = subtotal * .16
        var total = subtotal + iva

        updateTexts(subtotal, iva, total)

        val buttonCheckout = view.findViewById<MaterialButton>(R.id.btnCheckout)
        buttonCheckout.setOnClickListener {
            MenuData.items.forEach { item ->
                item.quantity = 0
            }
            subtotal = 0.0
            iva = 0.0
            total = 0.0

            Toast.makeText(requireContext(), "Orden Pagada", Toast.LENGTH_LONG).show()
            deleteTableData()
            populateTable(MenuData.items)
            updateTexts(subtotal, iva, total)

            (activity as? MainActivity)?.navigateToFragment(R.id.menu_menu)

            /*val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.content, MenuFragment())
            transaction.addToBackStack(null)
            transaction.commit()*/
        }
    }

    fun updateTexts(subtotal: Double, iva: Double, total: Double) {
        val textSubTotal = view?.findViewById<TextView>(R.id.txtSubtotal)

        val subtotalFormatted = NumberFormat.getCurrencyInstance().format(subtotal)
        textSubTotal?.text = subtotalFormatted

        val textIVA = view?.findViewById<TextView>(R.id.txtIVA)

        val ivaFormatted = NumberFormat.getCurrencyInstance().format(iva)
        textIVA?.text = ivaFormatted

        val textTotal = view?.findViewById<TextView>(R.id.txtTotal)

        val totalFormatted = NumberFormat.getCurrencyInstance().format(total)
        textTotal?.text = totalFormatted
    }

    fun deleteTableData() {
        val tableLayout = view?.findViewById<TableLayout>(R.id.tableOrder)
        tableLayout?.removeViews(1, 4)
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

}