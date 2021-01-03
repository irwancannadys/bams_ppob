package inventory.machtwatch.bamsppob.base

import android.app.ActivityManager
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseActivity<V: BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    var mViewModel: V? = null

    private lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initToolbar()
        provideViewModel()
//        initProgressDialog()
    }

    fun provideViewModel() {
        val clazz = getViewModelClass(this::class.java)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(clazz)
    }

    private fun getViewModelClass(aClass: Class<*>): Class<V> {
        val type = aClass.genericSuperclass

        return if (type is ParameterizedType) {
            type.actualTypeArguments[0] as Class<V>
        } else {
            getViewModelClass(aClass.superclass)
        }

    }

//    private fun initProgressDialog() {
//        progressDialog = Dialog(this)
//        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        progressDialog.setContentView(R.layout.progress_dialog)
//        progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        val window = progressDialog.window
//        window?.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//        window?.setGravity(Gravity.CENTER)
//        progressDialog.setCancelable(false)
//    }

    @Synchronized
    fun showLoading() {
        if (progressDialog.isShowing) progressDialog.dismiss()
        progressDialog.show()
    }

    @Synchronized
    fun hideLoading() {
        if (progressDialog.isShowing) progressDialog.dismiss()
    }

    fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }


//    fun showTimeOutNetworkAlert() {
//        showAlertDialog(getString(R.string.caution),
//            getString(R.string.connection_timeout_message))
//    }
//
//    fun showNoNetworkAlert() {
//        showAlertDialog(getString(R.string.caution),
//            getString(R.string.no_internet_access))
//    }


    fun setToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        if (item?.itemId == android.R.id.home) {
//            finish()
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }

    protected fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    protected abstract fun initToolbar()
}