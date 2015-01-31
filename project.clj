(def ks-version "1.0.0")
(def tk-version "1.0.1")
(def tk-jetty9-version "1.1.0")

(defproject puppetlabs/trapperkeeper-yourkit "0.1.0-SNAPSHOT"
  :description "Snapshot generator for YourKit Java Profiler"
  :url "https://github.com/camlow325/trapperkeeper-yourkit"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.8"]
                 [org.clojure/tools.logging "0.2.6"]
                 [puppetlabs/trapperkeeper ~tk-version]
                 [puppetlabs/trapperkeeper-webserver-jetty9 ~tk-jetty9-version]]

  :profiles {:dev {:dependencies [[puppetlabs/trapperkeeper ~tk-version :classifier "test" :scope "test"]
                                  [puppetlabs/kitchensink ~ks-version :classifier "test" :scope "test"]
                                  [clj-http "0.9.2"]
                                  [ring-mock "0.1.5"]]}}

  :aliases {"tk" ["trampoline" "run" "--config" "dev-resources/config.conf"
                  "--plugins" "./lib"]}

  ; tests use a lot of PermGen (jruby instances)
  :jvm-opts ["-XX:MaxPermSize=256m"
             "-agentpath:./lib/libyjpagent.lib"]

  :main puppetlabs.trapperkeeper.main)
