package in.com.horizontalscrollview;

import android.app.Application;
import android.content.Context;

/**
 * Created by divum on 5/4/17.
 */

public class App extends Application {
  //  private static App instance;
    private static Context context;

  //  public static App getInstance() {
        //return instance;
   // }

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // instance = this;
        context= this;
        DiscreteScrollViewOptions.init(this);
    }
}
