package inventory.machtwatch.bamsppob.feature.history

import android.content.Context
import android.graphics.Typeface
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.feature.model.DataTransaction
import inventory.machtwatch.bamsppob.feature.model.ResponseHistoryTransaction
import inventory.machtwatch.bamsppob.utils.UtilsApp
import kotlinx.android.synthetic.main.item_list_transaction.view.*

class HistoryAdapter(val context: Context, val list: List<DataTransaction>, val clickItem: OnDetailsClick) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_transaction, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position], context)

        if (list[position].body != null) {
            holder.itemView.ll_details.setOnClickListener {
                clickItem.onItemClick(list[position])
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(historyTransaction: DataTransaction, context: Context) {
            if (historyTransaction.status == "failed"){
                itemView.tv_status_detail.background = ContextCompat.getDrawable(context, R.drawable.background_rounded_status_failed)
                itemView.tv_status_detail.setTextColor(context.getColor(R.color.black))
            }
            if (historyTransaction.body == null) {
                itemView.tv_transaction_id.setTypeface(itemView.tv_status_detail.typeface, Typeface.ITALIC)
                itemView.tv_transaction_id.text = "On Process"
                itemView.iv_arrow.visibility = View.GONE
            }
            itemView.tv_status_detail.text = historyTransaction.status
            itemView.tv_date_detail.text = UtilsApp.getFormattedDateSimple(historyTransaction.created_at)
            itemView.tv_hp.text = historyTransaction.hp
        }
    }

    interface OnDetailsClick {
        fun onItemClick(historyTransaction: DataTransaction)
    }
}