package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.PizzaDao;
import model.Pizza;
import model.Utente;

/**
 * Servlet implementation class PizzaSrv
 */
public class PizzaSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PizzaSrv() {
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
		

			String creaPizza = request.getParameter("creaPizza");
			String cancellaPizze = request.getParameter("cancellaPizze");

			if (creaPizza != null && creaPizza.equals("crea")) {
				PizzaDao pizzaDao = new PizzaDao();
				Pizza pizza = new Pizza();

				String[] ingredienti = request.getParameterValues("ingredienti");

				String nomePizza = request.getParameter("nome_pizza");
				Integer impastoId = Integer.parseInt(request.getParameter("impasti"));
				Integer utenteId = Integer.parseInt(request.getParameter("idUtente"));

				pizza.setNome(nomePizza);
				pizza.setUtenteId(utenteId);
				pizza.setImpastoId(impastoId);

				try {
					pizzaDao.create(pizza, ingredienti);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Utente utente = (Utente) request.getSession().getAttribute("utente");
				Map<Pizza, ArrayList<Integer>> pizze;
				try {
					pizze = pizzaDao.getPizzePerUtente(utente.getId());
					request.getSession().setAttribute("listaPizze", pizze);
					request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (cancellaPizze != null && cancellaPizze.equals("cancella")) {
				PizzaDao pizzaDao = new PizzaDao();
				String[] pizzeDaCancellare = request.getParameterValues("pizzeUtente");

				try {
					if (pizzeDaCancellare.length != 0) {
						pizzaDao.delete(pizzeDaCancellare);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Utente utente = (Utente) request.getSession().getAttribute("utente");
				Map<Pizza, ArrayList<Integer>> pizze;
				try {
					pizze = pizzaDao.getPizzePerUtente(utente.getId());
					request.getSession().setAttribute("listaPizze", pizze);
					request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		

	}

}
