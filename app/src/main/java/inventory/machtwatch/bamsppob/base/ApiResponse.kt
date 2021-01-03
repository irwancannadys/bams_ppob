package inventory.machtwatch.bamsppob.base

class ApiResponse<T> {

    var isSuccessful: Boolean = false

    var code: Int = 0

    var body: T? = null

    lateinit var errorMessage: String

}
