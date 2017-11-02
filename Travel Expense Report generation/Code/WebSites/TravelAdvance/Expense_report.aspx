<%@ Page Language = "VB" %>
<%@ Import Namespace = "System.Data.OleDb" %>
<!DOCTYPE html>
<html xmlns = "http://www.w3.org/1999/xhtml">
<head id="Head1" runat = "server">
<title>Connection</title>
<script runat = "server">
    Sub Insert_Click(Src As Object, E As EventArgs)
        Try
            'Connect to the Database
            Dim cnAccess As New OleDbConnection(
            "Provider = Microsoft.Jet.OLEDB.4.0;" &
            "Data Source = C:\Users\Shweta\OneDrive\Documents\TALogin.mdb")

            cnAccess.Open()
            Dim sHotel_Expenses, sMeal_Expenses, sTaxi_Expenses, sTicket_Expenses As Integer
            Dim suser, sInsertSQL As String

            sHotel_Expenses = Hotel_Expenses.Text
            sMeal_Expenses = Meal_Expenses.Text
            sTaxi_Expenses = Taxi_Expenses.Text
            sTicket_Expenses = Ticket_Expenses.Text
            suser = Session("emp_user_name")


            'Constr7uct the insert statement
            sInsertSQL = "INSERT INTO expense_report(" &
"[Hotel_Expenses], [Meal_Expenses], [Taxi_Expenses], [Ticket_Expenses], [emp_user_name]) VALUES" &
"('" & sHotel_Expenses & "','" & sMeal_Expenses & "','" & sTaxi_Expenses & "','" & sTicket_Expenses & "','" & suser & "');"

            'Construct the OleDbCommand object
            Dim cmdInsert As New OleDbCommand(sInsertSQL, cnAccess)

            'since this is not a query, we do not expect to return data 
            cmdInsert.ExecuteNonQuery()

            Response.Write("Data Recorded!")
        Catch ex As Exception
            Response.Write(ex.Message)
            Response.Write("Connection Failed")
        End Try

    End Sub


</script>
</head>

<body style = "font-family:Tahoma;">
<h3>Enter your Expenses for Travel</h3>
<form runat = "server" id = "form1">

<table>

<tr>
<td>Hotel Expense: </td>
<td><asp:Textbox id = "Hotel_Expenses" runat = "server" /></td>
</tr>
<tr>
<td>Meal Expense: </td>
<td><asp:Textbox id = "Meal_Expenses" runat = "server" /></td>
</tr>
<tr>
<td>Taxi Expense: </td>
<td><asp:Textbox id = "Taxi_Expenses" runat = "server" /></td>
</tr>
<tr>
<td>Ticket Expense: </td>
<td><asp:Textbox id = "Ticket_Expenses" runat = "server" /></td>
</tr>

</table>
<br />
<asp:Button Text = "Insert" OnClick = "Insert_Click"
runat = "server" ID = "Button1" />
<p>
<asp:Label id = "msg" runat = "server" />
</p>
<br />
</form>

<div></div>
</body>
</html>

