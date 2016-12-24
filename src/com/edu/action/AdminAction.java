package com.edu.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.dao.IAdmin;
import com.edu.dao.IStudent;
import com.edu.dao.ITeacher;
import com.edu.dao.impl.AdminImpl;
import com.edu.dao.impl.StudentImpl;
import com.edu.dao.impl.TeacherImpl;
import com.edu.dto.Admin;



/**
 * Servlet implementation class AdminAction
 */
@WebServlet("/AdminAction")
public class AdminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IAdmin admin = new AdminImpl();
	IStudent student = new StudentImpl();
	ITeacher teacher = new TeacherImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String operate = request.getParameter("operate");
		if (operate.equals("login")) {
			login(request, response);
		} else if (operate.equals("add")) {
			add(request, response);
		} else if (operate.equals("list")) {
			list(request, response);
	} else if (operate.equals("delete")) {
			delete(request, response);
		} else if (operate.equals("create")) {
			create(request, response);
		} else if (operate.equals("modify")) {
			modify(request, response);
		} else if (operate.equals("update")) {
			update(request, response);
		} else if (operate.equals("loginOut")) {
			loginOut(request, response);
		} else if (operate.equals("recycleBin")) {
			recycleBin(request, response);
		} else if (operate.equals("clearRecycleBin")) {
			clearRecycleBin(request, response);
		} else if (operate.equals("move")) {
			move(request, response);
		} else if (operate.equals("recover")) {
			recover(request, response);
		}

	}
	/*
	 * ����Աע��
	 */
	/* �÷�����Ҫ�����ǻ��������ɵ���ɫ */

	/*
	 * private void yzm(HttpServletRequest request, HttpServletResponse
	 * response) { // TODO Auto-generated method stub //�������� int width=112; int
	 * height=37; //�����ڴ�ͼ�� BufferedImage image=new BufferedImage(width, height,
	 * BufferedImage.TYPE_INT_RGB); //�������� Graphics g=image.getGraphics();
	 * //ָ���߿����ɫ g.setColor(Color.RED); //��ͼ��ı߿� g.drawRect(0, 0, width,
	 * height);
	 * 
	 * //�����εı���ɫ //�趨������ɫ g.setColor(Color.YELLOW); //�����εı��� g.fillRect(1, 1,
	 * width-2, height-2);
	 * 
	 * //�������� g.setFont(new Font("����", Font.BOLD+Font.ITALIC, 24));
	 * 
	 * //������� //�������4�����������ҳ��
	 * 
	 * Random r=new Random();
	 * 
	 * 
	 * //�趨���ʵ���ɫ g.setColor(Color.RED);
	 * 
	 * //�������4������ String s="����ְҵ����ѧԺ"; //�Ƚ�����ת��Ϊunicode����
	 * s="\u6ec1\u5dde\u804c\u4e1a\u6280\u672f\u5b66\u9662"; for (int i = 0; i
	 * <4; i++) { char c=s.charAt(r.nextInt(s.length())); int
	 * flag=r.nextBoolean()?1:-1; g.drawString(c+"", 18+18*i+flag*r.nextInt(3),
	 * 24+flag*r.nextInt(5)); }
	 * 
	 * for (int i = 0; i <=4; i++) { int n=r.nextInt(10); g.drawString(n+"",
	 * 23*i, 22);
	 * 
	 * } //��ͼƬ������ͻ��� try { //���߿ͻ��˲�Ҫ����ͼ�� response.setHeader("Expires", -1+"");
	 * response.setHeader("Cache-control", "no-cache");
	 * response.setHeader("pragma", "no-cache");
	 * 
	 * 
	 * ImageIO.write(image, "jpg", response.getOutputStream()); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * }
	 * 
	 * 
	 * }
	 */
	private void loginOut(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		try {
			if (session != null) {
				session.invalidate();
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void move(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			if (admin.delete(id)) {
				list(request, response);
			} else {
				request.setAttribute("msg", "ɾ������Աʧ��");
				list(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void recover(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Admin ad=admin.select(id);
			if (ad != null) {
				ad.setState(1);
				if (admin.updateStatus(ad)) {
					request.setAttribute("msg", "��ԭ�ɹ���");
					recycleBin(request, response);
				} else {
					request.setAttribute("msg", "��ԭʧ��");
					recycleBin(request, response);
				}

			} else {
				request.setAttribute("msg", "�Ҳ���");
				list(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
		
		
	}

	private void clearRecycleBin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			if (admin.deleteState()) {
				request.setAttribute("msg", "�Ѿ�����ɾ����");
				recycleBin(request, response);
			} else {
				request.setAttribute("msg", "����ɾ��ʧ�ܣ�");
				recycleBin(request, response);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void recycleBin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			List<Admin> aList=admin.recycleBinList();
			request.setAttribute("aList", aList);
			request.getRequestDispatcher("AdminInfo/recycleBin.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * �޸Ĺ���Ա
	 */
	private void modify(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String adminname = request.getParameter("adminName");
		String password = request.getParameter("password");
		StringBuffer sbf = null;
		try {
			sbf = new StringBuffer();
			int id = Integer.parseInt(request.getParameter("id"));
			Admin ad = admin.select(id);
			if (ad != null) {
				if (adminname == null || adminname.equals("")) {
					sbf.append("�û�������Ϊ��");
				} else {
					ad.setName(adminname);
				}
				if (password == null || password.equals("")) {
					sbf.append("���벻��Ϊ��");
				} else {
					ad.setPws(password);
				}
				if (sbf.length() != 0) {
					request.setAttribute("msg", sbf.toString());
					request.getRequestDispatcher("AdminInfo/modify.jsp").forward(request, response);
				} else {
					if (admin.modify(ad)) {
						list(request, response);
					} else {
						request.setAttribute("msg", "�޸Ĺ���Աʧ��");
						request.getRequestDispatcher("AdminInfo/modify.jsp").forward(request, response);
					}
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * �޸Ĺ���Ա(�Ȳ��ң��ٵ���modify��ҳ��)
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			Admin ad = admin.select(id);

			request.setAttribute("adminInfo", ad);
			request.getRequestDispatcher("AdminInfo/modify.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//ɾ������Ա
	 

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			Admin ad=admin.select(id);
			if(ad!=null){
				ad.setState(0);
				if (admin.updateStatus(ad)) {
					list(request, response);
				} else {
					request.setAttribute("msg", "ɾ������Աʧ��");
					list(request, response);
				}
			}else {
				request.setAttribute("msg", "�Ҳ���");
				list(request, response);
			}
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * ��ʾ����Ա�б�
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Admin> aList = null;
		try {
			aList = new ArrayList<Admin>();
			aList = admin.list();
			request.setAttribute("aList", aList);
			request.getRequestDispatcher("AdminInfo/list.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * ���ӹ���Ա������add.jsp��
	 */
	private void create(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/AdminInfo/add.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * ���ӹ���Ա
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-gen erated method stub
		String username = request.getParameter("username");
		String psw = request.getParameter("psw");
		String name = request.getParameter("name");
		StringBuffer sbf = new StringBuffer();

		Admin ad = new Admin();
		try {
			if (username == null || username.equals("")) {
				sbf.append("�û�������Ϊ��");
			} else {
				ad.setUsername(username);
			}
			if (psw == null || psw.equals("")) {
				sbf.append("���벻��Ϊ��");
			} else {
				ad.setPws(psw);
			}
			if (name == null || name.equals("")) {
				sbf.append("��������Ϊ��");
			} else {
				ad.setName(name);
			}
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("AdminInfo/add.jsp").forward(request, response);
			} else {
				if (admin.add(ad)) {
					list(request, response);
				} else {
					request.setAttribute("msg", "���ʧ�ܣ�");
					request.getRequestDispatcher("AdminInfo/add.jsp").forward(request, response);

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * ����Ա��¼
	 */

	private void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		StringBuffer sbf = new StringBuffer();
		String sf = request.getParameter("s");
		String username = request.getParameter("username");
		String psw = request.getParameter("psw");
		Admin an = null;

		try {
			an = new Admin();
			if (sf.equals("")) {
				sbf.append("select me");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				if (sf.equals("admin")) {
					if (username == null || username.equals("")) {
						sbf.append("�û�������Ϊ�գ�<br/>");
					} else {
						an.setUsername(username);
					}
					if (psw == null || psw.equals("")) {
						sbf.append("���벻��Ϊ�գ�<br/>");
					} else {
						an.setPws(psw);
					}
					if (sbf.length() != 0) {
						request.setAttribute("msg", sbf.toString());
						request.getRequestDispatcher("login.jsp").forward(request, response);
					} else {
						an = admin.login(username, psw);
						HttpSession session = request.getSession();
						session.setAttribute("AdminName", username);
						if (an != null) {
							request.getRequestDispatcher("Admin/nav.jsp").forward(request, response);

						} else {
							request.setAttribute("msg", "�û������������,�����û������� ");
							request.getRequestDispatcher("login.jsp").forward(request, response);
						}
					}

					// Cookie cookie = new Cookie("username", username);
					// cookie.setMaxAge(60 * 60 * 24);
					// response.addCookie(cookie);
				}
				if (sf.equals("teacher")) {
					if (username == null || username.equals("")) {
						sbf.append("�̹��Ų���Ϊ�գ�<br/>");
					} else {
						an.setUsername(username);
					}
					if (psw == null || psw.equals("")) {
						sbf.append("���벻��Ϊ�գ�<br/>");
					} else {
						an.setPws(psw);
					}
					if (sbf.length() != 0) {
						request.setAttribute("msg", sbf.toString());
						request.getRequestDispatcher("login.jsp").forward(request, response);
					} else {
						teacher.loginTeacher(username, psw);
						// Cookie cookie = new Cookie("username", username);
						// cookie.setMaxAge(60 * 60 * 24);
						// response.addCookie(cookie);
						HttpSession session = request.getSession();
						session.setAttribute("AdminName", username);
						if (teacher != null) {
							request.getRequestDispatcher("TeacherInfo/teaNav.jsp").forward(request, response);
						} else {
							request.setAttribute("msg", "�û������������ ");
							request.getRequestDispatcher("login.jsp").forward(request, response);

						}
					}

				}
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
