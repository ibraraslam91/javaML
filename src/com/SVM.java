package com;

import libsvm.LibSVM;
import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.evaluation.EvaluateDataset;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

import java.util.Map;

/**
 * Created by ibrar on 3/7/17.
 */
public class SVM {

    Classifier svm;

    public void BuildClassifier(Dataset data){
        svm = new LibSVM();
        svm.buildClassifier(data);
    }

    public void getAccuracy(Dataset validationData){
        long startTime = System.nanoTime();
        System.out.println("SVM Accuracy");
        int correct = 0, wrong = 0;
        for (Instance inst : validationData) {
            Object predictedClassValue = svm.classify(inst);
            Object realClassValue = inst.classValue();
            System.out.println("Predicted Class: "+ predictedClassValue.toString()+ " -- Real Class : "+realClassValue.toString());
            if (predictedClassValue.equals(realClassValue))
                correct++;
            else
                wrong++;
        }

        System.out.println("Correct Predictions  " + correct);
        System.out.println("Wrong Predictions " + wrong);
        Map<Object, PerformanceMeasure> pm = EvaluateDataset.testDataset(svm, validationData);
        for(Object o:pm.keySet())
            System.out.println("Class : "+o+"--> Accuracy : "+pm.get(o).getAccuracy());
        long endTime = System.nanoTime();
        System.out.println("Completion Time : "+(endTime-startTime)/1000 );
    }


    public Object getPredicted(Instance instance){
        Object predicted = svm.classify(instance);
        return predicted;

    }
}
