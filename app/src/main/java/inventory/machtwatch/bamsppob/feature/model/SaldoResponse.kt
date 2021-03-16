package inventory.machtwatch.bamsppob.feature.model

data class SaldoResponse(val code: Int, val message: String, val data: ResponseSaldo)

data class ResponseSaldo(val balance: Double)