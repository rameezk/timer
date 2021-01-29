(ns timer.app
  (:require [goog.events :as events]
            [reagent.dom :as rd]
            [timer.components :as c]
            [re-frame.core :as rf]
            [timer.state :as state]))

(def v 1)

(defn- version []
  [:span {:style {:position "fixed" :right "1rem" :bottom "1rem"}} v])

(defn app []
  [:div.section.has-background-dark
   {:style {:height "100vh"}}
   [:div.container
    [c/clock]
    [c/control]
    [version]]])

(defn mount-reagent []
  (rd/render app (js/document.getElementById "app")))

(defn ^:export run []
  (rf/dispatch-sync [::state/initialize])
  (mount-reagent))

(defn ^:dev/init init []
  (mount-reagent))

(defn- listen []
  (events/listen js/window "load" #(run))
  (js/setInterval #(rf/dispatch [::state/tick]) 100))

(defonce listening? (listen))

(defn ^:dev/after-load shaddow-start [] (mount-reagent))
(defn ^:dev/before-load shaddow-stop [])



