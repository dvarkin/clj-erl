(defproject clj-erl "0.1.0"
  :description "small lib for mapping Clojure to/from Erlang throw Jinterface"
  :url "https://github.com/dvarkin/clj-erl/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.erlang.otp/jinterface "1.5.6"]
                 [lein-localrepo "0.5.3"]]
  :aot [clj-erl.node])
