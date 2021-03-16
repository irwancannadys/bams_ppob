package inventory.machtwatch.bamsppob.feature.model

data class PaketDataResponse(val code: Int, val message: String, val data: List<DataResponse>)

data class DataResponse(
    val status: String,
    val icon_url: String,
    val pulsa_code: String,
    val pulsa_op: String,
    val pulsa_nominal: String,
    val pulsa_details: String,
    val pulsa_price: Double,
    val pulsa_type: String,
    val masaaktif: String
)