/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.unsupervised.partition;

import unalcol.learn.supervised.classification.fuzzy.FuzzyClassifier;
import unalcol.math.metric.QuasiMetric;

/**
 *
 * @author jgomez
 */
public class CentroidsRecognizer<T> implements FuzzyClassifier<T> {
    T[] mu;
    QuasiMetric<T> metric;
    
    public CentroidsRecognizer( T[] mu, QuasiMetric<T> metric ){
        this.mu = mu;        
        this.metric = metric;
    }

    @Override
    public int classesNumber() {
        return mu.length;
    }

    @Override
    public double[] apply(T data) {
        double[] conf = new double[mu.length];
        for( int i=0; i<conf.length; i++ ){
            if( mu[i] != null )
                conf[i] = metric.apply(data, mu[i]);
        }
        return conf;
    }   
    
    public QuasiMetric<T> metric(){ return metric; }
    
    public void setMu( int i, T mu ){
        this.mu[i] = mu;
    }

    public T mu( int i ){
        return mu[i];
    }
}