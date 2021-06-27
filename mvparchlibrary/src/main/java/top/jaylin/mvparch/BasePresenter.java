package top.jaylin.mvparch;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @param <DATA>    generic dat
 * @param <MvpView> the implementation of {@link BaseMvpView}
 * @author jaysen.lin@foxmail.com
 */
public abstract class BasePresenter<DATA, MvpView extends BaseMvpView<DATA>> {

    /**
     * add a disposable to disposables list to release them when activity or fragment is not available
     *
     * @param disposable the disposable tobe release later
     */
    public void addDisposables(Disposable disposable) {
        this.disposables.add(disposable);
    }

    private ArrayList<Disposable> disposables = new ArrayList<>();

    private WeakReference<MvpView> mvpViewRef;

    /**
     * @param mvpView the implementation of interface of mvpView
     */
    public BasePresenter(@NonNull MvpView mvpView) {
        this.mvpViewRef = new WeakReference<>(mvpView);
    }

    /**
     * @return Mvp VIEW
     */
    public MvpView getMvpView() {
        if (mvpViewRef != null) {
            return mvpViewRef.get();
        }
        return null;
    }

    /**
     * @param isReload whether reload or not
     * @param param    assigned object params arrays handled by {@link #execution(Object...)}
     */
    public void loadData(final boolean isReload, final Object... param) {
        getMvpView().showLoadingView();
        final Disposable disposable = Observable
                .create(new ObservableOnSubscribe<DATA>() {
                    @Override
                    public void subscribe(ObservableEmitter<DATA> emitter) throws Exception {
                        if (param != null && param.length > 0) {
                            DATA data = execution(param);
                            if (!emitter.isDisposed()) {
                                emitter.onNext(data);
                                emitter.onComplete();
                            }
                        } else {
                            emitter.onError(new NullPointerException("param is NULL"));
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DATA>() {
                    @Override
                    public void accept(DATA data) throws Exception {
                        if (getMvpView() == null) return;
                        getMvpView().hideLoadingView();
                        getMvpView().onDataLoadSuccess(data, isReload);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (getMvpView() == null) return;

                        getMvpView().hideLoadingView();
                        getMvpView().onDataLoadFailed(throwable, isReload);

                    }
                });
        addDisposables(disposable);
    }

    /**
     * 在loadData()方法里执行的后台任务具体实现
     *
     * @param params passed from {@link #loadData(boolean, Object...) object params}
     * @return generic DATA types
     */
    abstract protected DATA execution(Object... params);

    /**
     * dispose task or subscription
     */
    public void dispose() {
        for (int i = 0; i < disposables.size(); i++) {
            Disposable disposable = disposables.get(i);
            if (disposable != null) {
                disposable.dispose();
            }
        }
        disposables.clear();
    }
}
