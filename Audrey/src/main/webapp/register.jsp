<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script type="text/javascript">
        // Function to hide error messages after 5 seconds
        function hideErrors() {
            var errorElements = document.getElementsByClassName("error");
            for (var i = 0; i < errorElements.length; i++) {
                errorElements[i].style.display = "none";
            }
        }
        
        // Automatically hide error messages after 5 seconds
        setTimeout(hideErrors, 5000);
        
        function displayPhoneFormatMessage(phoneNumber) {
            var phoneFormatMessageElement = document.getElementById("phoneFormatMessage");
            if (phoneNumber.startsWith("07")) {
                phoneFormatMessageElement.textContent = "Local number format";
            } else if (phoneNumber.startsWith("2507")) {
                phoneFormatMessageElement.textContent = "International number format";
            } else {
                phoneFormatMessageElement.textContent = "";
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Registration Form</h2>
        <form action="register" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required>
            <%-- Display email error message --%>
            <span class="error">${emailError}</span>

            <label for="age">Age:</label>
            <input type="number" id="age" name="age" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <%-- Display password error message --%>
            <span class="error">${passwordError}</span>

            <label for="phoneNumber">Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" oninput="displayPhoneFormatMessage(this.value)" required>
            <%-- Display phone number error message --%>
            <span class="error">${phoneNumberError}</span>
            <p id="phoneFormatMessage"></p>

            <%-- Display general error message --%>
            <span class="error">${error}</span>

            <button type="submit">Register</button>
        </form>
        <p>Already have an account? <a href="./login.jsp">Login</a></p>
    </div>
</body>
</html>
