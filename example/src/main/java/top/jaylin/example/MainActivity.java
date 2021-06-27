package top.jaylin.example;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import top.jaylin.mvparch.BaseMvpView;
import top.jaylin.mvparch.MyLog;

public class MainActivity extends AppCompatActivity implements BaseMvpView<List<String>>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyLog.e("hello");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyLog.e("onResume");

    }

    @Override
    public void onDataLoadSuccess(List<String> dataSet, boolean isReload) {

    }

    @Override
    public void onDataLoadFailed(Throwable throwable, boolean isReload) {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
}
