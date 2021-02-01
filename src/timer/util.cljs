(ns timer.util)

(defn time->str
  "Converts an integer value of minuutes or hours into
  the str representation"
  [time]
  (if (< time 10) (str "0" time) (str time)))
