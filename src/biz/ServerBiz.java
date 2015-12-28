package biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Commodity;
import entity.ServerUser;

public class ServerBiz {
	public static Map<String, Commodity> commodityMap;
	public static Map<String, ServerUser> serverUserMap;

	public ServerBiz() {
		// TODO Auto-generated constructor stub
		makeData();
	}

	/**
	 * ��ʼ�������Ϣ
	 */
	public static void makeData() {
		commodityMap = new HashMap<String, Commodity>();
		Commodity commodity1 = new Commodity("1001", "֥����", 9.50, "125ml", 20);
		Commodity commodity2 = new Commodity("1002", "ȸ������", 1.50, "13g", 200);
		Commodity commodity3 = new Commodity("1003", "��������", 7.00, "1.5l", 50);
		Commodity commodity4 = new Commodity("1004", "����QQ��", 2.50, "70g", 70);
		Commodity commodity5 = new Commodity("1005", "����Ƭ", 6.50, "400g", 10);
		Commodity commodity6 = new Commodity("1006", "�ƶ�ѿ", 2.40, "350g", 20);
		Commodity commodity7 = new Commodity("1007", "���񶹸�", 1.00, "400g", 15);

		commodityMap.put(commodity1.getComId(), commodity1);
		commodityMap.put(commodity2.getComId(), commodity2);
		commodityMap.put(commodity3.getComId(), commodity3);
		commodityMap.put(commodity4.getComId(), commodity4);
		commodityMap.put(commodity5.getComId(), commodity5);
		commodityMap.put(commodity6.getComId(), commodity6);
		commodityMap.put(commodity7.getComId(), commodity7);

		serverUserMap = new HashMap<String, ServerUser>();
		serverUserMap.put("1101", new ServerUser("1101", "1111", "storeMgr"));
		serverUserMap.put("1001", new ServerUser("1001", "0000", "cashier"));
		serverUserMap.put("1002", new ServerUser("1002", "2222", "cashier"));
	}

	/**
	 * ���
	 * 
	 * @param commodity
	 *            ��Ʒ
	 */
	public boolean in(Commodity commodity) {
		if (commodityMap.containsKey(commodity.getComId())) {// �ж��Ƿ��д���Ʒ
			Commodity oldCommodity = commodityMap.get(commodity.getComId());
			oldCommodity.setNum(oldCommodity.getNum() + commodity.getNum());// ��������
			commodityMap.put(commodity.getComId(), oldCommodity);
			return true;
		} else {
			commodityMap.put(commodity.getComId(), commodity);// ������Ʒ
			return true;
		}
	}

	/**
	 * ��֤�û���¼
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public String userValidate(String userName, String passWord) {
		String role = null;
		if (serverUserMap.containsKey(userName)) {// �ж��Ƿ��д��û���
			ServerUser serverUser = serverUserMap.get(userName);
			if (serverUser.getPassWord().equals(passWord))// �ж�����
				role = serverUser.getRole();
		}
		return role;
	}

	/**
	 * ����
	 * 
	 * @param comId
	 *            ��Ʒ���
	 * @param num
	 *            ����
	 */
	public boolean out(String comId, int num) {
		Commodity commodity = commodityMap.get(comId);
		int amount = commodity.getNum() + num;
		if (amount >= 0) {// �жϿ����
			commodity.setNum(amount);// �޸Ŀ������
			return true;
		} else
			// System.out.println("��治�㣬��ǰ����ǣ�+"+commodity.getNum()+"�����ʵ��");
			return false;
	}

	/**
	 * ��ѯ���
	 * 
	 * @return ��Ʒ�б�
	 */
	public List<Commodity> query() {
		return new ArrayList<Commodity>(commodityMap.values());
	}

	/**
	 * ����Ų�ѯ��Ʒ
	 * 
	 * @param comId
	 *            ��Ʒ���
	 * @return ��Ʒ
	 */
	public Commodity queryById(String comId) {
		return commodityMap.get(comId);
	}
}
