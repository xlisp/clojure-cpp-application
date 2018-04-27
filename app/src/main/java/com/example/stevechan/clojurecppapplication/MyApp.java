package com.example.stevechan.clojurecppapplication;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;

import clojure.lang.IFn;
import clojure.lang.RT;

public class MyApp extends Application {

    static public final String TAG = "clojure";

    @Override
    public void onCreate() {
        super.onCreate();


        try {
            Class dalvikCLclass = Class.forName("clojure.lang.DalvikDynamicClassLoader");
            Method setContext = dalvikCLclass.getMethod("setContext", Context.class);
            setContext.invoke(null, this);
            Log.i(TAG, "DalvikDynamicClassLoader load clojure ok ............");
            String histr = RT.var("clojure.core", "inc").invoke(10).toString();
            Log.i("把Clojure当成Java来用,测试成功! (inc 10) => ", histr);
        } catch (ClassNotFoundException e) {
            Log.i(TAG, "DalvikDynamicClassLoader is not found, probably Skummet is used.");
        } catch (Exception e) {
            Log.e(TAG, "setContext method not found, check if your Clojure dependency is correct.");
        }

    }

}
