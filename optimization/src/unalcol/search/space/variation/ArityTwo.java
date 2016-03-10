package unalcol.search.space.variation;

import unalcol.clone.Clone;
import unalcol.search.solution.Solution;
import unalcol.search.variation.ArityTwoSearchOperator;

public interface ArityTwo<T> extends ArityTwoSearchOperator<T>, Operator<T> {
    @SuppressWarnings("unchecked")
	public default Solution<T>[] apply( Solution<T> one, Solution<T> two ){
    	T[] next = apply( one.object(), two.object() );
    	Solution<T>[] s = (Solution<T>[])new Object[]{new Solution<T>(next[0]),new Solution<T>(next[1])};
    	s[0].cloneTaggedMethods(one);
    	s[1].cloneTaggedMethods(two);
    	return s;
    }   

    /**
     * Apply the genetic operator to the first and second individuals in the population of parents
     * This method is parent compatible
     * @param parents Collection of parents used by the genetic operator (selects just the first and second
     * individuals in the collection
     * @return A collection of individuals generated by the genetic operator
     */
    @SuppressWarnings("unchecked")
    @Override
    public default T[] apply( T... parents ){
  	  T[] v = (T[])(new Object[parents.length]);
  	  int n = (v.length>>1)<<1;
        for( int i=0; i<n;i+=2){
      	  T[] p = apply( parents[i], parents[i+1] );
      	  v[i] = p[0]; 
      	  v[i+1] = p[1];
        }
        if( n < v.length ) v[n] = (T)Clone.create(parents[n]);
        return v;
    }    
}
