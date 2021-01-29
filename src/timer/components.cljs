(ns timer.components
  (:require [re-frame.core :as rf]
            [timer.state :as state]))

(defn clock []
  (let [{:keys [minutes seconds]} @(rf/subscribe [::state/current-time])]
    [:div.level.is-mobile.mt-5
     [:div.level-item.has-text-white
      {:style {:font-size "120px"}}
      (str minutes ":" seconds)]]))


(defn start-button []
  [:button.button.is-success.mx-2.is-size-3
   {:on-click #(rf/dispatch [::state/start])}
   "Start"])

(defn stop-button []
  [:button.button.is-danger.mx-2.is-size-3
   {:on-click #(rf/dispatch [::state/stop])}
   "Stop"])

(defn reset-button []
  [:button.button.is-warning.mx-2.is-size-3
   {:on-click #(rf/dispatch [::state/reset])}
   "Reset"])


(defn control []
  (let [running? @(rf/subscribe [::state/running?])]
    [:div.level.is-mobile.mt5
     [:div.level-item
      (if running? (stop-button) (start-button))
      (reset-button)]]))
