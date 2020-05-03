package week1;

//public class Start {
//	public void start() {
//		int z = 4;
//		System.out.println(func1(z, 1) - 3);
//	}
//
//	public int func2(int w) {
//		return w * 3;
//	}
//
//	public int func1(int a, int b) {
//	  int n = a + b;
//	  return 2 + func2(n);
//	}
//}

public class Start {
	public void start() {
		int a = 2; 
		int b = 0 ;
		int r = k(a, b);
		System.out.print(r);
	}
//		public int g(int a) {
//			  if (a < 9) {
//			    return 9;
//			  }
//			  
//			  if (a < 7) {
//			    return 7;
//			  }
//			  
//			  if (a < 4) {
//			    return 4;
//			  }
//			  
//			  return 0;
//			}
		
		public int k (int a, int b) {
			  if (a < b) {
			    if (b > 4) {
			      return 0 ;
			    }
			    else {
			      return 1;
			    }
			  }
			  else {
			    if (a > 4) {
			      return 2;
			    }
			    else {
			      return 3;
			    }
			  }
			}
}