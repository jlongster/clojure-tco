
(ns ctco.core
  (:require [ctco.mini-passes :as mp]
            [ctco.parse :as parse]
            [ctco.protocol :as proto]
            [ctco.util :as util]))

(def tramp (gensym 'tramp))
(def init-k (gensym 'init-k))
(def apply-k (gensym 'apply-k))

(defn apply-cps [expr]
  (condp extends? (type expr)
    proto/PCpsTriv (proto/cps-triv expr)
    proto/PCpsSrs  (proto/cps-srs expr init-k)
    :else (throw (Exception. (str "unexpected expression " expr)))))

(-> (parse/parse '(foo))
    apply-cps
    (proto/abstract-k (parse/parse apply-k))
    proto/thunkify
    proto/unparse
    (mp/overload tramp))