<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<p>&nbsp;</p>
<title>Login</title>
<h2 style="text-align: center;">
	<span style="color: #2e8b57;"> Benvenuto, accedi al sistema di
		gestione della tua macroarea universitaria</span>
</h2>
<center>
	<!-- imposto il metodo post in modo che i paramentri del form non vengano mostrati nell'url -->
	<form method="POST" action="LoginServlet" name="login">
		<fieldset style="border-color: #2e8b57;">
			<legend>Login</legend>

			<table style="height: 109px;" data-width="367">
				<tbody>
					<tr>
						<td style="width: 103.2px;">UserID</td>
						<td style="width: 248.8px;"><input name="id" type="text"
							required autofocus/></td>
					</tr>
					<tr>
						<td style="width: 103.2px;">Password</td>
						<td style="width: 248.8px;"><input name="pass"
							type="password" required /></td>
					</tr>
					<tr>
						<td style="width: 103.2px;">&nbsp;</td>
						<td style="width: 248.8px;"><input name="b_accedi"
							type="submit" value="login" /></td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</form>
</center>