package inventory.machtwatch.bamsppob.feature.model

data class ResponseHistoryTransaction(val code: Int, val message: String, val data: List<DataTransaction>)

data class DataTransaction(
    val id: String,
    val ref_id: String,
    val status: String,
    val pulsa_code: String,
    val hp: String,
    val body: BodyHistory,
    val created_at: String
)

data class BodyHistory(
    val ref_id: String,
    val status: String,
    val code: String,
    val hp: String,
    val price: String,
    val balance: String,
    val tr_id: String,
    val sign: String,
    val message: String,
    val rc: String

)