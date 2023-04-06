package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzaDao;
import dao.UtenteDao;
import model.Impasto;
import model.Ingrediente;
import model.Pizza;
import model.Utente;
import util.JPAUtil;

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
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		String creaPizza = request.getParameter("creaPizza");
		String cancellaPizze = request.getParameter("cancellaPizze");

		if (creaPizza != null && creaPizza.equals("crea")) {
			PizzaDao pizzaDao = new PizzaDao();
			Pizza pizza = new Pizza();

			String[] ingredienti = request.getParameterValues("ingredienti");

			List<Ingrediente> listaIngredienti = new ArrayList<Ingrediente>();
			
			for (int i = 0; i < ingredienti.length; i++) {
				Query query = entityManager.createQuery("SELECT i FROM Ingrediente i WHERE id = :id");
				query.setParameter("id", Integer.parseInt(ingredienti[i]));
				listaIngredienti.add((Ingrediente) query.getSingleResult());
			}


			String nomePizza = request.getParameter("nome_pizza");
			Integer impastoId = Integer.parseInt(request.getParameter("impasti"));
			Integer utenteId = Integer.parseInt(request.getParameter("idUtente"));

			Utente utente = entityManager.find(Utente.class, utenteId);
			Impasto impasto = entityManager.find(Impasto.class, impastoId);

			pizza.setNome(nomePizza);
			pizza.setUtente(utente);
			pizza.setImpasto(impasto);
			pizza.setIngredienti(listaIngredienti);

			try {
				pizzaDao.create(pizza);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<Pizza> pizze;

			//				pizze = pizzaDao.getPizzePerUtente(Utente.getId());
							pizze = utente.getPizze();
							request.getSession().setAttribute("listaPizze", pizze);
							request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
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
			List<Pizza> pizze;
			pizze = utente.getPizze();
			request.getSession().setAttribute("listaPizze", pizze);
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);

		}

	}

}
