<%@ Page Language = "VB" %>
<%@ Import Namespace = "System.Data.OleDb" %>
<!DOCTYPE html>
<html xmlns = "http://www.w3.org/1999/xhtml">
<head id="Head1" runat = "server">
<title>Connection</title>
<script runat = "server">

    Sub Login_Click(Src As Object, E As EventArgs)
        Try
            'Connect to the Database
            Dim cnAccess As New OleDbConnection(
            "Provider = Microsoft.Jet.OLEDB.4.0;" &
            "Data Source = C:\Users\Shweta\OneDrive\Documents\TALogin.mdb")

            cnAccess.Open()

            Dim sAccountant_name As String
            sAccountant_name = accountant_name.Text.Trim

            Dim sAccountant_password As String
            sAccountant_password = accountant_password.Text.Trim

            'Construct the SELECT statement

            Dim sSelectSQL As String
            'Create the SQL Select Statement

            sSelectSQL = "SELECT * FROM accountant_tbl WHERE Accountant_name= '" & sAccountant_name & "' and Accountant_password= '" & sAccountant_password & "'"

            'Create the OleDbCommand object
            Dim cmdSelect As New OleDbCommand(sSelectSQL, cnAccess)
            Dim drEmp As OleDbDataReader, sbResults As New StringBuilder()

            drEmp = cmdSelect.ExecuteReader()
            sbResults.Append("<table>")
            If drEmp.HasRows Then

                Response.Redirect("Travel_Advance.aspx")

            Else
                Response.Write("Username password does not match!")
            End If

        Catch ex As Exception
            Response.Write(ex.Message)
            Response.Write("Connection Failed")
        End Try
    End Sub

</script>
</head>
<body style = "font-family:Tahoma;">
<h3>Accountant Login</h3>
<form runat = "server" id = "form1">
<table>
<tr>
<td>Accountant username: </td>
<td><asp:Textbox id = "accountant_name" runat = "server" /></td>
</tr>
    <tr>
<td>Accountant password: </td>
<td><asp:Textbox id = "accountant_password" runat = "server" /></td>
</tr>
</table>
<br />
<asp:Button Text = "Login" OnClick = "Login_Click"
runat = "server" ID = "Button1" />

<p>
<asp:Label id = "msg" runat = "server" />
</p>
</form>
<div></div>
</body>
</html>

