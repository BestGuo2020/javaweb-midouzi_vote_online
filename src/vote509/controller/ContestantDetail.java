package vote509.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vote509.dao.IContestantDAO;
import vote509.dao.impl.ContestantDAO;
import vote509.entity.Contestant;

@WebServlet("/vote/detail")
public class ContestantDetail extends HttpServlet {

	private static final long serialVersionUID = -6133935613167296830L;
	
	IContestantDAO contestDAO = new ContestantDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if("download".equals(req.getParameter("status"))) {
			int id = Integer.parseInt(req.getParameter("cid")); // ��ȡѡ��id
			
			// ��ȡѡ��id
			Contestant con = contestDAO.getContestantById(id);
			String[] file = con.getImage().split("\\/");
			// ��ȡ�ļ���
			String fileName = file[file.length - 1];
			// ������������
			resp.setContentType("application/x-msdownload");
	        resp.setHeader("content-disposition","attachment;filename=" + fileName);
	        
            File file2 = new File(req.getRealPath(con.getImage()));
            //��ȡ�����ļ�
            FileInputStream fIn = new FileInputStream(file2);
            //�����
            OutputStream out = resp.getOutputStream();
            //������
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fIn.read(buffer,0,buffer.length))!=-1){
                //��������������������������ʵ���ļ�����
                out.write(buffer,0,len);
            }
            out.flush();
            out.close();
            fIn.close();
	            
		} else {
			int id = Integer.parseInt(req.getParameter("cid"));
			String aid = req.getParameter("aid");
			String uid = req.getParameter("uid");
			String vote = req.getParameter("vote");
			// TODO Auto-generated method stub
			Contestant con = contestDAO.getContestantById(id);
			req.setAttribute("contestant", con);
			req.setAttribute("aid", aid);
			req.setAttribute("uid", uid);
			req.setAttribute("vote", vote);
			req.getRequestDispatcher("/WEB-INF/jsp/frontend/voteDetail.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
