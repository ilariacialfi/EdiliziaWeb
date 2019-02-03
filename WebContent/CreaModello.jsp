<%@page import="servlet.ModelloServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script>
	
<%!ModelloServlet servlet = new ModelloServlet();%>
	function aggiornaListe() {
<%servlet.aggiornaListaModelli(request, response);%>
	}
</script>

</head>
<title>Crea/Modifica modelli di stanze</title>
<body leftmargin="0" topmargin="0" onload="aggiornaListe()">
	<!-- questo form contiene il pulsante per tornare indietro -->
	<form action="ModelloServlet" method="GET">
		<fieldset style="border-color: #2e8b57;">
			<table>
				<tbody>
					<tr>
						<td><input name="indietro" type="submit" value="Indietro"></td>
					</tr>
				</tbody>
			</table>
		</fieldset>
		<!-- all'interno di questo fieldset visualizzo i modelli e li ricerco -->
		<fieldset style="border-color: #2e8b57;">
			<fieldset style="border-color: #63B063;">
				<legend> Scegliere il modello da modificare o creare un
					nuovo modello</legend>
				<table align="center">
					<tbody>
						<tr>
							<td><select name="modelli">
									<option label=Modelli></option>
									<c:forEach var="modello" items="${listaModelli}">
										<option><c:out value="${modello}" /></option>
									</c:forEach>

							</select></td>
							<td width=25%><input name="ok" type="submit"
								value="Ok"></td>
							<td width=25%><input name="elModello" type="submit"
								value="Elimina Modello" ></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<fieldset style="border-color: #63B063;">
				<table align="center">
					<tbody>
					<caption><nobr>Inserire o modificare nome modello:</nobr></caption>
					<tr>
						<td><input name="nome" style="height: 25px"
							value="${nomeModello}"></td>
					</tr>
					<tr>
						<td><input name="rinomina" type="submit" value="Rinomina" ></td>
					</tr>
					</tbody>
				</table>
			</fieldset>
			<fieldset style="border-color: #63B063;">
				<table align="center">
					<tbody>
						<tr>
							<td><select name="attrezzatura">
									<option label=Attrezzatura></option>
									<c:forEach var="attr" items="${listaAttrezzatura}">
										<option><c:out value="${attr}" /></option>
									</c:forEach>

							</select></td>
							<td width=25%><input name="aggiungi" type="submit"
								value="Aggiungi"></td>
							<td width=25%><input name="elimina" type="submit"
								value="Elimina Attrezzatura"></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</fieldset>
		<fieldset style="border-color: #2e8b57;">
			<legend>Attrezzatura relativa al modello scelto</legend>
			<table id="TabAttr" align="center">
				<tbody>
					<c:forEach var="attrMod" items="${attrezzaturaModello}">
						<tr>
							<td width=50% align="center"><c:out value="${attrMod}" /></td>
						</tr>
					</c:forEach>
					<tr>
						<td width=25% align="right"><input name="salva" type="submit"
							value="Salva" ></td>
					</tr>

				</tbody>
			</table>
		</fieldset>

	</form>
<body>

</body>
</html>