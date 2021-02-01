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

(defn choose-time-button []
  [:button.button.is-info.mx-2.is-size-3
   {:on-click #(rf/dispatch [::state/choose-time])}
   "Custom Time"])

(defn control []
  (let [running?     @(rf/subscribe [::state/running?])
        choose-time? @(rf/subscribe [::state/choose-time?])]
    [:div.level.is-mobile.mt5
     [:div.level-item
      (if running? (stop-button) (start-button))
      (reset-button)
      (when (not  choose-time?) (choose-time-button))]]))

(defn choose-time[]
  (let [choose-time? @(rf/subscribe [::state/choose-time?])]
    (when choose-time? 
      [:div
       [:p.title.has-text-white "Select Time"]
       [:div.field
        [:label.label.has-text-white "Time"]
        [:div.control
         [:input.input {:type        "text"
                        :placeholder "00:00"
                        :pattern     #"a"}]
         [:span.icon.is-small.is-left
          [:i.fas.fa-clock]]]]
       [:div.field.is-grouped
        [:div.control
         [:button.button.is-link "Submit"]]]])))
