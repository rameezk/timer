(ns timer.util
  (:require [clojure.string :as str]))

(defn time->str
  "Converts an integer value of minuutes or hours into
  the str representation"
  [time]
  (if (< time 10) (str "0" time) (str time)))

(defn time->seconds
  "Convert a time str into seconds"
  [time-str]
  (let [[minutes seconds] (str/split time-str #":")]
    (+
      (* (js/parseInt minutes) 60)
      (js/parseInt seconds))))
