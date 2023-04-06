package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ImpastoDao;
import dao.IngredienteDao;
import dao.PizzaDao;
import dao.UtenteDao;
import model.Pizza;
import model.Utente;

/**
 * Servlet implementation class utenteSrv
 */
@WebServlet("/UtenteSrv")
public class UtenteSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UtenteSrv() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

			UtenteDao uD = new UtenteDao();
			ImpastoDao impastoDao = new ImpastoDao();
			IngredienteDao ingredienteDao = new IngredienteDao();
			PizzaDao pizzaDao = new PizzaDao();
			String user = request.getParameter("user");
			String pswd = request.getParameter("pswd");
			try {
				Utente utente = uD.findUser(user, pswd);

				if (utente != null) {
					Map<Integer, String> tipiImpasti = impastoDao.getImpasti();
					Map<Integer, String> ingredienti = ingredienteDao.getIngredienti();
					List<Pizza> pizze;
					pizze = utente.getPizze();

					request.getSession().setAttribute("utente", utente);
					request.getSession().setAttribute("listaImpasti", tipiImpasti);
					request.getSession().setAttribute("listaIngredienti", ingredienti);
					request.getSession().setAttribute("listaPizze", pizze);
					request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
				} else {
					request.setAttribute("error", "Utente non trovato");
					request.getRequestDispatcher("/index.jsp").forward(request, response);

				}

			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Errore in fase di autenticazione");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}

		

	}

}
