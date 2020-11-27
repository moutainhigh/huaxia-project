/**
 * Title: MarriageAgencyImpl.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������9:53:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @class_name:MarriageAgencyImpl2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������9:53:37
 */
public class MarriageAgencyImpl implements MarriageAgency {
	//�л�Ա
	List<Man> men = new ArrayList<Man>();
	//Ů��Ա
	List<Woman> woman = new ArrayList<Woman>();
	/**
	 * 
	 */
	public MarriageAgencyImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator.MarriageAgency#pair(liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator.Person)
	 */
	@Override
	public void pair(Person person) {
		// TODO Auto-generated method stub
		if(person.sex == Sex.MALE){
			for(Woman w:woman){
				if(w.age == person.requestAge){
					System.out.println(person.name+"��"+w.name+"��Գɹ�");
					return;
				}
			}
		}else if(person.sex == Sex.FEMALE){
			for(Man m:men){
				if(m.age == person.requestAge){
					System.out.println(person.name+"��"+m.name+"��Գɹ�");
					return;
				}
			}
		}
		System.out.println("û��Ϊ"+person.name+"�ҵ����ʵĶ���");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator.MarriageAgency#register(liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator.Person)
	 */
	@Override
	public void register(Person person) {
		// TODO Auto-generated method stub
		if(person.sex == Sex.MALE)
			men.add((Man)person);
		else if(person.sex == Sex.FEMALE)
			woman.add((Woman)person);
	}

}
