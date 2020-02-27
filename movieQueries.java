// Queries for movies

        // Query all movies
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie;");

        while (rs.next()) {
            String primaryTitle = rs.getString("primaryTitle");

            System.out.println(primaryTitle);
        }
        rs.close();
        stmt.close();
        conn.close();

        // Query movies where title is similar to "Drive
         stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where primaryTitle like 'Drive' ");

        while (rs.next()) {
            String primaryTitle = rs.getString("primaryTitle");
            String titleType = rs.getString("titleType");
            Integer averageRating = rs.getInt("averageRating");
            Integer numVotes = rs.getInt("numVotes");
            Integer runtimeMinutes = rs.getInt("runtimeMinutes");
            Integer year = rs.getInt("year");
            String genre1 = rs.getString("genre1");
            String genre2 = rs.getString("genre2");
            String genre3 = rs.getString("genre3");
            String director = rs.getString("director");
            String writer = rs.getString("writer");



            System.out.println(primaryTitle);

        // Query movies where Type is movie
                stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where titleType is 'movie' ");

        while (rs.next()) {
            String primaryTitle = rs.getString("primaryTitle");
            String titleType = rs.getString("titleType");
            Integer averageRating = rs.getInt("averageRating");
            Integer numVotes = rs.getInt("numVotes");
            Integer runtimeMinutes = rs.getInt("runtimeMinutes");
            Integer year = rs.getInt("year");
            String genre1 = rs.getString("genre1");
            String genre2 = rs.getString("genre2");
            String genre3 = rs.getString("genre3");
            String director = rs.getString("director");
            String writer = rs.getString("writer");



            System.out.println(primaryTitle);
            //System.out.println(titleType);
            //System.out.println(averageRating);
            //System.out.println(numVotes);
            //System.out.println(runtimeMinutes);
            //System.out.println(year);
            //System.out.println(genre1);
            //System.out.println(genre2);
            //System.out.println(genre3);
            //System.out.println(director);
            //System.out.println(writer);
        }
        rs.close();
        stmt.close();
        conn.close();



        // Query movies where Type is tvEpisode

               stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where titleType is 'tvEpisode' ");


        // Query movies where average rating is 1

          stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating like '1,%' ");

        // Query movies where average rating is 2

          stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating like '2,%' ");


        // Query movies where average rating is 3

          stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating like '3,%' ");

        // Query movies where average rating is 4

          stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating like '4,%' ");

        // Query movies where average rating is 5

          stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating like '5,%' ");

        // Query movies where average rating is 6

          stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating like '6,%' ");

        // Query movies where average rating is 7

          stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating like '7,%' ");

        // Query movies where average rating is 8

          stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating like '8,%' ");;

        // Query movies where average rating is 9

          stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating like '9,%' ");

        // Query movies where average rating is 10

          stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating like '10' ");

        // Query movies where average rating is at least 1

         stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating >= '1,0' ");

        // Query movies where average rating is at least 2

         stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating >= '2,0' ");

        // Query movies where average rating is at least 3

         stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating >= '3,0' ");

        // Query movies where average rating is at least 4

         stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating >= '4,0' ");

        // Query movies where average rating is at least 5

         stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating >= '5,0' ");

        // Query movies where average rating is at least 6

         stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating >= '6,0' ");

        // Query movies where average rating is at least 7

         stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating >= '7,0' ");

        // Query movies where average rating is at least 8

         stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating >= '8,0' ");

        // Query movies where average rating is at least 9

         stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating >= '9,0' ");

        // Query movies where average rating is 10

         stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where averageRating >= '10,0' ");


        // Query movies where runtime is "120 minutes"
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where runtimeMinutes == '120' ");


        // Query movies where runtime is at least "110 minutes"
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where runtimeMinutes >= '110' ");

        // Query movies where runtime is between "110" and "120" minutes
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where runtimeMinutes between 110 and 120");


        // query movies where year is "2010"
         stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where year == 2010");


        // Query movies created between year "2018" and "2019"
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where year between 2018 and 2019");

        // Query movies created in decade "90"
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where year like '199%' ");

        // Query movies created in decade "80"
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where year like '198%' ");

        // Query movies created in decade "00"
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where year like '200%' ");

        // Query movies created in decade "10"
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where year like '201%' ");

        // Query movies with genre Action
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where' Action' in (genre1, genre2, genre3)");

        // Query movies with genre Thriller
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where' Thriller' in (genre1, genre2, genre3)");

        // Query movies with genre Animation
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where' Animation' in (genre1, genre2, genre3)");

        // Query movies with genre Adventure
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where' Adventure' in (genre1, genre2, genre3)");

        // Query movies with genre Biography
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Biography' in (genre1, genre2, genre3)");

        // Query movies with genre Comedy
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Comedy' in (genre1, genre2, genre3)");

        // Query movies with genre Crime
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Crime' in (genre1, genre2, genre3)");

        // Query movies with genre Documentary
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Documentary' in (genre1, genre2, genre3)");

        // Query movies with genre Drama
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Drama' in (genre1, genre2, genre3)");

        // Query movies with genre Horror
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Horror' in (genre1, genre2, genre3)");

        // Query movies with genre Fantasy
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Fantasy' in (genre1, genre2, genre3)");

        // Query movies with genre Music
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Music' in (genre1, genre2, genre3)");

        // Query movies with genre Mystery
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Mystery' in (genre1, genre2, genre3)");

        // Query movies with genre Romance
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Romance' in (genre1, genre2, genre3)");

        // Query movies with genre Sci_Fi
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Sci_Fi' in (genre1, genre2, genre3)");

        // Query movies with genre Talk_Show
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Talk_Show' in (genre1, genre2, genre3)");

        // Query movies with genre War
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'War' in (genre1, genre2, genre3)");

        // Query movies with genre Western
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Western' in (genre1, genre2, genre3)");

        // Query movies with genre Family
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from movie where 'Family' in (genre1, genre2, genre3)");
