package DataStructuresAndAlgorithm;
/**
 *  二叉树的节点类
 * @author liuwei
 *
 */
public class BinaryNode <Integer> {
	Integer elemnet;
	BinaryNode left;
	BinaryNode right;
	public BinaryNode(Integer x,BinaryNode left,BinaryNode right){
		this.elemnet = x;
		this.left = left;
		this.right = right;
	}
	public BinaryNode<Integer> insert(Integer x,BinaryNode<Integer> t){
		if(t == null){
			return new BinaryNode<Integer>(x,null,null);
		}
		//暂定使用Int
		int ele = (int) t.elemnet;
		int compareResult = ((int)x - ele);
		if(compareResult <0){
			t.left= insert(x,t.left);
		}else
			t.right = insert(x,t.right);
		return t;
	}
	//输出二叉树
	public void printTree(BinaryNode<Integer> t){
		if(t == null){
//			System.out.println("No Data");
		}
		if(t != null){
			printTree(t.left);
			System.out.print(t.elemnet + " ");
			printTree(t.right);
		}
	}
	//后序输出二叉树
		public void printTree2(BinaryNode<Integer> t){
			if(t == null){
//				System.out.print("No Data");
			}
			if(t != null){
				printTree(t.left);
				printTree(t.right);
				System.out.print(t.elemnet + " ");
			}
		}
	//输出树的深度,递归，树的深度等于左树和右树的最大深度+1
	public int height(BinaryNode<Integer> t){
		if(t == null)
			return -1;
		else 
			return 1+Math.max(height(t.left), height(t.right));
	}
//	//删除数据
//	public BinaryNode<Integer> remove(Integer x ,BinaryNode<Integer> t){
//		
//		return null;
//	}
	 //查找最小值
	public BinaryNode<Integer> findMin(BinaryNode<Integer> t){
		if(t == null) 
			return null;
		else if(t.left == null){
			return t;
		}
		else{
			return findMin(t.left);
		}
	}
	 //查找最大值
	public BinaryNode<Integer> findMax(BinaryNode<Integer> t){
		if(t != null){
			while(t.right != null)
				t= t.right;
		}
		return t;
	}
	//是否包含算法
	public boolean contains( Integer x ,BinaryNode<Integer> t){
		if(t ==  null)
			return false;
		//暂定使用Int
		int ele = (int) t.elemnet;
		int compareResult = ((int)x - ele);
		if(compareResult <0){
			return contains(x,t.left);
		}else if(compareResult >0)
			return contains(x,t.right);
		else 
			return true;
	}
	//删除数据
	public  BinaryNode<Integer> remove(Integer x , BinaryNode<Integer> t){
		if(t == null){
			return t;//Item not found ;do noting
		}
		//暂定使用Int
		int ele = (int) t.elemnet;
		int compareResult = ((int)x - ele);
		if(compareResult <0){
				t.left = remove(x,t.left);
		}else if(compareResult >0)
				t.right = remove(x,t.right);
		else if(t.left != null && t.right !=null){
			t.elemnet = findMin(t.right).elemnet;
			t.right = remove(t.elemnet,t.right);
		}
		else
			t = (t.left != null) ?t.left:t.right;
		return t;
	}
}
