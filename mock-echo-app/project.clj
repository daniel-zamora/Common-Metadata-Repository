(defproject nasa-cmr/cmr-mock-echo-app "0.1.0-SNAPSHOT"
  :description "Mocks out the ECHO REST API."
  :url "***REMOVED***projects/CMR/repos/cmr/browse/mock-echo-app"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [nasa-cmr/cmr-system-trace-lib "0.1.0-SNAPSHOT"]
                 [compojure "1.3.2"]
                 [ring/ring-core "1.3.2" :exclusions [clj-time]]
                 [ring/ring-json "0.3.1"]
                 [org.clojure/tools.reader "0.8.15"]
                 [org.clojure/tools.cli "0.3.1"]]
  :plugins [[lein-test-out "0.3.1"]
            [lein-modules "0.3.11"]]
  :repl-options {:init-ns user}
  :profiles
  {:dev {:dependencies [[org.clojure/tools.namespace "0.2.10"]
                        [org.clojars.gjahad/debug-repl "0.3.3"]
                        [nasa-cmr/cmr-transmit-lib "0.1.0-SNAPSHOT"]]
         :source-paths ["src" "dev" "test"]}
   :uberjar {:main cmr.mock-echo.runner
             :aot :all}})


