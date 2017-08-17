package star.iota.kansou;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

abstract class StringPresenter implements PVContract.Presenter {

    private final PVContract.View view;

    private final CompositeDisposable mCompositeDisposable;

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
                Observable.just(Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 UBrowser/6.1.3397.16 Safari/537.36")
                        .ignoreContentType(true)
                        .ignoreHttpErrors(true)
                        .timeout(60000))
                        .map(new Function<Connection, Document>() {
                            @Override
                            public Document apply(@NonNull Connection connection) throws Exception {
                                return connection.get();
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .map(new Function<Document, Bean>() {
                            @Override
                            public Bean apply(@NonNull Document document) throws Exception {
                                return dealResponse(type, document);
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

    protected abstract Bean dealResponse(int type, Document document);
}
