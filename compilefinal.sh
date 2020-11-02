#!/usr/bin/env bash

ARTIFACT=${1}
MAINCLASS=${2}
VERSION=${3}

JAR="${ARTIFACT}-${VERSION}.jar"
echo "Packaging $ARTIFACT with Maven"
mvn install -DskipTests

JAR="$ARTIFACT-$VERSION.jar"
rm -f $ARTIFACT
echo "Unpacking $JAR"
mkdir target/native-image
cd target/native-image
jar -xvf ../$JAR >/dev/null 2>&1
mkdir BOOT-INF
mkdir BOOT-INF/classes
cp -R META-INF BOOT-INF/classes

LIBPATH=`find BOOT-INF/lib | tr '\n' ':'`
CP=BOOT-INF/classes:$LIBPATH

echo "Generating reflection files for $ARTIFACT"
rm -rf graal/META-INF 2>/dev/null
mkdir -p graal/META-INF/native-image

GRAALVM_VERSION=`native-image --version`

time native-image \
  --verbose --no-server \
  --initialize-at-build-time=org.apache.el.parser.SimpleNode \
  --report-unsupported-elements-at-runtime \
  --allow-incomplete-classpath \
  --enable-all-security-services \
  -H:EnableURLProtocols=http,jar \
  -H:ConfigurationFileDirectories=../\
  -H:ResourceConfigurationFiles=../../resource-config.json \
  -H:ReflectionConfigurationFiles=../../reflect-config.json \
  -H:JNIConfigurationFiles=../../jni-config.json \
  -H:DynamicProxyConfigurationFiles=../../proxy-config.json \
  -H:Name=$ARTIFACT \
  -H:+ReportExceptionStackTraces \
  --no-fallback \
  -Dsun.rmi.transport.tcp.maxConnectionThreads=10 \
  -Dspring.native.remove-jmx-support=true \
  -cp $CP $MAINCLASS 
  
