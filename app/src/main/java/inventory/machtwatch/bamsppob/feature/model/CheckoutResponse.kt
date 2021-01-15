package inventory.machtwatch.bamsppob.feature.model

data class CheckoutResponse(val code: Int, val message: String, val data: DataCheckout)

data class DataCheckout(
    val ref_id: String,
    val status: Int,
    val price: Double,
    val message: String,
    val balance: Double,
    val tr_id: Double,
    val rc: String,
    val code: String,
    val hp: String
)