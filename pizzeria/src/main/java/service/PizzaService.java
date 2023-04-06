package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.PizzaDao;
import model.Pizza;

@Path("/pizze")
public class PizzaService {

	@GET
	@Path("/{userId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Map<Pizza, List<Integer>> getPizzas_JSON(@PathParam("userId") String userId) {
		Map<Pizza, List<Integer>> listOfPizzas = null;
		try {
			listOfPizzas = PizzaDao.getPizzePerUtente(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfPizzas;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void addPizza(Pizza pizza) {
		try {
			PizzaDao.create(pizza);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void deletePizzas(String[] pizzeDaCancellare) {
		try {
			PizzaDao.delete(pizzeDaCancellare);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
