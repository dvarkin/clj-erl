(ns clj-erl.static-test
  (:require [clojure.test   :refer :all]
            [clj-erl.static :refer :all])
  (:import [com.ericsson.otp.erlang
            OtpErlangObject]))

(deftest static-types-test
  (testing "boolean test fail"
    (is (=  true (encode (encode true)))))
  (testing "string test fail"
    (is (= "string test" (encode (encode "string test")))))
  (testing "integer test fail"
    (is (= (int 1) (encode (encode (int 1))))))
  (testing "long test fail"
    (is (= 1 (encode (encode 1)))))
  (testing "double test fail"
    (is (= 1.2 (encode (encode 1.2)))))
  (testing "keyword test fail"
    (is (= :a (encode (encode :a)))))
  (testing "symbol test fail"
    (is (= :a (encode (encode 'a)))))
  (testing "float test fail"
    (is (= (float 1.2) (encode (encode (float 1.2))))))
  (testing "char test fail"
    (is (= \a (encode (encode \a)))))
  (testing "binary object fail"
    (is
     (let [ o (byte 123)] (= o (encode (encode o))))))
  (testing "vector test fail"
    (is (let [v [1 2 "s" :a]]
          (= v (encode (encode v))))))
  (testing "list test fail"
    (is (let [l '(1 2 "s" :a)]
          (= l (encode (encode l)))))))