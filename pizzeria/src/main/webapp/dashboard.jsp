<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tua pagina privata</title>
</head>
<body>
	<%@page import="java.util.ArrayList, java.util.List"%>
	<%@page import="model.Impasto, model.Ingrediente, model.Pizza"%>


	<h1>Benvenuto, ${utente.name}</h1>

	<h2>Crea la tua pizza</h2>

	<%
	List<Impasto> tipiImpasti = (List<Impasto>) session.getAttribute("listaImpasti");
	List<Ingrediente> tipiIngredienti = (List<Ingrediente>) session.getAttribute("listaIngredienti");
	List<Pizza> pizze = (List<Pizza>) session.getAttribute("listaPizze");
	%>

	<form method="post" action="PizzaSrv">
		<input type="hidden" id="idUtente" name="idUtente"
			value="${utente.id}"> <input type="hidden" id="creaPizza"
			name="creaPizza" value="crea"> <label for="nome_pizza">
			Dai un nome alla tua pizza </label><br> <input id="nome_pizza"
			name="nome_pizza" type="text"> <br>
		<table>
			<tr>
				<th>Impasti</th>
				<th>Ingredienti</th>
			</tr>
			<tr>
				<td>
					<table>
						<%
						for (Impasto impasto : tipiImpasti) {
						%>
						<tr>
							<td><input type="radio" id="impasto-<%out.print(impasto.getId());%>"
								name="impasti" value="<%out.print(impasto.getId());%>"> <label
								for="impasto-<%out.print(impasto.getId());%>"> <%
 out.print(impasto.getName());
 %>
							</label></td>
						</tr>
						<%
						}
						%>
					</table>
				</td>
				<td>
					<table>
						<%
						for (Ingrediente ingrediente : tipiIngredienti) {
						%>
						<tr>
							<td><label for="ingrediente-<%out.print(ingrediente.getId());%>"> <input
									type="checkbox" name="ingredienti" id="ingrediente-<%out.print(ingrediente.getId());%>"
									value="<%out.print(ingrediente.getId());%>"> <%
 out.print(ingrediente.getName());
 %>
							</label></td>
						</tr>
						<%
						}
						%>
					</table>
				</td>
			</tr>
		</table>
		<input type="submit"> <br>
	</form>

	<form method="post" action="PizzaSrv">
		<input type="hidden" id="cancellaPizze" name="cancellaPizze"
			value="cancella">
		<table>
			<tr>
				<th>Le tue pizze</th>
			</tr>
			<%
			for (Pizza key : pizze) {
			%>
			<tr>
				<td>
					<%
					out.print(key.getNome());
					%>
				</td>
				<td>
					<%
					out.print(key.getImpasto().getName());
					%>
				</td>
				<td>
					<%
					List<Ingrediente> ingredienti = key.getIngredienti();

					for (int i = 0; i < ingredienti.size(); i++) {
						out.print(ingredienti.get(i).getName() + ", ");
					}
					%>
				</td>
				<td><input type="checkbox" name="pizzeUtente"
					id="<%out.print(key.getId());%>"
					value="<%out.print(key.getId());%>"></td>
			</tr>
			<%
			}
			%>
		</table>
		<input type="submit">
	</form>
</body>
</html>