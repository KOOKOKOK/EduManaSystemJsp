package com.edu.action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.dao.IStudent;
import com.edu.dao.impl.StudentImpl;
import com.edu.dto.Student;

/**
 * Servlet implementation class StudentAction
 */
@WebServlet("/StudentAction")
public class StudentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IStudent student = new StudentImpl();
	public static final int PAGESIZE = 5;// �������

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentAction() {
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

		if (operate.equals("stuLogin")) {
			stuLogin(request, response);
		} else if (operate.equals("listone")) {
			listone(request, response);
		} else if (operate.equals("addStu")) {
			addStu(request, response);
		} else if (operate.equals("loginOut")) {
			loginOut(request, response);
		} else if (operate.equals("changePsw")) {
			changePsw(request, response);
		} else if (operate.equals("deleteStu")) {
			deleteStu(request, response);
		} else if (operate.equals("updateStu")) {
			updateStu(request, response);
		} else if (operate.equals("modifyStu")) {
			modifyStu(request, response);
		} else if (operate.equals("selectCourse")) {
			selectCourse(response, response);
		} else if (operate.equals("writeAssess")) {
			writeAssess(request, response);
		} else if (operate.equals("searchScore")) {
			searchScore(request, response);
		} else if (operate.equals("searchStu")) {
			searchStu(request, response);
		} else if (operate.equals("listStu")) {
			listStu(request, response);
		} else if (operate.equals("recycleBin")) {
			recycleBin(request, response);
		} else if (operate.equals("clearRecycleBin")) {
			clearRecycleBin(request, response);
		} else if (operate.equals("move")) {
			move(request, response);
		} else if (operate.equals("recover")) {
			recover(request, response);
		} else if (operate.equals("yzm")) {
			yzm(request, response);
		}
	}

	public Color getRandColor(int s, int e) {
		Random random = new Random();
		if (s > 255)
			s = 255;
		if (e > 255)
			e = 255;
		int r, g, b;
		r = s + random.nextInt(e - s); // �������RGB��ɫ�е�rֵ
		g = s + random.nextInt(e - s); // �������RGB��ɫ�е�gֵ
		b = s + random.nextInt(e - s); // �������RGB��ɫ�е�bֵ
		return new Color(r, g, b);
	}

	public void yzm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���ò�����ͼƬ
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// ָ�����ɵ���ӦͼƬ,һ������ȱ����仰,�������.
		response.setContentType("image/jpeg");
		int width = 112, height = 37; // ָ��������֤��Ŀ�Ⱥ͸߶�
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // ����BufferedImage����,�������൱��һͼƬ
		Graphics g = image.getGraphics(); // ����Graphics����,�������൱�ڻ���
		Graphics2D g2d = (Graphics2D) g; // ����Grapchics2D����
		Random random = new Random();
		Font mfont = new Font("����", Font.BOLD, 24); // ����������ʽ
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height); // ���Ʊ���
		g.setFont(mfont); // ��������
		g.setColor(getRandColor(180, 200));

		// ����100����ɫ��λ��ȫ��Ϊ�������������,������Ϊ2f
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL); // ����������ʽ
			Line2D line = new Line2D.Double(x, y, x + x1, y + y1);
			g2d.setStroke(bs);
			g2d.draw(line); // ����ֱ��
		}
		// �����Ӣ�ģ����֣������������ɵ���֤���֣��������Ϸ�ʽ�������������ȷ����
		String sRand = "";
		String ctmp = "";
		int itmp = 0;
		// �ƶ��������֤��Ϊ��λ
		for (int i = 0; i < 4; i++) {
			switch (random.nextInt(3)) {
			case 1: // ����A-Z����ĸ
				itmp = random.nextInt(26) + 65;
				ctmp = String.valueOf((char) itmp);
				break;
			case 2: // ���ɺ���
				String[] rBase = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
				// ���ɵ�һλ����
				int r1 = random.nextInt(3) + 11;
				String str_r1 = rBase[r1];
				// ���ɵڶ�λ����
				int r2;
				if (r1 == 13) {
					r2 = random.nextInt(7);
				} else {
					r2 = random.nextInt(16);
				}
				String str_r2 = rBase[r2];
				// ���ɵ�һλλ��
				int r3 = random.nextInt(6) + 10;
				String str_r3 = rBase[r3];
				// ���ɵڶ�λλ��
				int r4;
				if (r3 == 10) {
					r4 = random.nextInt(15) + 1;
				} else if (r3 == 15) {
					r4 = random.nextInt(15);
				} else {
					r4 = random.nextInt(16);
				}
				String str_r4 = rBase[r4];
				// �����ɵĻ�����ת��Ϊ����
				byte[] bytes = new byte[2];
				// �����ɵ����뱣�浽�ֽ�����ĵ�һ��Ԫ����
				String str_12 = str_r1 + str_r2;
				int tempLow = Integer.parseInt(str_12, 16);
				bytes[0] = (byte) tempLow;
				// �����ɵ�λ�뱣�浽�ֽ�����ĵڶ���Ԫ����
				String str_34 = str_r3 + str_r4;
				int tempHigh = Integer.parseInt(str_34, 16);
				bytes[1] = (byte) tempHigh;
				ctmp = new String(bytes);
				break;
			default:
				itmp = random.nextInt(10) + 48;
				ctmp = String.valueOf((char) itmp);
				break;
			}
			sRand += ctmp;
			Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), random.nextInt(110));
			g.setColor(color);
			// �����ɵ����������������Ų���ת�ƶ��Ƕ� PS.���鲻Ҫ�����ֽ�����������ת,��Ϊ����ͼƬ���ܲ�������ʾ
			/* ��������ת�ƶ��Ƕ� */
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			trans.rotate((45) * 3.14 / 180, 15 * i + 8, 7);
			/* �������� */
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1f)
				scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			g.drawString(ctmp, 15 * i + 18, 14);
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("randCheckCode", sRand);
		g.dispose(); // �ͷ�g��ռ�õ�ϵͳ��Դ
		ImageIO.write(image, "JPEG", response.getOutputStream()); // ���ͼƬ
	}

	private void recover(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		try {
			Student s = student.findStudent(num);
			if (s != null) {
				s.setStatus("1");
				if (student.updateStatus(s)) {
					request.setAttribute("msg", "��ԭ�ɹ���");
					recycleBin(request, response);
				} else {
					request.setAttribute("msg", "��ԭʧ��");
					recycleBin(request, response);
				}

			} else {
				request.setAttribute("msg", "�Ҳ���");
				listStu(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void move(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		try {
			Student s = student.findStudent(num);
			if (s != null) {
				if (student.deleteStudent(num)) {
					clearRecycleBin(request, response);
				}
			} else {
				request.setAttribute("msg", "�Ҳ���");
				clearRecycleBin(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void clearRecycleBin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			if (student.deleteStatus()) {
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

	/*
	 * private void recycleBin(HttpServletRequest request, HttpServletResponse
	 * response) { // TODO Auto-generated method stub List<Student> sList=new
	 * ArrayList<Student>(); try { sList=student.recycleBinList();
	 * request.setAttribute("sList", sList);
	 * request.getRequestDispatcher("StudentInfo/recycleBin.jsp").forward(
	 * request, response); } catch (Exception e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); }
	 * 
	 * }
	 */
	private void recycleBin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("StudentInfo/recycleBin.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteStu(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		try {
			Student s = student.findStudent(num);
			if (s != null) {
				s.setStatus("0");
				if (student.updateStatus(s)) {
					request.setAttribute("msg", "ɾ���ɹ���");
					listStu(request, response);
				} else {
					request.setAttribute("msg", "ɾ��ʧ��");
					listStu(request, response);
				}

			} else {
				request.setAttribute("msg", "�Ҳ���");
				listStu(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * private void listStu(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * // ����ҳ
	 * 
	 * try {
	 * 
	 * List<Student> sList = student.lispStudent();
	 * request.setAttribute("sList", sList);
	 * request.getRequestDispatcher("StudentInfo/stuList.jsp").forward(request,
	 * response); } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

	private void listStu(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = 1;
		int pageSize = 6;//��ǰҳ�������
		int count = 0;
		// String pageN=request.getParameter("pageNo");
		// int pn=Integer.parseInt(request.getParameter("pageNo"));
		// int ps=Integer.parseInt(request.getParameter("pageSize"));
		try {
			//
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));

			count = student.getCount();
			int totalPage = (int) Math.ceil((float) count / pageSize);// ȡ��

			if (pageNo < 1)
				pageNo = 1;
			if (pageNo > totalPage)
				pageNo = totalPage;

			List<Student> sList = student.lispStudent(pageSize, pageNo);
			request.setAttribute("sList", sList);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("StudentInfo/sList.jsp").forward(request, response);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchStu(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			String num = request.getParameter("num");
			if (num == null || num.equals("")) {
				request.setAttribute("msg", "ѧ�Ų���Ϊ�գ�");
			} else {
				Student stu = student.findStudent(num);
				if (stu != null) {
					request.setAttribute("student", stu);
					request.getRequestDispatcher("StudentInfo/searchStu.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "�Ҳ���");
					listStu(request, response);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * ѧ���鿴�Լ��ɼ�
	 */
	private void searchScore(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	/*
	 * ѧ����������ʦд����
	 */
	private void writeAssess(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	/*
	 * ѧ��ѡ��
	 */
	private void selectCourse(HttpServletResponse response, HttpServletResponse response2) {
		// TODO Auto-generated method stub

	}

	/*
	 * ����Ա�޸�ѧ��
	 */
	private void modifyStu(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		String xb = request.getParameter("xb");
		String name = request.getParameter("name");
		String Id = request.getParameter("Id");
		try {

			Student stu = student.findStudent(num);
			if (stu != null) {
				if (xb.equals("��Ϣϵ")) {
					stu.setDepartment("��Ϣϵ");
				} else if (xb.equals("��óϵ")) {
					stu.setDepartment("��óϵ");
				} else if (xb.equals("��ýϵ")) {
					stu.setDepartment("��ýϵ");
				} else if (xb.equals("����ϵ")) {
					stu.setDepartment("����ϵ");
				} else if (xb.equals("ʳƷ����ϵ")) {
					stu.setDepartment("ʳƷ����ϵ");
				}
				stu.setID(Id);
				stu.setName(name);
				if (student.modifyStudent(stu)) {
					listStu(request, response);
				} else {
					request.setAttribute("msg", "�޸�ʧ�ܣ�");
					request.getRequestDispatcher("StudentInfo/modifyStu.jsp").forward(request, response);
				}

			} else {
				request.setAttribute("msg", "�Ҳ���ѧ�ţ�");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * ����Ա�޸�ѧ�����Ȳ��ң��������޸�ҳ�棩
	 */
	private void updateStu(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		try {
			Student stu = student.findStudent(num);
			if (stu != null) {
				request.setAttribute("StudentInfo", stu);
				request.getRequestDispatcher("StudentInfo/modifyStu.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "�鲻��");
				listStu(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * ����ѧ��Ĭ��������000��ѧ���޸ĸ�������
	 */
	private void changePsw(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		StringBuffer sbf = new StringBuffer();
		String oldPsw = request.getParameter("oldPsw");

		HttpSession session = request.getSession();
		session.getAttribute("stuNum");
		Student stu = null;
		try {
			String num = request.getParameter("num");
			if (oldPsw == null || oldPsw.equals("")) {
				sbf.append("ԭ���벻��Ϊ��!<br/>");
			}
			stu = student.getPsw(oldPsw, num);
			if (stu != null) {
				String newPsw = request.getParameter("newPsw");
				String againPsw = request.getParameter("againPsw");
				if (newPsw == null || newPsw.equals("")) {
					sbf.append("�����벻��Ϊ�գ�<br/>");
				}
				if (againPsw == null || againPsw.equals("")) {
					sbf.append("�ٴ����������벻��Ϊ�գ�<br/>");
				}
				if (oldPsw.equals(newPsw)) {
					sbf.append("�����벻��ԭ��������ͬ��<br/>");
				}
				if (newPsw.equals(againPsw)) {
					stu.setPassword(newPsw);
				} else {
					sbf.append("�����������벻ͬ��<br/>");
				}
			} else {
				sbf.append("ԭ�����������<br/>");
			}

			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("StudentInfo/changePsw.jsp").forward(request, response);
			} else {
				if (student.modifyStudent(stu)) {
					request.setAttribute("msg", "�޸�����ɹ���");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "�޸�����ʧ��");
					request.getRequestDispatcher("StudentInfo/changePsw.jsp").forward(request, response);

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * ����Ա���ѧ��
	 */
	private void addStu(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			Student stu = new Student();
			StringBuffer sbf = new StringBuffer();
			String xb = request.getParameter("xb");
			String num = request.getParameter("num");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String Id = request.getParameter("Id");
			if (xb.equals("��Ϣϵ")) {
				stu.setDepartment("��Ϣϵ");
			} else if (xb.equals("��óϵ")) {
				stu.setDepartment("��óϵ");
			} else if (xb.equals("��ýϵ")) {
				stu.setDepartment("��ýϵ");
			} else if (xb.equals("����ϵ")) {
				stu.setDepartment("����ϵ");
			} else if (xb.equals("ʳƷ����ϵ")) {
				stu.setDepartment("ʳƷ����ϵ");
			}

			if (num == null || num.equals("")) {
				sbf.append("ѧ�Ų���Ϊ�գ�<br/>");
			} else {
				stu.setNum(num);
			}
			if (name == null || name.equals("")) {
				sbf.append("��������Ϊ�գ�<br/>");
			} else {
				stu.setName(name);
			}

			if (sex.equals("��")) {
				stu.setSex(sex);
			} else {
				stu.setSex("Ů");
			}
			if (Id == null || Id.equals("")) {
				sbf.append("���֤���벻��Ϊ��");
			} else {
				stu.setID(Id);
			}
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("StudentInfo/addStu.jsp").forward(request, response);
			} else {
				if (student.addStudent(stu)) {
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "���ʧ��");
					request.getRequestDispatcher("StudentInfo/addStu.jsp").forward(request, response);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * ѧ����¼������Ϣ
	 */
	private void listone(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String num = request.getParameter("num");
			Student stu = student.findStudent(num);
			request.setAttribute("student", stu);
			request.getRequestDispatcher("StudentInfo/listone.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * ѧ����¼
	 */
	private void stuLogin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		StringBuffer sbf = new StringBuffer();
		String stuNum = request.getParameter("stuNum");
		String password = request.getParameter("password");
		String checkcode = request.getParameter("checkCode");
		if (stuNum == null || stuNum.equals("")) {
			sbf.append("ѧ�Ų���Ϊ�գ�<br/>");
		}
		if (password.equals("") || password == null) {
			sbf.append("���벻��Ϊ�գ�<br/>");
		}
		try {
			Student stu = student.login(stuNum, password);
			if (stu != null) {
				if (sbf.length() != 0) {
					request.setAttribute("msg", sbf.toString());
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					HttpSession session = request.getSession();
					if (checkcode.equals("") || checkcode == null) {
						sbf.append("��������֤�룡");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					} else {
						if (!checkcode.equalsIgnoreCase((String) session.getAttribute("randCheckCode"))) {
							request.setAttribute("msg", "��֤�����");
							request.getRequestDispatcher("index.jsp").forward(request, response);
						} else {
							session.setAttribute("stuNum", stuNum);
							session.setAttribute("password", password);
							request.getRequestDispatcher("StudentInfo/stuNav.jsp").forward(request, response);
						}
					}
				}

			} else {
				sbf.append("�û��������벻�ԣ������û�������!<br/>");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * ע��
	 */
	private void loginOut(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		try {
			if (session != null) {
				session.invalidate();// sessionʧЧ
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
