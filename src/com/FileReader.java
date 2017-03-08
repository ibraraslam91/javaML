package com;


import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.sampling.Sampling;
import net.sf.javaml.tools.data.FileHandler;

import java.io.File;
import java.io.IOException;

/**
 * Created by ibrar on 3/7/17.
 */
public class FileReader {

    public Dataset LoadDataset(String[] filesName) throws IOException {
        Dataset data1 = FileHandler.loadDataset(new File(filesName[0]), 64, ",");
        Dataset data2 = FileHandler.loadDataset(new File(filesName[1]), 64, ",");
        Dataset data = new DefaultDataset();
        data.addAll(data1.subList(0,data1.size()-1));
        data.addAll(data2.subList(0,data1.size()-1));
        return data;
    }

    public be.abeel.util.Pair<Dataset, Dataset> getMixData(Dataset data,double ratio){
        Sampling s=Sampling.SubSampling;
        be.abeel.util.Pair<Dataset, Dataset> datas=s.sample(data, (int)(data.size()*ratio),5);
        return datas;
    }






}
