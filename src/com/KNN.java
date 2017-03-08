package com;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.classification.evaluation.EvaluateDataset;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

import java.util.Map;

/**
 * Created by ibrar on 3/7/17.
 */

    /*
    *   KNN k nearest neighbours algorithm Model Class
    * */

public class KNN {

    // KNN classifier object

    Classifier knn;

    /*
    *   function for initializing KNN classifier
    *
    *   @param Dataset for training classifier
    *
    * */

    public void BuildClassifier(Dataset data){
        knn = new KNearestNeighbors(1);
        knn.buildClassifier(data);
    }


    /*
    *   function for finding accuracy of classifier
    *
    *   @param Dataset(test) for cross validation
    * */


    public void getAccuracy(Dataset validationData){

        long startTime = System.nanoTime();

        System.out.println("KNN Accuracy");
        int correct = 0, wrong = 0;
        for (Instance inst : validationData) {
            Object predictedClassValue = knn.classify(inst);
            Object realClassValue = inst.classValue();
            System.out.println("Predicted Class: "+ predictedClassValue.toString()+ " -- Real Class : "+realClassValue.toString());
            if (predictedClassValue.equals(realClassValue))
                correct++;
            else
                wrong++;
        }

        System.out.println("Correct predictions  " + correct);
        System.out.println("Wrong predictions " + wrong);
        Map<Object, PerformanceMeasure> pm = EvaluateDataset.testDataset(knn, validationData);
        for(Object o:pm.keySet())
            System.out.println("Class : "+o+"--> Accuracy : "+pm.get(o).getAccuracy());
        long endtime = System.nanoTime();

        System.out.println("Completion Time : "+(endtime-startTime)/1000 );
    }

    /*
    *   function for getting classifier prediction on one instance
     *
     *   @param Intsance of test dataset
    *
    * */

    public Object getPredicted(Instance instance){
        Object predicted = knn.classify(instance);
        return predicted;

    }

}
