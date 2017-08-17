package star.iota.kansou;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

abstract class StringPresenter implements PVContract.Presenter {

    private final PVContract.View view;

    private final CompositeDisposable mCompositeDisposable;

    @SuppressWarnings("unchecked")
    StringPresenter(PVContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void get(final int type, String url) {
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, Bean>() {
                            @Override
                            public Bean apply(@NonNull Response<String> s) throws Exception {
                                return dealResponse(type, s.body());
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Bean>() {
                            @Override
                            public void accept(@NonNull Bean result) throws Exception {
                                view.success(result);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                view.error(throwable.getMessage());
                            }
                        })
        );
    }

    protected abstract Bean dealResponse(int type, String s);
}
