package inventory.machtwatch.bamsppob.feature.listdenom

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.feature.model.DataResponse
import inventory.machtwatch.bamsppob.feature.model.DenomList
import inventory.machtwatch.bamsppob.utils.UtilsApp
import inventory.machtwatch.bamsppob.utils.loadImage
import kotlinx.android.synthetic.main.item_list_denom_pulsa.view.*
import kotlinx.android.synthetic.main.item_list_paketdata.view.*

class ListDenomPaketDataAdapter(private val list: List<DataResponse>, val listener: OnPulsaClick) : RecyclerView.Adapter<ListDenomPaketDataAdapter.ViewHolderPaketData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPaketData {
        return ViewHolderPaketData(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_paketdata, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolderPaketData, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(list[position])
        }
    }

    class ViewHolderPaketData(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(data: DataResponse) {
            itemView.iv_paket_data.loadImage(data.icon_url)
            itemView.tv_desc.text = data.pulsa_nominal
            itemView.tv_masaaktif.text = "${data.masaaktif} hari"
            itemView.tv_nominal.text = UtilsApp.formatPrice(data.pulsa_price)
        }
    }

    interface OnPulsaClick {
        fun onItemClick(data: DataResponse)
    }
}
