(ns timer.state
  (:require [re-frame.core :as rf]
            [timer.util :as u]))

(rf/reg-event-fx
  ::initialize
  (fn [_ _]
    {:db
     {:running?        nil
      :current-seconds nil}
     :dispatch [::reset]}))

(rf/reg-event-fx
  ::tick
  (fn [{:keys [db]} _]
    (let [running? (:running? db)]
      (if running? 
        (let [current-seconds (:current-seconds db)
              next            (dec current-seconds)
              done?           (neg? next)]
          (if done?
            {:db db :dispatch [::reset]}
            {:db (assoc db :current-seconds next)}))
        {:db db}))))

(rf/reg-event-db
  ::reset
  (fn [db _]
    (assoc db
           :running? false
           :current-seconds 120)))

(rf/reg-event-db
::stop
(fn [db _]
  (assoc db :running? false)))

(rf/reg-event-db
::start
(fn [db _]
  (assoc db :running? true)))

(rf/reg-sub
::running?
(fn [db _]
  (:running? db)))

(rf/reg-sub
  ::current-time
  (fn [db _]
    (let [total-seconds (:current-seconds db)
          minutes       (u/time->str (quot total-seconds 60))
          seconds       (u/time->str (mod total-seconds 60))]
      {:minutes minutes :seconds seconds})))

