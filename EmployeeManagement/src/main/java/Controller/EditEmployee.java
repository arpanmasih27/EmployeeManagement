package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import Enteties.Address;
import Enteties.Department;
import Enteties.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class EditEmployee extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		try {
			
			int id=Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("ename");
			String dept = req.getParameter("dept");
			String email = req.getParameter("email");
			int sal = Integer.parseInt(req.getParameter("sal"));
			int hNo = Integer.parseInt(req.getParameter("hNo"));
			String street = req.getParameter("street");
			String city = req.getParameter("city");

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("arpan");
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			
			Employee e1 = em.find(Employee.class, id);
			if (e1!=null) {
				Address a1 = e1.getAddress();
				Department d1 = e1.getDept();
				e1.setName(name);	
				d1.setDname(name);
				e1.setEmail(email);
				e1.setSal(sal);
				a1.setHNo(hNo);
				a1.setStreet(street);
				a1.setCity(city);
				
				et.begin();
				em.merge(e1);
				em.merge(d1);
				em.merge(a1);
				RequestDispatcher rd = req.getRequestDispatcher("Welcome.html");
				rd.include(req, resp);
				
				et.commit();
			}
			
			
	}catch (Exception e) {
		// TODO: handle exception
	}
	}
}
