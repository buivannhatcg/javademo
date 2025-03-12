<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.example.employee.model.Employee"%>
<!DOCTYPE html>
<html>
<head>
    <title>Employees</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<div class="container mt-5">
    <h2>Employees List</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>EmployeeID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Hire Date</th>
                <!-- Bạn có thể bổ sung thêm các cột khác nếu cần -->
            </tr>
        </thead>
        <tbody>
            <%
                List<Employee> employees = (List<Employee>) request.getAttribute("employees");
                if (employees != null) {
                    for (Employee emp : employees) {
            %>
            <tr>
                <td><%= emp.getEmployeeID() %></td>
                <td><%= emp.getFirstName() %></td>
                <td><%= emp.getLastName() %></td>
                <td><%= emp.getEmail() %></td>
                <td><%= emp.getPhoneNumber() %></td>
                <td><%= emp.getHireDate() %></td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
    <a href="index.jsp" class="btn btn-secondary">Back</a>
</div>
</body>
</html>
