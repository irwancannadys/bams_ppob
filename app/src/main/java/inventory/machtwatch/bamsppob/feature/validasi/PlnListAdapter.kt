package inventory.machtwatch.bamsppob.feature.validasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.feature.model.DenomList
import inventory.machtwatch.bamsppob.utils.UtilsApp
import inventory.machtwatch.bamsppob.utils.loadImage
import kotlinx.android.synthetic.main.item_list_denom_pln.view.*

class PlnListAdapter(private val list: List<DenomList>, val click: PlnItemClickListener) : RecyclerView.Adapter<PlnListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlnListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_denom_pln, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PlnListAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            click.onItemclick(list[position])
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(data: DenomList) {
            itemView.iv_image_pln.loadImage(data.icon_url)
            itemView.tv_op_pln.text = data.pulsa_op
            itemView.tv_nominal_pln.text = data.pulsa_nominal
            itemView.tv_price_pln.text = UtilsApp.formatPrice(data.pulsa_price)
        }
    }

    interface PlnItemClickListener {
        fun onItemclick(data: DenomList)
    }
}