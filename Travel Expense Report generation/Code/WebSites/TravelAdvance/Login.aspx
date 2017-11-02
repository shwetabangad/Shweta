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

            Dim semp_user_name As String
            semp_user_name = emp_user_name.Text.Trim

            Dim semp_password As String
            semp_password = emp_password.Text.Trim

            'Construct the SELECT statement

            Dim sSelectSQL As String
            'Create the SQL Select Statement

            sSelectSQL = "SELECT * FROM employee_tbl WHERE emp_user_name= '" & semp_user_name & "' and emp_password= '" & semp_password & "'"

            'Create the OleDbCommand object
            Dim cmdSelect As New OleDbCommand(sSelectSQL, cnAccess)
            Dim drEmp As OleDbDataReader, sbResults As New StringBuilder()

            drEmp = cmdSelect.ExecuteReader()
            sbResults.Append("<table>")
            If drEmp.HasRows Then
                Session("emp_user_name") = semp_user_name
                Response.Redirect("Expense_report.aspx")

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
<h3>TRAVEL ADVANCE Employee Login</h3>
<form runat = "server" id = "form1">
<table>
    <tr>
    </tr>
<tr>
<td>Employee username: </td>
<td><asp:Textbox id = "emp_user_name" runat = "server" /></td>
</tr>
    <tr>

    </tr>
    <tr>
<td>Employee password: </td>
<td><asp:Textbox id = "emp_password" runat = "server" /></td>
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

