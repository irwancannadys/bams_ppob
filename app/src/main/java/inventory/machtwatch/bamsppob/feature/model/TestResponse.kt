package inventory.machtwatch.bamsppob.feature.model

data class TestResponse(val status: Boolean, val data: MutableList<DetailData>)

data class DetailData(val id: String, val name: String)