package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzaDao;
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

		Utente utente = (Utente) request.getSession().getAttribute("utente");
		List<Pizza> pizze = new ArrayList<Pizza>();

		if (creaPizza != null && creaPizza.equals("crea")) {
			Pizza pizza = new Pizza();

			String[] ingredienti = request.getParameterValues("ingredienti");
			List<Ingrediente> listaIngredienti = new ArrayList<Ingrediente>();

			for (int i = 0; i < ingredienti.length; i++) {
				TypedQuery<Ingrediente> query = entityManager.createQuery("SELECT i FROM Ingrediente i WHERE id = :id",
						Ingrediente.class);
				query.setParameter("id", Integer.parseInt(ingredienti[i]));
				listaIngredienti.add((Ingrediente) query.getSingleResult());
			}

			String nomePizza = request.getParameter("nome_pizza");
			Integer impastoId = Integer.parseInt(request.getParameter("impasti"));
			Impasto impasto = entityManager.find(Impasto.class, impastoId);

			pizza.setNome(nomePizza);
			pizza.setUtente(utente);
			pizza.setImpasto(impasto);
			pizza.setIngredienti(listaIngredienti);

			try {
				PizzaDao.create(pizza);
				pizze = PizzaDao.getPizzePerUtente(utente.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		else if (cancellaPizze != null && cancellaPizze.equals("cancella")) {
			String[] pizzeDaCancellare = request.getParameterValues("pizzeUtente");
			

			try {
				PizzaDao.delete(pizzeDaCancellare);
				pizze = PizzaDao.getPizzePerUtente(utente.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			request.getSession().setAttribute("listaPizze", pizze);
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);

	}

}
