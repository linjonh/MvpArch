package top.jaylin.mvparch;

public interface BaseMvpView<DATA> {
    void onDataLoadSuccess(DATA dataSet, boolean isReload);

    void onDataLoadFailed(Throwable throwable, boolean isReload);

    void showLoadingView();

    void hideLoadingView();
}
