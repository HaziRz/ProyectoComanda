package me.hazi.pytcomanda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import me.hazi.pytcomanda.data.MenuData
import me.hazi.pytcomanda.util.MenuAdapter

class MenuFragment : Fragment(R.layout.menu_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridView: GridView = view.findViewById(R.id.menuItems)
        val adapter = MenuAdapter(MenuData.items)
        gridView.adapter = adapter
        gridView.setOnItemClickListener { _, _, position, _ ->
            val clickedItem = MenuData.items[position]

            when(clickedItem.id) {
                1 -> { /* Specific logic for Paella */ }
                2 -> { /* Specific logic for Lasaña */ }
                else -> {
                    // General logic: Add to order, show toast, etc.
                    println("Clicked: ${clickedItem.name} with ID: ${clickedItem.id}")
                }
            }
        }

        val btnMore: MaterialButton = view.findViewById(R.id.btnOrderMore)
        btnMore.setOnClickListener { v ->
            val wrapper = ContextThemeWrapper(requireContext(), R.style.Theme_App_PopupMenu)
            val popup = PopupMenu(wrapper, v)
            popup.menuInflater.inflate(R.menu.order_options_menu, popup.menu)

            popup.setOnDismissListener {
                btnMore.isChecked = false
            }

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.option_new -> {
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

        val btnOrder: View = view.findViewById(R.id.btnOrderMain)
        btnOrder.setOnClickListener {

        }
    }

}