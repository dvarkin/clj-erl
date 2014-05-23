# clj-erl

A Clojure library designed to work with Erlagn VM throw Jinterface. 

## Usage

Cast Erlang static types. 

```clojure 

(:require [clj-erl.static :refer :all])


(encode nil)  ;; clojure's nil cast to Erlang's atom null #<OtpErlangAtom null>

(encode true) ;; #<OtpErlangBoolean true>

(encode 1)    ;; #<OtpErlangLong 1>

(encode 1.2)  ;; #<OtpErlangDouble 1.2>

(encode :keyword) ;; kewords cast to atoms #<OtpErlangAtom keyword>

(encode (float 1.2)) ;; #<OtpErlangFloat 1.2000000476837158>

(encode \a) ;; #<OtpErlangChar 97>

(encode (byte 1)) ;; #<OtpErlangBinary #Bin<75>>

(encode '(nil 1 1.2 :keyword (float 1.2) \a (byte 1))) ;; #<OtpErlangList [null,1,1.2,keyword,[float,1.2],97,[byte,1]]>

;; Vectors cast to tuples
(encode [nil 1 1.2 :keyword (float 1.2) \a (byte 1)]) ;; #<OtpErlangTuple {null,1,1.2,keyword,1.2000000476837158,97,#Bin<75>}>

```

Look for more examples in tests.

Working with Erlangs processes.


```clojure

(:require [clj-erl.static :refer :all]
          [clj-erl.node   :refer :all])

(defn echo-handler 
	  [self message-from-erlang-process]
	  (send! self erlang-pid message-from-erlang-process))

(defn start-server
  "start loop for handle messages from erlang node."
  [^String node-name ^String process-name handler]
  (let [self (init-node node-name process-name)]
    (while true
      (echo-handler self (recv self)))))

;; 'self' is Node type that support common process operations:
;; 1.  (ping self erlang-node 1000) - ping with timeout
;; 2.  (names self) - get available node names
;; 3.  (send! self remote-pid message) or (send! self remote-proccess-name remote-node-name message)
;; 4.  (recv self) or (recv self 1000) - block process while message is comming or exit by timeout

send! and recv operations are encoding messages between Clojure and Erlang VMs.

```



## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
