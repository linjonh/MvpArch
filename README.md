# MVP Architecture lib for android
[ ![Download](https://api.bintray.com/packages/jaysen/Android-MVP-Arch/jaylin-mvparch/images/download.svg) ](https://bintray.com/jaysen/Android-MVP-Arch/jaylin-mvparch/_latestVersion)

toc

### MVP arch
- BasePresenter
    ```java
    //ExampleData.java
    public class ExampleData{

    }

    //ExampleP.java
    public class ExampleP extends BasePresenter<ExampleData,ExampleMvpView>{
        public ExampleP(@NonNull MainMvpView mvpView) {
            super(mvpView);
        }
        ...
    }

    ```
- BaseMvpView
    ```java
      //ExampleMvpView.java
      public interface ExampleMvpView extends BaseMvpView<ExampleData> {
           //add other method if needed
      }

      //MainActivity.java
      public class MainActivity entends AppCompatActivity implement ExampleMvpView{

          public void onDataLoadSuccess(DATA dataSet, boolean isReload){
            //do something
          }

          public void onDataLoadFailed(Throwable throwable, boolean isReload){
            //do something
          }

          public void showLoadingView(){
             //do something
          }
          public void hideLoadingView(){
             //do something
          }

      }
    ```


    ```


- MyLog

  A simplified log util class



