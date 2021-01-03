package inventory.machtwatch.bamsppob.base

import inventory.machtwatch.bamsppob.di.DaggerAppComponent


class AppInjector {
    companion object {
        fun init(app: App) {
            DaggerAppComponent
                .builder()
                .application(app)
                .build()
                .inject(app)
        }
    }
}