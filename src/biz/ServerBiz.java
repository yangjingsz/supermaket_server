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
	 * 初始化书的信息
	 */
	public static void makeData() {
		commodityMap = new HashMap<String, Commodity>();
		Commodity commodity1 = new Commodity("1001", "芝麻油", 9.50, "125ml", 20);
		Commodity commodity2 = new Commodity("1002", "雀巢咖啡", 1.50, "13g", 200);
		Commodity commodity3 = new Commodity("1003", "脉动柠檬", 7.00, "1.5l", 50);
		Commodity commodity4 = new Commodity("1004", "旺仔QQ糖", 2.50, "70g", 70);
		Commodity commodity5 = new Commodity("1005", "熟切片", 6.50, "400g", 10);
		Commodity commodity6 = new Commodity("1006", "黄豆芽", 2.40, "350g", 20);
		Commodity commodity7 = new Commodity("1007", "白玉豆腐", 1.00, "400g", 15);

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
	 * 入库
	 * 
	 * @param commodity
	 *            商品
	 */
	public boolean in(Commodity commodity) {
		if (commodityMap.containsKey(commodity.getComId())) {// 判断是否有此商品
			Commodity oldCommodity = commodityMap.get(commodity.getComId());
			oldCommodity.setNum(oldCommodity.getNum() + commodity.getNum());// 更改数量
			commodityMap.put(commodity.getComId(), oldCommodity);
			return true;
		} else {
			commodityMap.put(commodity.getComId(), commodity);// 新增商品
			return true;
		}
	}

	/**
	 * 验证用户登录
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public String userValidate(String userName, String passWord) {
		String role = null;
		if (serverUserMap.containsKey(userName)) {// 判断是否有此用户名
			ServerUser serverUser = serverUserMap.get(userName);
			if (serverUser.getPassWord().equals(passWord))// 判断密码
				role = serverUser.getRole();
		}
		return role;
	}

	/**
	 * 出库
	 * 
	 * @param comId
	 *            商品编号
	 * @param num
	 *            数量
	 */
	public boolean out(String comId, int num) {
		Commodity commodity = commodityMap.get(comId);
		int amount = commodity.getNum() + num;
		if (amount >= 0) {// 判断库存量
			commodity.setNum(amount);// 修改库存数量
			return true;
		} else
			// System.out.println("库存不足，当前库存是：+"+commodity.getNum()+"，请核实！");
			return false;
	}

	/**
	 * 查询库存
	 * 
	 * @return 商品列表
	 */
	public List<Commodity> query() {
		return new ArrayList<Commodity>(commodityMap.values());
	}

	/**
	 * 按编号查询商品
	 * 
	 * @param comId
	 *            商品编号
	 * @return 商品
	 */
	public Commodity queryById(String comId) {
		return commodityMap.get(comId);
	}
}
