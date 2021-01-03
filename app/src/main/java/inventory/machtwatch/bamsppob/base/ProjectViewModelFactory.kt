package inventory.machtwatch.bamsppob.base

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ProjectViewModelFactory @Inject
internal constructor(private val mCreators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    @NonNull
    override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = mCreators[modelClass]
        if (creator == null) {
            for ((key, value) in mCreators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("unknown model class $modelClass")
        }
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}