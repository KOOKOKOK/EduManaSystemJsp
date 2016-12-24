/**
 * 
 */
package com.edu.dao;

import java.util.List;

import com.edu.dto.Student;

/**
 * @author Administrator
 *
 */
public interface IStudent {

	/*
	 * ��¼���ܣ���Ҫ����ѧ�ź�����
	 */

	public Student login(String stuNum, String password) throws Exception;

	/*
	 * ����Ա���ѧ����Ϣ��boolean���ж��Ƿ����
	 */
	public boolean addStudent(Student s) throws Exception;

	/*
	 * ����Աɾ��ѧ����Ϣ��boolean���ж��Ƿ�ɾ��
	 */
	public boolean deleteStudent(String num) throws Exception;

	/*
	 * ����Ա �޸�ѧ����Ϣ��boolean���ж��Ƿ��޸�ѧ����Ϣ
	 */
	public boolean modifyStudent(Student s) throws Exception;

	/*
	 * ��ʾѧ���б� <list����>
	 * 
	 */

	public List<Student> lispStudent(int pageSize, int pageNo) throws Exception;

	/*
	 * ����ѧ�� Student
	 */
	public int getCount() throws Exception;

	public Student findStudent(String num) throws Exception;

	/*
	 * ���ݹؼ��ֲ�ѯ ģ����ѯ
	 */
	public List<Student> findStudent(int key, String keyname) throws Exception;

	public Student getPsw(String password, String num) throws Exception;

	public boolean updateStatus(Student s) throws Exception;

	public List<Student> recycleBinList() throws Exception;

	public boolean deleteStatus() throws Exception;

	/*public List<Student> getPageList(int currentPageIndex, int count) throws Exception;

	*//**
	 * ��ȡ���ж���ҳ
	 * 
	 * @param count
	 *            �������ҳ����ʾ�ļ�¼��
	 * @return ����ҳ�������
	 *//*
	public int getPageCount(int count)throws Exception;*/

}
