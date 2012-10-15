(ns entry
  (:require [ctco.core :as ctco]))

(defn -main [& args]
  (ctco/ctco (defn foo [x y] x))
  (foo 3 4))

(set! *main-cli-fn* -main)

