package tk.zwander.rootactivitylauncher.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.extras_dialog.view.*
import tk.zwander.rootactivitylauncher.R
import tk.zwander.rootactivitylauncher.adapters.ExtrasDialogAdapter
import tk.zwander.rootactivitylauncher.util.findActionForComponent
import tk.zwander.rootactivitylauncher.util.findExtrasForComponent
import tk.zwander.rootactivitylauncher.util.updateActionForComponent
import tk.zwander.rootactivitylauncher.util.updateExtrasForComponent


class ExtrasDialog(context: Context, componentKey: String) : MaterialAlertDialogBuilder(context) {
    private val view: View = LayoutInflater.from(context).inflate(R.layout.extras_dialog, null)
    private val adapter = ExtrasDialogAdapter()

    init {
        setView(view)

        adapter.setItems(context.findExtrasForComponent(componentKey))
        view.action.setText(context.findActionForComponent(componentKey))
        view.list.adapter = adapter

        setTitle(R.string.extras)
        setNegativeButton(android.R.string.cancel, null)
        setPositiveButton(android.R.string.ok) { _, _ ->
            val newData = adapter.currentData()

            context.updateExtrasForComponent(componentKey, newData)
            context.updateActionForComponent(componentKey, view.action.text?.toString())
        }
    }
}