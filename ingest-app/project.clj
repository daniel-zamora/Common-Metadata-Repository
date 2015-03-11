(defproject nasa-cmr/cmr-ingest-app "0.1.0-SNAPSHOT"
  :description "Ingest is an external facing CMR service facilitating providers to create and  update their concepts in CMR. Internally it delegates concept persistence operations to metadata db and indexer micro services."

  :url "***REMOVED***projects/CMR/repos/cmr/browse/ingest-app"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [nasa-cmr/cmr-system-trace-lib "0.1.0-SNAPSHOT"]
                 [nasa-cmr/cmr-umm-lib "0.1.0-SNAPSHOT"]
                 [nasa-cmr/cmr-transmit-lib "0.1.0-SNAPSHOT"]
                 [nasa-cmr/cmr-acl-lib "0.1.0-SNAPSHOT"]
                 [nasa-cmr/cmr-message-queue-lib "0.1.0-SNAPSHOT"]
                 [compojure "1.3.2"]
                 [ring/ring-core "1.3.2" :exclusions [clj-time]]
                 [ring/ring-json "0.3.1"]
                 [clj-http "1.0.1"]
                 [org.clojure/tools.reader "0.8.15"]
                 [org.clojure/tools.cli "0.3.1"]
                 [drift "1.5.2"]

                 ;; Database related
                 [org.quartz-scheduler/quartz-oracle "2.1.7"]
                 [nasa-cmr/cmr-oracle-lib "0.1.0-SNAPSHOT"]

                 ]
  :plugins [[lein-test-out "0.3.1"]
            [drift "1.5.2"]
            [lein-exec "0.3.2"]
            [lein-modules "0.3.11"]]

  :repl-options {:init-ns user}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]
                        [org.clojure/tools.namespace "0.2.10"]
                        [org.clojars.gjahad/debug-repl "0.3.3"]]
         :source-paths ["src" "dev" "test"]}
   :uberjar {:main cmr.ingest.runner
             :aot :all}}


  :aliases {;; Database migrations run by executing "lein migrate"
            "create-user" ["exec" "-p" "./support/create_user.clj"]
            "drop-user" ["exec" "-p" "./support/drop_user.clj"]
            ;; Prints out documentation on configuration environment variables.
            "env-config-docs" ["exec" "-ep" "(do (use 'cmr.common.config) (print-all-configs-docs))"]})


