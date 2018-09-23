package top.jaylin.mvparch;

/**
 * @param <DATA> generic data type
 * @author jaysen.lin@foxmail.com
 */
public interface BaseMvpView<DATA> {
    /**
     * called when data load success
     *
     * @param dataSet  the loaded data of generic DATA
     * @param isReload whether reload or not
     */
    void onDataLoadSuccess(DATA dataSet, boolean isReload);

    /**
     * called when data load failed
     *
     * @param throwable an exception
     * @param isReload  whether reload or not
     */
    void onDataLoadFailed(Throwable throwable, boolean isReload);

    /**
     * show UI loading logic
     */
    void showLoadingView();

    /**
     * hide UI loading logic
     */
    void hideLoadingView();
}
