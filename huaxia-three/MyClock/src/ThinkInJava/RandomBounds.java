package ThinkInJava;
//ceshi Math.Random[0,1)的边界
public class RandomBounds {
static void usage(){
	System.out.print("Usage:");
	System.out.print("\tRandomBounds lower");
	System.out.print("\tRandomBounds upper");
	System.exit(1);
}
public static void main(String args[]){
//	if(args.length != 1) usage();
//	if(args[0].equals("lower")){
		while(Math.random() !=0)
			System.out.println(Math.random());
			;
		System.out.println("Produce 0.0!");
//	}
//	if(args[0].equals("upper")){
		while(Math.random() !=1)
			;
		System.out.println("Produce 1.0!");
//	}
//	else 
//		usage();
}
}
