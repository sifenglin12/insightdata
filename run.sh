#create the file for compiling
if [ ! -d "./classes" ]; then
  mkdir classes
fi
chmod a+x run.sh
#compile and run the code
javac -d ./classes edu/utexas/orie/insightdata/InsightData.java
java -classpath ./classes edu.utexas.orie.insightdata.InsightData