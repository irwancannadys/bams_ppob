package inventory.machtwatch.bamsppob.base;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public abstract class RemoteResource<Response> {

    private final MediatorLiveData<Resource<Response>> mResponse = new MediatorLiveData<>();

    @MainThread
    protected RemoteResource() {
        mResponse.setValue(Resource.Companion.loading(null));
        LiveData<ApiResponse<Response>> apiResponse = createCall();
        mResponse.addSource(apiResponse, response -> {
            if (response.isSuccessful()) {
                setValue(Resource.Companion.success(response.getBody(), response.getCode()));
            } else {
                setValue(Resource.Companion.error(response.getCode(), response.getErrorMessage()));
            }
        });
    }

    @MainThread
    private void setValue(Resource<Response> newValue) {
        if (!equals(mResponse.getValue(), newValue)) {
            mResponse.setValue(newValue);
        }
    }

    private static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    public LiveData<Resource<Response>> asLiveData() {
        return mResponse;
    }

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<Response>> createCall();
}
