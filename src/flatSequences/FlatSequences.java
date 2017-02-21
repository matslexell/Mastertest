package flatsequences;

public class Flatsequences {
		double[] seq = null;
		
		public Flatsequences(double[] seq){
			this.seq = seq;
		}
		
		public int fl(int k){
			if(k<2){
				return 1;
			}
						
			int fl = 1 + fl(k-1);
			
			return Math.min(fl, flatS(k));
			
		}
		
		public int flatS(int k){
			int s = k-1;
			for (; s >= 1; s--) {
				if(!eq(k,s)){
					break;
				}
			}
			return k-s;
		}
		
		public boolean eq(int k, int s){


			double eq = (seq[k-1] - seq[s-1]) / (k-s);
			double round = Math.round(eq * 1000000) * 1.0 / 1000000;
			
			return 0 < round && round < 1;
		}
		
		public void findLargestFl(){
			int idx = seq.length;
			int length = fl(idx);
			
			int max = length;
			int maxIdx = idx;
			
			idx = idx - length;
			while(idx>0){
				length = fl(idx);
				if(length>max){
					maxIdx = idx;
					max = length;
				}
				idx = idx - length;
			}
			
			
			System.out.println("Max:    " + max);
			System.out.println("MaxIdx: " + maxIdx);

		}

		public static void main(String[] args) {
			double[] seq = {1.9,2.8,3.7,4.6,5.6,6.5,7.4,8.3,9.2,10.2,11.1};
			new Flatsequences(seq).findLargestFl();
			
		}
}
