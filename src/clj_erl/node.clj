(ns clj-erl.node
  (:require [clj-erl.static :refer :all])
  (:import  [com.ericsson.otp.erlang
             OtpNode
             OtpMbox
             OtpErlangObject])
  (:gen-class))

(defprotocol NodeProto
  (create  [this] "create Erlang node")
  (ping    [this remote-name timeout] "ping remote Erlang node")
  (names   [this] "get nodes names")
  (mbox    [this proc-name] "create and register named mbox")
  (send!   [this remote-pid message] [this remote-mbox remote-node message] "send message to Erlang node or pid")
  (recv    [this] [this timeout] "blocking receive message from Erlang node"))

(defrecord Node [name node mbox]
  NodeProto
  (create  [this] (->Node name (OtpNode. name) mbox))
  (ping    [this remote-node-name timeout] (.ping node remote-node-name timeout))
  (names   [this] (.getNames node))
  (mbox    [this proc-name] (->Node name node (.createMbox node proc-name)))
  (send!   [this remote-pid message] (->> (encode message)  (.send mbox remote-pid)))
  (send!   [this remote-mbox remote-node message] (->> (encode message) (.send mbox remote-mbox remote-node)))
  (recv    [this] (-> (.receive mbox) encode))
  (recv    [this timeout] (-> (.receive mbox timeout) encode)))

(defn ^OtpMbox init-node
  "create Erlang node and mbox"
  [^String node-name ^String process-name]
  (-> node-name
      (->Node nil nil)
      create
      (mbox process-name)))


