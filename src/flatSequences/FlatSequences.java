package flatSequences;

import java.text.DecimalFormat;



public class FlatSequences {
	double[] seq = null;
	
	public FlatSequences(double[] seq){
		this.seq = seq;
	}
	
	public int fl(int k){
		if(k<2){
			return 1;
		}
		
		int fl = fl(k-1);
		
		if(isFlat(k)){
			return fl+1;
		}else{
			return 0;
		}
	}
	
	public boolean isFlat(int k){
		for (int s = k-1; s >= 1; s--) {
			if(!eq(k,s)){
				return false;
			}
		}
		return true;
	}
	
	public boolean eq(int k, int s){


		double eq = (seq[k-1] - seq[s-1]) / (k-s);
		double round = Math.round(eq * 1000000) * 1.0 / 1000000;
		
		return 0 < round && round < 1;
	}

	public static void main(String[] args) {
		double[] seq = {1.9,2.8,3.7,4.6,5.6,6.5,7.4};
		
		System.out.println(new FlatSequences(seq).fl(4));
		
	}

}
