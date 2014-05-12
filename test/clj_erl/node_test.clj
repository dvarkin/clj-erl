(ns clj-erl.node-test
  (:require [clojure.test   :refer :all]
            [clj-erl.node   :refer :all])
  (:import [com.ericsson.otp.erlang
            OtpErlangObject]))

(deftest node-test
  (testing "create node fail"
    (is (true? (-> (init-node "node_name@localhost" "input_box") (ping "node_name@localhost" 100))))))
