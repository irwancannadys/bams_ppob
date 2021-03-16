package inventory.machtwatch.bamsppob.feature.model

data class PlnPostPaidResponse(val code: Int, val message: String, val data: ResponsePlnPostPaid)

data class ResponsePlnPostPaid(
    val tr_id: Int,
    val code: String,
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
    val desc: DescResponse
)

data class DescResponse(
    val tarif: String,
    val daya: Int,
    val lembar_tagihan: String,
    val tagihan: TagihanResponse
)

data class TagihanResponse(val detail: List<DetailResponse>)

data class DetailResponse(
    val periode: String,
    val nilai_tagihan: String,
    val admin: String,
    val denda: String,
    val total: Double
)