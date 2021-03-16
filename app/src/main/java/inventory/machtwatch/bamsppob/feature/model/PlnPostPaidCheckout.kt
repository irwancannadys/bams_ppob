package inventory.machtwatch.bamsppob.feature.model

data class PlnPostPaidCheckout(
    val code: Int,
    val message: String,
    val data: ResponsePlnPostPaidCheckout
)

data class ResponsePlnPostPaidCheckout(
    val tr_id: Int,
    val code: String,
    val datetime: String,
    val hp: String,
    val tr_name: String,
    val period: String,
    val nominal: Double,
    val admin: Double,
    val ref_id: String,
    val response_code: String,
    val message: String,
    val price: Double,
    val selling_price: Double,
    val balance: Double,
    val noref: String,
    val desc: DescResponseCheckout
)

data class DescResponseCheckout(
    val tarif: String,
    val daya: Int,
    val lembar_tagihan: String,
    val lembar_tagihan_sisa: Int,
    val tagihan: TagihanResponseCheckOut
)

data class TagihanResponseCheckOut(val detail: List<DetailResponseCheckout>)

data class DetailResponseCheckout(
    val meter_awal: String,
    val meter_akhir: String,
    val periode: String,
    val nilai_tagihan: String,
    val admin: String,
    val denda: String,
    val total: Double
)