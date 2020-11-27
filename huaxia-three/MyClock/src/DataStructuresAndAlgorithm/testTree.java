package DataStructuresAndAlgorithm;
/**
 * 测试输出二叉树
 * 正确的输出
 * @author liuwei
 */
public class testTree {
	public static void main(String args[]){
		BinaryNode b3 = new BinaryNode(3,null,null);
		b3.insert(1, b3);
		b3.insert(2, b3);
		b3.insert(4, b3);
		b3.insert(5, b3);
		b3.insert(3, b3);
		b3.printTree(b3);
		System.out.println("树的深度"+b3.height(b3));
		System.out.println("树的最小值"+b3.findMin(b3).elemnet);
		System.out.println("树的最大值"+b3.findMax(b3).elemnet);
		System.out.println("树是否包含1"+b3.contains(1,b3));
		System.out.println("树是否包含6"+b3.contains(6,b3));
		System.out.println("删除一个元素3"+b3.remove(3,b3).elemnet);
		b3.printTree(b3);
		System.out.println("后序输出树");
		b3.printTree2(b3);
	}
}
