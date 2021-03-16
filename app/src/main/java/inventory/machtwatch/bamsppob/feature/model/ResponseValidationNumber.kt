package inventory.machtwatch.bamsppob.feature.model

data class ResponseValidationNumber(
    val code: Int,
    val message: String,
    val data: DataOperator
)

data class DataOperator(val operator: String, val operator_data: String)