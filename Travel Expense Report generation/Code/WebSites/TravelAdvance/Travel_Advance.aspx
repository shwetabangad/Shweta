<%@ Page Language="VB" %>

<%@ Import Namespace="System.Data.OleDb" %>
<%@ Import Namespace="System.IO" %>
<%@ Import Namespace="System.Drawing" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <title>Connection</title>

    <script runat="server">
        Public Class PictureBox
            Property Image As Drawing.Bitmap
        End Class

        Sub Search_Click(Src As Object, E As EventArgs)
            Try
                'Connect to the Database
                Dim cnAccess As New OleDbConnection(
                "Provider = Microsoft.Jet.OLEDB.4.0;" &
                "Data Source = C:\Users\Shweta\OneDrive\Documents\TALogin.mdb")

                cnAccess.Open()

                Dim semp_user_name As String
                semp_user_name = emp_user_name.Text.Trim

                'Construct the SELECT statement

                Dim sSelectSQL As String
                'Create the SQL Select Statement

                sSelectSQL = "SELECT * FROM expense_report WHERE ([emp_user_name] Like '" & semp_user_name & "')"

                'Create the OleDbCommand object
                Dim cmdSelect As New OleDbCommand(sSelectSQL, cnAccess)
                Dim drEmp As OleDbDataReader, sbResults As New StringBuilder()
                drEmp = cmdSelect.ExecuteReader()
                sbResults.Append("<table>")
                Do While drEmp.Read()
                    sbResults.Append("<table>")
                    sbResults.Append("<tr><td><b>Expense ID: </b>")
                    sbResults.Append(drEmp.GetInt32(0))
                    sbResults.Append("</td></tr><tr><td><b>  Total Expense: </b>")
                    'Response.Write(Convert.ToInt32(drEmp.GetString(1)))
                    sbResults.Append(Convert.ToInt32(drEmp.GetString(1)) + Convert.ToInt32(drEmp.GetString(2)) + Convert.ToInt32(drEmp.GetString(3)) + Convert.ToInt32(drEmp.GetString(4)))
                    sbResults.Append("")
                    sbResults.Append("</table>")
                    sbResults.Append("<br></br>")

                Loop

                sbResults.Append("</table>")

                msg.Text = sbResults.ToString()

                cnAccess.Close()
                cnAccess = Nothing
                If sbResults.ToString().Length < 16 Then
                    Response.Write("Data Not Found")
                Else
                    Response.Write("Data Found!")
                End If

            Catch ex As Exception
                Response.Write(ex.Message)
                Response.Write("Connection Failed")
            End Try
        End Sub

    </script>
</head>

<body style="font-family: Tahoma;">
    <h3>Enter Employee Name</h3>
    <form runat="server" id="form1">
        <table>
            <tr>
                <td>Employee Name: </td>
                <td>
                    <asp:TextBox ID="emp_user_name" runat="server" /></td>
            </tr>
        </table>
        <br />
        <asp:Button Text="Search" OnClick="Search_Click"
            runat="server" ID="Button1" />

        <p>
            <asp:Label ID="msg" runat="server" />
        </p>

    </form>
    <div></div>
</body>
</html>

