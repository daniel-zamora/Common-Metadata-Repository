(ns cmr.es-spatial-plugin.SpatialScriptPlugin
  (:gen-class :extends org.elasticsearch.plugins.Plugin
              :implements [org.elasticsearch.plugins.ScriptPlugin]))
              ; :constructors {[Object org.elasticsearch.common.logging.ESLogger] []}
              ; :init init))
              ; :exposes-methods {doc getDoc fields getFields}
              ; :state data))

; (import 'cmr.es_spatial_plugin.SpatialScript)

; (defn- -init [intersects-fn logger]
;   [[] {:intersects-fn intersects-fn
;        :logger logger}])
;
; (defn- intersects-fn [^SpatialScript this]
;   (:intersects-fn (.data this)))
;
; (defn- logger [^SpatialScript this]
;   (:logger (.data this)))

(gen-class
  :name "SpatialSearchPlugin"
  :implements [org.elasticsearch.script.ScriptEngine]
  :prefix "plugin-")
  ; :state "state"
  ; :init "init"
  ; :constructors {[] []}

(defn plugin-complie
  ""
  [scriptName scriptSource context params])
  ; (let [intersects? (var-get (find-var 'cmr.es-spatial-plugin.spatial-script-helper/doc-intersects?))]
  ;   (intersects? (logger this) (.getFields this) (intersects-fn this))))

(defn -getScriptEngine
  ""
  [settings context]
  (new SpatialSearchPlugin))

; (defn -run [^SpatialScript this]
;   (let [intersects? (var-get (find-var 'cmr.es-spatial-plugin.spatial-script-helper/doc-intersects?))]
;     (intersects? (logger this) (.getFields this) (intersects-fn this))))
