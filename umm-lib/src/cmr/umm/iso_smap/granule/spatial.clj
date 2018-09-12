(ns cmr.umm.iso-smap.granule.spatial
  "Contains functions for parsing and generating the ISO SMAP granule spatial"
  (:require
   [cmr.common.xml :as cx]
   [cmr.common.util :as util]
   [cmr.umm.umm-granule :as g]
   [cmr.umm.iso-smap.spatial :as spatial]))

(def extent-path
  [:composedOf :DS_DataSet :has :MI_Metadata :identificationInfo :MD_DataIdentification
   :extent :EX_Extent :geographicElement])

(defn- orbit?
  "Returns true or false depending on the existence of the :ascending-crossing key existing in the
  spatial coverage map."
  [spatial-map]
  (some #(= :ascending-crossing %) (keys spatial-map)))

(defn xml-elem->SpatialCoverage
  "Returns a UMM SpatialCoverage from a parsed XML structure"
  [xml-struct]
  (let [spatial-elems (cx/elements-at-path xml-struct extent-path)
        geometries (flatten (map spatial/decode spatial-elems))]
    (when (seq geometries)
      (g/map->SpatialCoverage (util/remove-map-keys empty?
                                {:geometries (remove #(orbit? %) geometries)
                                 :orbit (first (filter #(orbit? %) geometries))})))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Generators

(defn generate-spatial
  "Generates the Spatial element from spatial coverage"
  [{:keys [geometries]}]
  (map spatial/encode geometries))
