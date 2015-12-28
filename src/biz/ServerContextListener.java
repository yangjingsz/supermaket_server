package biz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import entity.Commodity;

import util.Datas;
import util.SysConstants;
import util.Tools;



/**
 * 服务器的线程类。 接收客户端请求
 * 
 * @author 杨静
 * 
 */
public class ServerContextListener extends Thread {

	public static void main(String[] args) {
		new ServerContextListener().start();
	}

	public void run() {
		System.out.println("服务端启动");
		// 创建服务器的服务类
		ServerBiz serverbiz = new ServerBiz();
		// 创建服务器Socket相关实例
		ServerSocket serverSocket = null;
		ObjectOutputStream objectOutputStream = null;
		ObjectInputStream objectInputStream = null;
		Socket socket = null;
		try {
			// 创建Socket对象
			serverSocket = new ServerSocket(Integer.parseInt(Tools
					.getValue(SysConstants.SYS_LISTENERPORT)));
			while (true) {
				// 获取客户端Socket对象
				socket = serverSocket.accept();
				objectOutputStream = new ObjectOutputStream(socket
						.getOutputStream());
				objectInputStream = new ObjectInputStream(socket
						.getInputStream());
				
				// 获取客户端请求的对象
				Datas datas = (Datas) objectInputStream.readObject();
				// 判断客户端请求的操作
				if (SysConstants.SYS_LOGIN.equals(datas.getFlag())) {
					// 登录
					String role=serverbiz.userValidate(datas.getUserName(), datas.getPassWord());
					datas.setRole(role);						
				} else {
					if (SysConstants.SYS_QUERY.equals(datas.getFlag())) {// 查询库存商品
						List<Commodity> comList=serverbiz.query();
						datas.setComList(comList);
					} else if (SysConstants.SYS_QUERYBYID.equals(datas.getFlag())) {// 按编号查询商品
						String comId = datas.getComId();
						Commodity commodity=serverbiz.queryById(comId);	
						datas.setCommodity(commodity);
					} else if (SysConstants.SYS_INSERT.equals(datas.getFlag())) {//入库
						Commodity commodity=datas.getCommodity();
						boolean result=serverbiz.in(commodity);
						if(result)//如果插入成功，设置成功标识
							datas.setFlag(SysConstants.SYS_SUCCESS);
						else //否则设置失败标识
							datas.setFlag(SysConstants.SYS_ERROR);
					}else if(SysConstants.SYS_QUERYUPDATEBYID.equals(datas.getFlag())){//出库
						Commodity commodity = datas.getCommodity();//客户端传来的
						//System.out.println("买："+commodity.getNum()+"库存："+commodity.getNum());
						
						boolean result=serverbiz.out(commodity.getComId(),commodity.getNum());
						
						if(result)//如果出库成功，设置成功标识
							datas.setFlag(SysConstants.SYS_SUCCESS);
						else //否则设置失败标识
							datas.setFlag(SysConstants.SYS_ERROR);
					}
					
				}
				objectOutputStream.writeObject(datas);
				objectOutputStream.flush();
				socket.shutdownOutput();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭
				objectInputStream.close();
				objectOutputStream.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("服务器终止");
	}

}
