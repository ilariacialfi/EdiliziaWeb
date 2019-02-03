<%@page import="servlet.StanzaServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script>
	
<%!StanzaServlet servlet = new StanzaServlet();%>
	function aggiornaListe() {
<%servlet.aggiornaListaStanze(request, response);%>
	}
</script>

</head>
<title>Crea/Modifica stanze</title>
<body leftmargin="0" topmargin="0" onload="aggiornaListe()">
	<!-- questo form contiene il pulsante per tornare indietro -->
	<form action="StanzaServlet" method="GET">
		<fieldset style="border-color: #2e8b57;">
			<table>
				<tbody>
					<tr>
						<td><input name="indietro" type="submit" value="Indietro"></td>
					</tr>
				</tbody>
			</table>
		</fieldset>
		<!-- all'interno di questo fieldset visualizzo le stanze e le ricerco -->
		<fieldset style="border-color: #2e8b57;">
			<fieldset style="border-color: #63B063;">
				<legend> Scegliere la stanza da modificare o crearne una
					nuova</legend>

				<table align="center">
					<tbody>
						<tr>
							<td><select name="stanze">
									<option label=Stanze></option>
									<c:forEach var="stanza" items="${listaStanze}">
										<option><c:out value="${stanza}" /></option>
									</c:forEach>

							</select></td>
							<td width=25%><input name="ok" type="submit"
								value="Ok"></td>
							<td width=25%><input name="elStanza" type="submit"
								value="Elimina Stanza"></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<fieldset style="border-color: #63B063;">
				<legend> Scegliere un modello per la stanza: </legend>
				<table align="center">
					<tbody>
						<tr>
							<td><select name="modelli">
									<option label=Modelli></option>
									<c:forEach var="modello" items="${listaModelli}">
										<option><c:out value="${modello}" /></option>
									</c:forEach>

							</select></td>
							<td width><input name="importa" type="submit"
								value="Importa Modello"></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<fieldset style="border-color: #63B063;">
				<table align="center">
					<tbody>
					<caption>
						<nobr>Inserire o modificare:</nobr>
					</caption>
					<tr>
						<td><input name="nome" style="height: 25px"
							value="${nomeStanza}"></td>
						<td>Edificio:</td>
						<td><select name="edificio">
								<c:forEach var="edificio" items="${listaEdifici}">
									<option><c:out value="${edificio}" /></option>
								</c:forEach>
						</select></td>
						<td>Piano:</td>
						<td><select name="piano">
								<c:forEach var="piano" items="${listaPiani}">
									<option><c:out value="${piano}" /></option>
								</c:forEach>
						</select></td>
						<td>Tipo:</td>
						<td><select name="tipo">
								<c:forEach var="tipo" items="${listaTipi}">
									<option><c:out value="${tipo}" /></option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><input name="rinomina" type="submit" value="Rinomina"
							align="right"></td>
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
							<td><input type="number" name="quantita" min="0" max="1000"
								step="1" placeholder="Q.tà"></td>
							<td width=25%><input name="aggiungi" type="submit"
								value="Aggiungi"></td>
							<td width=25%><input name="elimina" type="submit"
								value="Elimina Attrezzatura"></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<fieldset style="border-color: #2e8b57;">
				<legend>Attrezzatura relativa alla stanza scelta</legend>
				<table id="TabAttr" align="center">
					<thead>
						<tr bgcolor="#63B063">
							<th width=50% align="center">Attrezzatura</th>
							<th width=50% align="center">Quantità</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="attrSt" items="${attrezzaturaStanza}">
							<tr>
								<td width=50% align="center"><c:out
										value="${attrSt.getAttr()}" /></td>
								<td width=50% align="center"><c:out
										value="${attrSt.getQuantita()}" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td width=25% align="right"><input name="salva"
								type="submit" value="Salva"></td>
						</tr>

					</tbody>
				</table>
			</fieldset>
		</fieldset>
	</form>
</body>
</html>