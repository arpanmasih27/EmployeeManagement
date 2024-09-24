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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		try {
			String name = req.getParameter("ename");
			String dept = req.getParameter("dept");
			int deptid = Integer.parseInt(req.getParameter("deptid"));
			String email = req.getParameter("email");
			int sal = Integer.parseInt(req.getParameter("sal"));
			int hno = Integer.parseInt(req.getParameter("houseNo"));
			String street = req.getParameter("street");
			String city = req.getParameter("city");

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("arpan");
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();

			Employee e1 = new Employee();
			Department d1 = new Department();
			Address a1 = new Address();
			e1.setName(name);
			d1.setDid(deptid);
			d1.setDname(dept);
			e1.setEmail(email);
			e1.setSal(sal);
			a1.setHNo(hno);
			a1.setStreet(street);
			a1.setCity(city);
			e1.setDept(d1);
			e1.setAddress(a1);
			

			et.begin();
			em.persist(e1);
			RequestDispatcher rd = req.getRequestDispatcher("Welcome.html");
			rd.include(req, resp);
			et.commit();
				
		} catch (Exception e) {
			out.println("not success");
		}
	}
}
