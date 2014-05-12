# clj-erl

A Clojure library designed to ... well, that part is up to you.

## Usage

```clojure

(:require [clj-erl.static :refer :all]
          [clj-erl.node   :refer :all])


(defn start-server
  \"start loop for handle messages from erlang node\"
  [^String node-name ^String process-name handler]
  (let [node (init-node node-name process-name)]
    (while true
      (handler (recv node))
      )))

```



## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
