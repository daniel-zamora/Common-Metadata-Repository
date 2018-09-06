#!/bin/bash

mkdir tmp-plugin
cp ../target/cmr-es-spatial-plugin-0.1.0-SNAPSHOT.jar tmp-plugin
cp ../resources/plugin-descriptor.properties tmp-plugin
zip -rj cmr-es-spatial.zip tmp-plugin
docker cp cmr-es-spatial.zip cmr-graph-elastic:/tmp/cmr-es-spatial.zip 
docker exec cmr-graph-elastic /usr/share/elasticsearch/bin/elasticsearch-plugin install --verbose file:/tmp/cmr-es-spatial.zip
rm -rf tmp-plugin
rm cmr-es-spatial.zip
