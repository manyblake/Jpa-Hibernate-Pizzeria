<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tua pagina privata</title>
</head>
<body>
	<%@page import="java.util.ArrayList, java.util.Map"%>
	<%@page import="model.Impasto, model.Ingrediente, model.Pizza"%>
	

	<h1>Benvenuto, ${utente.name}</h1>

	<h2>Crea la tua pizza</h2>

	<%
	Map<Integer, String> tipiImpasti = (Map<Integer, String>) session.getAttribute("listaImpasti");
	Map<Integer, String> ingredienti = (Map<Integer, String>) session.getAttribute("listaIngredienti");
	Map<Pizza, ArrayList<Integer>> pizze = (Map<Pizza, ArrayList<Integer>>) session.getAttribute("listaPizze");
	%>

	<form method="post" action="PizzaSrv">
		<input type="hidden" id="idUtente" name="idUtente" value="${utente.id}">
		<input type="hidden" id="creaPizza" name="creaPizza" value="crea">
		<label for="nome_pizza">Dai un nome alla tua pizza</label><br>
		<input id="nome_pizza" name="nome_pizza" type="text">
		<br>
		<table>
			<tr>
				<th>Impasti</th>
			  	<th>Ingredienti</th>		    
			</tr>
			<tr>
				<td>
					<table>
						
						<%for (Integer key : tipiImpasti.keySet()) {%>
							<tr>
								<td>
									<input type="radio" id="<%out.print(key);%>" name="impasti" value="<%out.print(key);%>">   
									<label for="<%out.print(key);%>"><%out.print(tipiImpasti.get(key));%></label>
								</td>
							</tr>
						<% } %>
					</table>
				</td>
				<td>
					<table>
						<%for (Integer key : ingredienti.keySet()) {%>
							<tr>
								<td>
									<label for="<%out.print(key);%>"> 
										<input type="checkbox" name="ingredienti" id="<%out.print(key);%>" value="<%out.print(key);%>">
										<%out.print(ingredienti.get(key));%> 
									</label>
								</td>
							</tr>
						<% } %>
					</table>
				</td>   
			</tr>
		</table>
		<input type="submit">
		<br> 
	</form>
	
	<form method="post" action="PizzaSrv">
		<input type="hidden" id="cancellaPizze" name="cancellaPizze" value="cancella">	
		<table>
			<tr>
				<th>Le tue pizze</th>
			</tr>
				<%for (Pizza key : pizze.keySet()) {%>
					<tr>
						<td>							 
							<%out.print(key.getNome());%>							
						</td>
						<td>
							 <%out.print(tipiImpasti.get(key.getImpastoId()));%> 							
						</td>
						<td>
						<% ArrayList<Integer> keys = new ArrayList(pizze.get(key));
							for(int i = 0; i < keys.size(); i++) {			
								out.print(ingredienti.get(keys.get(i)) + ", ");
							} %>
						</td>
						<td>
							<input type="checkbox" name="pizzeUtente" id="<%out.print(key.getId());%>" value="<%out.print(key.getId());%>">
						</td>
					</tr>
				<% } %>			
		</table>
		<input type="submit">
	</form>
</body>
</html>