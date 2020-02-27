// Query for where First_name = Nick
 stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from Member where First_name is 'Nick' ");

        while (rs.next()) {
            String First_name = rs.getString("First_name");
            String Last_name = rs.getString("Last_name");
            String Email= rs.getString("Email");



            System.out.println(First_name);
            System.out.println(Last_name);
            System.out.println(Email);
        }
        rs.close();
        stmt.close();
        conn.close();

// Query for where Last_name = Gutic
stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from Member where Last_name is 'Gutic' ");

// Query to show all member info
stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from Member");
