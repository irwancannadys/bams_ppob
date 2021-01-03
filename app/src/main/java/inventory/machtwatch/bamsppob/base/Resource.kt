package inventory.machtwatch.bamsppob.base

import androidx.annotation.Nullable

class Resource<T>(val status: Status, val data: T?, val message: String, val networkCode: Int) {
    companion object {

        private val NETWORK_LOADING = 0

        fun <T> success(@Nullable data: T, code: Int): Resource<T> {
            return Resource(Status.SUCCESS, data, "", code)
        }

        fun <T> error(@Nullable networkCode: Int, message: String): Resource<T> {
            return Resource<T>(Status.ERROR, null, message, networkCode)
        }

        fun <T> loading(@Nullable data: T): Resource<T> {
            return Resource(Status.LOADING, data, "", NETWORK_LOADING)
        }
    }
}
