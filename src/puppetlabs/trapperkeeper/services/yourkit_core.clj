(ns puppetlabs.trapperkeeper.services.yourkit-core
  (:require [clojure.tools.logging :as log]
            [compojure.core :as compojure]))

(defn take-snapshot!
  [controller]
  (let [result (if controller
                 (do
                   (log/info "Capturing snapshot")
                   (str "Snapshot captured: "
                        (.captureMemorySnapshot controller)))
                 "Snapshots not enabled")]
    (log/info result)
    result))

(defn app
  [controller]
  (compojure/routes
    (compojure/GET "/snapshot" []
      (fn [_]
        (let [response (take-snapshot! controller)]
          {:status  200
           :headers {"Content-Type" "text/plain"}
           :body    response})))))