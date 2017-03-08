package com;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        Dataset dataset = null;
        try {
           dataset  = fileReader.LoadDataset(new String[]{"cw2DataSet1.csv","cw2DataSet2.csv"});
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (dataset==null){
            return;
        }
        be.abeel.util.Pair<Dataset,Dataset> datasets = fileReader.getMixData(dataset,0.8);

        KNN knn = new KNN();
        knn.BuildClassifier(datasets.x());
        knn.getAccuracy(datasets.y());

        NearestMean nearestMean = new NearestMean();
        nearestMean.BuildClassifier(datasets.x());
        nearestMean.getAccuracy(datasets.y());

        SVM svm = new SVM();
        svm.BuildClassifier(datasets.x());
        svm.getAccuracy(datasets.y());

        Dataset[] datasetsFolded = dataset.folds(5,new Random());
        for(Dataset data : datasetsFolded){
            for(Instance ins : data){
                Object realClass = ins.classValue();
                Object knnP = knn.getPredicted(ins);
                Object kMeanP = nearestMean.getPredicted(ins);
                Object svmP = svm.getPredicted(ins);
                System.out.println("KNN shows the number is : "+knnP);
                System.out.println("K Mean shows the number is : "+kMeanP);
                System.out.println("KNN shows the number is : "+svmP);
                System.out.println("Real number is : "+realClass);
            }
        }

    }
}
