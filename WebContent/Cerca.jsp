<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>

</head>
<title>Cerca Stanza</title>
<!-- appena viene caricata la pagina si vanno ad aggiornare le liste, per prima quella delle attrezzature -->

<body leftmargin="0" topmargin="0" onload="aggiornaListe()">
	<!-- questo form contiene i pulsanti per uscire, creare una nuova stanza e un nuovo modello -->
	<form action="CercaServlet" method="GET">
		<fieldset>
			<table>
				<tbody>
					<tr>
						<td><input name="esci" type="submit" value="Esci"></td>
						<td><input name="creaStanza" type="submit"
							value="Crea/Modifica Stanza"></td>
						<td><input name="creaModello" type="submit"
							value="Crea/Modifica Modello"></td>
					</tr>
				</tbody>
			</table>
		</fieldset>

	</form>
	<form action="CercaServlet" method="GET">
		<fieldset>
			<legend> Scegliere le attrezzature per ricercare la stanza</legend>
			<table>
				<tbody>
					<tr>
						<td><select name="listaStanze">
								<option label=Attrezzatura></option>
								<option></option>
						</select></td>
						<td><input type="number" name="min" min="0" max="1000"
							step="1" placeholder="Min"></td>
						<td><input type="number" name="max" min="0" max="1000"
							step="1" placeholder="Max"></td>
					</tr>
				</tbody>
			</table>

		</fieldset>
	</form>
</body>
</html>