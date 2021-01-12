package inventory.machtwatch.bamsppob.feature.checkout

import android.os.Bundle
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.base.BaseActivity

class CheckoutActivity : BaseActivity<CheckoutViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
    }

    override fun initToolbar() {
    }
}