package com;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {



        FileReader fileReader = new FileReader();   // init helper class object
        Dataset dataset = null;
        try {
           dataset  = fileReader.LoadDataset(new String[]{"cw2DataSet1.csv","cw2DataSet2.csv"}); // read csv files
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (dataset==null){
            return;
        }

        // mix and splite dataset
                                          // change ratio to change train and test dataset size
        be.abeel.util.Pair<Dataset,Dataset> datasets = fileReader.getMixData(dataset,0.8);   // current dataset size is 80%(train) and 20% (test)
        // init KNN model class object
        KNN knn = new KNN();
        knn.BuildClassifier(datasets.x()); // train KNN
        knn.getAccuracy(datasets.y()); // print out KNN accuracy

        // init KNN Means model class object
        NearestMean nearestMean = new NearestMean();
        nearestMean.BuildClassifier(datasets.x());  // train KNN means
        nearestMean.getAccuracy(datasets.y());  // print out KNN means accuracy

        // init SVM model class object
        SVM svm = new SVM();
        svm.BuildClassifier(datasets.x());   // train SVM
        svm.getAccuracy(datasets.y());  //print out SVM accuracy

        // fold test dataset
                         // current fold count is set to 5
        Dataset[] datasetsFolded = datasets.y().folds(5,new Random());
        for(Dataset data : datasetsFolded){
            for(Instance ins : data){  // get prediction from all classifiers on this instance
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
