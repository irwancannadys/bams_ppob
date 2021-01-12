package inventory.machtwatch.bamsppob.feature.listdenom

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.feature.model.DenomList
import inventory.machtwatch.bamsppob.utils.UtilsApp
import inventory.machtwatch.bamsppob.utils.UtilsApp.Companion.formatPriceNotRp
import inventory.machtwatch.bamsppob.utils.loadImage
import kotlinx.android.synthetic.main.item_list_denom_pulsa.view.*

class ListDenomPulsaAdapter(private val list: List<DenomList>, private val listener: OnPulsaClick) : RecyclerView.Adapter<ListDenomPulsaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_denom_pulsa, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(list[position])
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: DenomList) {
            itemView.iv_operator.loadImage(data.icon_url)
            itemView.tv_title_op.text = data.pulsa_op
            itemView.tv_price_nominal.text = data.pulsa_nominal
            itemView.tv_price_op.text = UtilsApp.formatPrice(data.pulsa_price)
        }
    }

    interface OnPulsaClick {
        fun onItemClick(data: DenomList)
    }
}