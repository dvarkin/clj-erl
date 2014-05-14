(ns clj-erl.static
  (:import [clojure.lang
            PersistentVector
            Sequential
            Keyword
            Symbol]
           [com.ericsson.otp.erlang
            OtpErlangObject
            OtpErlangBinary
            OtpErlangAtom
            OtpErlangChar
            OtpErlangString
            OtpErlangTuple
            OtpErlangList
            OtpErlangLong
            OtpErlangInt
            OtpErlangDouble
            OtpErlangFloat
            OtpErlangBoolean
            OtpErlangPid
            ]))

(defprotocol ErlangProto
  (encode [this] "convert static types between Clojure and Erlang"))

(extend-protocol ErlangProto
  Boolean
  (encode [this] (OtpErlangBoolean. this))
  OtpErlangBoolean
  (encode [this] (.booleanValue this))
  String
  (encode [this] (OtpErlangString. this))
  OtpErlangString
  (encode [this] (.stringValue this))
  Long
  (encode [this] (OtpErlangLong. this))
  OtpErlangLong
  (encode [this] (.longValue this))
  Double
  (encode [this] (OtpErlangDouble. this))
  OtpErlangDouble
  (encode [this] (.doubleValue this))
  Keyword
  (encode [this] (OtpErlangAtom. (name this)))
  OtpErlangAtom
  (encode [this] (keyword (.atomValue this)))
  Symbol
  (encode [this] (OtpErlangAtom. (name this)))
  Float
  (encode [this] (OtpErlangFloat. this))
  OtpErlangFloat
  (encode [this] (float (.floatValue this)))
  Character
  (encode [this] (OtpErlangChar. this))
  OtpErlangChar
  (encode [this] (.charValue this))
  Integer
  (encode [this] (OtpErlangInt. this))
  OtpErlangInt
  (encode [this] (.intValue this))
  Byte
  (encode [this] (OtpErlangBinary. this))
  OtpErlangBinary
  (encode [this] (.getObject this))
  
  Sequential
  (encode [this]
    (OtpErlangList. (into-array OtpErlangObject (map encode this))))
  OtpErlangList
  (encode [this] (apply list (map encode (seq (.elements this)))))
  OtpErlangTuple
  (encode [this] (apply vector (map encode (seq (.elements this)))))
  PersistentVector
  (encode [this]
    (OtpErlangTuple. (into-array OtpErlangObject (map encode this))))

  
  OtpErlangPid
  (encode [this] this))