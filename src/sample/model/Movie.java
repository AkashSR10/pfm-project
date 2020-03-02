package sample.model;


    public class Movie {

        public String primaryTitle;
        public String titleType;
        public String averageRating;
        public String numVotes;
        public String runtimeMinutes;
        public String genre1;
        public String genre2;
        public String genre3;
        public String director;
        public String writer;

        public String getPrimaryTitle() {
            return primaryTitle;
        }

        public void setPrimaryTitle(String primaryTitle) {
            this.primaryTitle = primaryTitle;
        }

        public String getTitleType() {
            return titleType;
        }

        public void setTitleType(String titleType) {
            this.titleType = titleType;
        }

        public String getNumVotes() {
            return numVotes;
        }

        public void setNumVotes(String numVotes) {
            this.numVotes = numVotes;
        }

        public String getRuntimeMinutes() {
            return runtimeMinutes;
        }

        public void setRuntimeMinutes(String runtimeMinutes) {
            this.runtimeMinutes = runtimeMinutes;
        }

        public String getGenre1() {
            return genre1;
        }

        public void setGenre1(String genre1) {
            this.genre1 = genre1;
        }

        public String getGenre2() {
            return genre2;
        }

        public void setGenre2(String genre2) {
            this.genre2 = genre2;
        }

        public String getGenre3() {
            return genre3;
        }

        public void setGenre3(String genre3) {
            this.genre3 = genre3;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getWriter() {
            return writer;
        }

        public void setWriter(String writer) {
            this.writer = writer;
        }
    }

