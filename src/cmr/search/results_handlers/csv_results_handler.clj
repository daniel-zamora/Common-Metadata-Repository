(ns cmr.search.results-handlers.csv-results-handler
  "Handles the CSV results format and related functions."
  (:require [cmr.search.data.elastic-results-to-query-results :as elastic-results]
            [cmr.search.data.elastic-search-index :as elastic-search-index]
            [cmr.search.services.query-service :as qs]
            [clojure.data.csv :as csv]
            [clojure.string :as str])
  (:import
    [java.io StringWriter]))

(def CSV_HEADER
  ["Granule UR","Producer Granule ID","Start Time","End Time","Online Access URLs","Browse URLs","Cloud Cover","Day/Night","Size"])

(defmethod elastic-search-index/concept-type+result-format->fields [:granule :csv]
  [concept-type result-format]
  ["granule-ur"
   "producer-gran-id"
   "start-date"
   "end-date"
   "downloadable-urls"
   "cloud-cover"
   "day-night"
   "size"])

(defmethod elastic-results/elastic-result->query-result-item :csv
  [context query elastic-result]
  (let [{[granule-ur] :granule-ur
         [producer-gran-id] :producer-gran-id
         [start-date] :start-date
         [end-date] :end-date
         [downloadable-urls] :downloadable-urls
         [cloud-cover] :cloud-cover
         [day-night] :day-night
         [size] :size} (:fields elastic-result)
        start-date (when start-date (str/replace (str start-date) #"\+0000" "Z"))
        end-date (when end-date (str/replace (str end-date) #"\+0000" "Z"))]
    [granule-ur producer-gran-id start-date end-date downloadable-urls
               (str cloud-cover) day-night (str size)]))


(defmethod qs/search-results->response :csv
  [context query results]
  (let [{:keys [hits took items]} results
        rows (conj items CSV_HEADER)
        string-writer (StringWriter.)]
    (csv/write-csv string-writer rows)
    (str string-writer)))
