(ns plain.someactivity
  (:import
   (android.support.v7.app AppCompatActivity)
   (android.util Log)
   (com.example.stevechan.clojurecppapplication.R$id)
   (com.example.stevechan.clojurecppapplication.R$layout)
   (javax.xml.bind DatatypeConverter))
  (:require [org.httpkit.client :as http]
            [clojure.tools.nrepl.server :as repl])
  (:gen-class
   :name "plain.someactivity.MyActivity"
   :exposes-methods {onCreate superOnCreate}
   :extends android.support.v7.app.AppCompatActivity
   :prefix "some-"))

(defn fetch [url]
  (http/get url))

(defonce this-atom (atom nil))

(defn some-onCreate [^plain.someactivity.MyActivity this ^android.os.Bundle bundle]
  (.superOnCreate this bundle)
  (.setContentView this com.example.stevechan.clojurecppapplication.R$layout/activity_main)
  (Log/i "clojure" "已启动应用...")
  (System/loadLibrary "native-lib")
  (reset! this-atom this)
  (try
    (do
      (Log/i "repl 启动中" "...")
      (repl/start-server :bind "127.0.0.1" :port 6888))
    (catch Exception e
      (Log/i "已启动" "clojure repl server")))
  #_(.start (Thread. (fn []
                       (let [data (fetch "http://www.yahoo.co.jp")]
                         (Log/i "clojure" (:body @data))))))
  )
