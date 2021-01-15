package inventory.machtwatch.bamsppob.feature.model

import java.io.Serializable

data class ResponseListDenomination(val code: Int, val message: String, val data: List<DenomList>)

data class DenomList(
    val status: String,
    val icon_url: String,
    val pulsa_code: String,
    val pulsa_op: String,
    val pulsa_nominal: String,
    val pulsa_detail: String,
    val pulsa_price: Double,
    val pulsa_type: String,
    val masaaktif: String
): Serializable