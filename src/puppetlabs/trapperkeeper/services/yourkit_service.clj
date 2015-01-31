(ns puppetlabs.trapperkeeper.services.yourkit-service
  (:import  [com.yourkit.api Controller])
  (:require [clojure.tools.logging :as log]
            [compojure.core :as compojure]
            [puppetlabs.trapperkeeper.services.yourkit-core :as core]
            [puppetlabs.trapperkeeper.core :as trapperkeeper]
            [puppetlabs.trapperkeeper.services :as tk-services]))

(defprotocol YourKitService
  "YourKitService protocol"
  (take-snapshot! [this]))

(defn get-controller
  [yourkit-config]
  (if (:enabled yourkit-config)
    (let [yourkit-controller (Controller.)]
      (log/info "YourKit profiling enabled")
      yourkit-controller)))

(trapperkeeper/defservice yourkit-service
  YourKitService
  [[:ConfigService get-in-config]
   [:WebroutingService add-ring-handler get-route]]
  (init [this context]
    (log/info "Initializing YourKit service")
    (let [url-prefix (get-route this)
          controller (get-controller (get-in-config [:yourkit]))]
      (add-ring-handler
          this
          (compojure/context url-prefix []
                             (core/app controller)))
      (assoc context :controller controller)))
  (take-snapshot! [this]
    (core/take-snapshot! (:controller (tk-services/service-context this)))))
